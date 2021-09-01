#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#include <wchar.h>
#include <math.h>
#include <wctype.h>
#include <time.h>
#include <stdbool.h>
#include <unistd.h>

#include "fatal.h"
#include "hashtable_keys.h"
#include "hashtable_words.h"

#define BUFFER_LENGTH 400
#define WORDS_TABLE_SIZE 9000000
#define KEYS_TABLE_SIZE 10


/*
* Function: OpenDictionary
* ------------------------
* open the dictionary via fileName passed as argument of the program, if the dictionary is inside the dictionaries folder
*
* fileName: name of the file to be opened
* wayToOpen: way that file will be opened(read, write, read and write, ...)
*
* Returns: the pointer to the opened file
*/
FILE *OpenDictionary(char *fileName, char* wayToOpen)
{
    FILE *fp;
    char filePath[BUFFER_LENGTH] = "../dictionaries/";
    strcat(filePath, fileName);

    printf("%s\n", filePath);

    fp = fopen(filePath, wayToOpen);

    return fp;
}


/*
* Function: CloseDictionary
* -------------------------
* close the file
*
* fp: file to be closed
*/
void CloseDictionary(FILE *fp)
{
    fclose(fp);
}


/*
* Function: UpdateDictionary
* --------------------------
* copy the contente of file source to file destination and insert the word passed as argument in destination file
*
* source: current file
* destination: updated file
* word: word to insert in destination file
*/
void UpdateDictionary(char* source, char* destination, wchar_t* word)
{
    // declaring file pointers and local variables
    char buff[100];
    FILE *fp1, *fp2;
 
    // opening files
    fp1 = OpenDictionary(source, "a+");
    fp2 = OpenDictionary(destination, "a+");
  
    //Copy all lines to output file if the file is empty
    fseek (fp2, 0, SEEK_END);
    int size = ftell(fp2);

    if (0 == size) {
        while (!feof(fp1)) {
            fgets(buff, sizeof(buff), fp1);
            fprintf(fp2, "%s", buff);
        }
    }

    CloseDictionary(fp2);
    fp2 = OpenDictionary(destination, "a+");

    //Check which type of dictionary is it
    fseek(fp1, 0, SEEK_SET);
    fscanf(fp1,"%s", buff);

    //Update dictionary with word and frequency==0
    if(strstr(buff, ",") != 0)
    {
        fwprintf(fp2, L"\n");
        fwprintf(fp2, L"%ls,0", word);
        rewind(fp2);
    }

    //Update dictionary with word passed as argument
    else
    {
        fwprintf(fp2, L"\n");
        fwprintf(fp2, L"%ls", word);
        rewind(fp2);
    }
}


/*
* Function: CleanWordProcess
* --------------------------
* processes the passed word, converting it to lowercase, and "remove" '-' or '\''
*
* word: word to be processed and cleaned
*
* Returns: cleaned word
*/
wchar_t *CleanWordProcess(wchar_t* word)
{
    wchar_t *tmpWord = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(word));
    
    //Clean word process
    for(int i=0 ; i<wcslen(word); i++)
    {
        //Remove '-' and '\''(apostrophe) from the word
        if('-' == word[i] || '\'' == word[i])
            break;  
 
        else
            tmpWord[i]= towlower(word[i]);

    }
    wchar_t *wordClean = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(tmpWord));
    wcscpy(wordClean, tmpWord);
    free(tmpWord);
    return wordClean;
}


/*
* Function: ProcessData
* ---------------------
* inserts the keys into KeysTable
* read the lines from the file and insert them into WordsTable, after line been processed and word cleaned!
*
* fp: file to be readed
* KeysTable: Hashtable to save the T9Keys
* WordsTable: Hashtable to save the processed words
*/
void ProcessData(FILE *fp, HashTable KeysTable, HashTable WordsTable)
{
    InsertT9Keys(KeysTable);        //Insert Keys in KeysTable
    
    printf("Loading dictionary...\n");

    wchar_t buffer[BUFFER_LENGTH];
    wchar_t *tmpWord;
    wchar_t *cleanWord;

    //Verification if the files contains word's usage frequency
    fwscanf(fp,L"%ls", buffer);

    if(wcsstr(buffer, L",") != 0)
    {
        //printf("Is a frequency file!\n");
        wchar_t *wordFreq;

        while(fwscanf(fp,L"%ls", buffer) != EOF)
        {
            wchar_t* bufferAux;
            wchar_t *word = wcstok(buffer, L",", &bufferAux);
            wordFreq = wcstok(NULL, L",", &bufferAux);
            long freq = wcstol(wordFreq, NULL, 10);

            tmpWord = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(buffer));
            tmpWord = wcscpy(tmpWord, word);
            cleanWord = CleanWordProcess(tmpWord);
            unsigned long res = StringToIntAccordingT9Keys(cleanWord, KeysTable);
            InsertWordAccordingFrequency(tmpWord, freq, res, WordsTable);
        }

        //Process the last line of the file
        long freq = wcstol(wordFreq, NULL, 10);
        tmpWord = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(buffer));
        tmpWord = wcscpy(tmpWord, buffer);
        cleanWord = CleanWordProcess(tmpWord);
        unsigned long res = StringToIntAccordingT9Keys(cleanWord, KeysTable);
        InsertWordAccordingFrequency(tmpWord, freq, res, WordsTable);
    }

    else
    {
        fseek(fp, 0, SEEK_SET); //Sets again the pointer to the start of the file
        //printf("Is not a frequency file\n");

        while(fwscanf(fp,L"%ls", buffer) != EOF)
        {
            tmpWord = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(buffer));
            tmpWord = wcscpy(tmpWord, buffer);
            cleanWord = CleanWordProcess(tmpWord);
            unsigned long res = StringToIntAccordingT9Keys(cleanWord, KeysTable);
            InsertWord(tmpWord, res, WordsTable);
        }

        //Process the last line of the file
        tmpWord = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(buffer));
        tmpWord = wcscpy(tmpWord, buffer);
        cleanWord = CleanWordProcess(tmpWord);
        unsigned long res = StringToIntAccordingT9Keys(cleanWord, KeysTable);
        InsertWord(tmpWord, res, WordsTable);
    }

    free(tmpWord);
    free(cleanWord);
    printf("Dictionary successfuly loaded!\n");
}


/*
* Function: InsertWordInHashAndPhrase
* -----------------------------------
* scans the word that user typed, that doesnt exists 
* insert it in the current phrase
* updated the dictionary
*
* fp: current dictionary
* wordToInsert: word to insert in phrase and hashtable
* phrase: phrase of accepted words suggestions by user
* KeysTable: Hashtable to save the T9Keys
* WordsTable: Hashtable to save the processed words
*
* Returns: the inserted word
*/
wchar_t *InsertWordInHashAndPhrase(FILE *fp, wchar_t *wordToInsert, wchar_t *phrase, HashTable WordsTable, HashTable KeysTable)
{
    char tmpBuff[BUFFER_LENGTH];
    wchar_t *tmpWord;
    wchar_t *cleanWord;

    //Insert word to current phrase
    wcscat(phrase, wordToInsert);
    wcscat(phrase, L" ");
    
    //Check which type of dictionary is it
    fseek(fp, 0, SEEK_SET);
    fscanf(fp,"%s", tmpBuff);

    //Update dictionary with word and frequency==0
    if(strstr(tmpBuff, ",") != 0)
    {
        //Clean the word to insert it in the WordsTable
        tmpWord = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(wordToInsert));
        tmpWord = wcscpy(tmpWord, wordToInsert);
        cleanWord = CleanWordProcess(tmpWord);
        unsigned long res = StringToIntAccordingT9Keys(cleanWord, KeysTable);
        InsertWordAccordingFrequency(tmpWord, 0, res, WordsTable);
    }

    //Not frequency file
    else
    {
        //Clean the word to insert it in the WordsTable
        tmpWord = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(wordToInsert));
        tmpWord = wcscpy(tmpWord, wordToInsert);
        cleanWord = CleanWordProcess(tmpWord);
        unsigned long res = StringToIntAccordingT9Keys(cleanWord, KeysTable);
        InsertWord(tmpWord, res, WordsTable);
    }

    free(cleanWord);
    return tmpWord;
}



int main(int argc, char* argv[])
{  
    FILE *fp;
    int res;
    wchar_t *phrase = (wchar_t*)malloc(sizeof(wchar_t*)*BUFFER_LENGTH);  //Array to save the accepted suggested words
    setlocale(LC_ALL, ""); 
    clock_t begin = clock();
    fp = OpenDictionary(argv[1], "a+");

    HashTable KeysTable = InitializeKeysTable(KEYS_TABLE_SIZE);
    HashTable WordsTable = InitializeWordsTable(WORDS_TABLE_SIZE);

    //Read the dictionary, process word by word and insert them in WordsTable
    ProcessData(fp, KeysTable, WordsTable);

    //Stops the clock and calculate the loading dictionary time
    clock_t end = clock();
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("Loading Time: %f s\n\n", time_spent);

    PrintHashKeysTable(KeysTable);  //Printing keys to users know them

    //Pogram menu
    printf("** Escreva a sua mensagem **\n");
    while(true)
    {
        scanf("%d", &res);

        switch(res)
        {
            List L;
            Position P;
            char c;

            //Ask if user wants to leave the application
            case 0:
                printf("Deseja sair da aplicação (s/n)? ");
                scanf(" %c", &c);
            
                if ('s' == c)
                    exit(0);

                break;
            
            //Write the message and exit the progam
            case 1:
                printf("Mensagem: %ls", phrase);
                exit(1);
                break;
            
            //Users inserts a word
            default:
                L = WordsTable->TheLists[HashWords(res, WordsTable->TableSize)];
                P = L->Next;

                //Check ifthe word does not exists, 
                //user will type the word and it will be insert in the current message and the dictionary updated
                if(P == NULL)
                {
                    //wchar_t *wordToInsert[BUFFER_LENGTH];
                    wchar_t *wordToInsert = (wchar_t*)malloc(sizeof(wchar_t*)*BUFFER_LENGTH);
                    printf("Não existem mais sugestões; introduza a palavra do teclado\n");
                    scanf(" %ls", wordToInsert);

                     wchar_t *inserted = InsertWordInHashAndPhrase(fp, wordToInsert, phrase, WordsTable, KeysTable);
                    char cpFileName[BUFFER_LENGTH];
                    strcpy(cpFileName, argv[1]);
                    char *updatedFileName;
                    updatedFileName = strtok(cpFileName, ".");
                    strcat(updatedFileName, "-updated.txt");
                    UpdateDictionary(argv[1], updatedFileName, inserted);
                    //UpdateDictionary(argv[1], "../dict/output.txt", inserted);
                }
                else
                {
                    printf("Sugestão: %ls, aceita(s/n)? ", P->Element);
                    scanf(" %c", &c);

                    //If the user accepts the suggestion, the word is inserted to the message
                    if('s' == c)
                    {
                        wcscat(phrase, P->Element);
                        wcscat(phrase, L" ");
                    }

                    //If the user dont accept it, suggest the next one until he accepts one or insert if dont have more words to suggest
                    else if('n' == c)
                    {
                        while(P->Next!=NULL && 'n' == c)
                        {
                            P = P->Next;
                            printf("Sugestão: %ls, aceita(s/n)? ", P->Element);
                            scanf(" %c", &c);
                        }
                        wchar_t wordToInsert[BUFFER_LENGTH];
                        printf("Não existem mais sugestões; introduza a palavra do teclado\n");
                        scanf(" %ls", wordToInsert);

                        wchar_t *inserted = InsertWordInHashAndPhrase(fp, wordToInsert, phrase, WordsTable, KeysTable);
                        char cpFileName[BUFFER_LENGTH];
                        strcpy(cpFileName, argv[1]);
                        char *updatedFileName;
                        updatedFileName = strtok(cpFileName, ".");
                        strcat(updatedFileName, "-updated.txt");
                        UpdateDictionary(argv[1], updatedFileName, inserted);
                        free(inserted);
                        //UpdateDictionary(argv[1], "../dict/output.txt", inserted);
                    } 
                }
                break;
        }
    }
    //Close dictionary
    CloseDictionary(fp);
    return 0;
}
