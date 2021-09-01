//Source: https://stackoverflow.com/questions/35914574/sorting-linked-list-simplest-way

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <wchar.h>

#include "fatal.h"
#include "hashtable_words.h"


#define MAX_LINE_SIZE 233
#define MinTableSize (10)


/*
* Function: NextPrime
* -------------------
* calculates the next prime number of a given number
*
* N: Tablesize
*
* Returns: the next prime number
*/
static int NextPrime( int N ){
    int i;

    if( N % 2 == 0 )
        N++;
    for( ; ; N += 2 ){
        for( i = 3; i * i <= N; i += 2 )
            if( N % i == 0 )
                goto ContOuter;  /* Sorry about this! */
        return N;
      ContOuter: ;
    }
}


/*
* Function: HashWords
* -------------------
* hash the given Key 
*
* Key: word as an integer
* TableSize: hashtable size
*
* Returns: an index of hashtable when it will be inserted data after
*/
long HashWords(unsigned long Key, int TableSize )
{  
    return Key % TableSize;
}


/* 
* Function: Initialize the Table
* ------------------------------
* initialize the hashtable
* making the correspondent malloc() and allocate the array of lists and them Headers to use on HashTable positions 
*
* TableSize: size of the initialized hashtable
*
* Returns: hashtable
*/
HashTable InitializeWordsTable( int TableSize ){
    HashTable H;
    int i;

    if( TableSize < MinTableSize ){
        Error( "Table size too small" );
        return NULL;
    }

    /* Allocate table */
    H = malloc( sizeof( struct HashTbl ) );
    if( H == NULL )
        FatalError( "Out of space!!!" );

        H->TableSize = NextPrime( TableSize );

    /* Allocate array of lists */
    H->TheLists = malloc( sizeof( List ) * H->TableSize );
    if( H->TheLists == NULL )
        FatalError( "Out of space!!!" );

    /* Allocate list headers */
    for( i = 0; i < H->TableSize; i++ ){
        H->TheLists[ i ] = malloc( sizeof( struct ListNode ) );
        if( H->TheLists[ i ] == NULL )
            FatalError( "Out of space!!!" );
        else
            H->TheLists[ i ]->Next = NULL;
    }

    return H;
}


/*
* Function: FindWord
* ------------------
* finds a word inside an hashtable 
*
* Key: word to be finded
* wordInInt: integer Key equivalent(Key converted to T9Keys)
* H: hashtable to find Key
*
* Returns: the Position of the Key in hashtable or NULL if its hasnt finded
*/
Position FindWord(wchar_t *Key, long wordinInt, HashTable H )
{   
    List L = H->TheLists[HashWords( wordinInt, H->TableSize )];
    Position P = L->Next;

    while( P != NULL && wcscmp(P->Element, Key)!=0 )
    {
        P = P->Next;
    }
    return P;
}


/*
* Function: SortLinkedList
* ------------------------
* sort the linked list passed as arguments by descending order
*
* L: linked list to be sorted
*
* Returns: L sorted by descending order
*/
void SortLinkedList(List L)
    {
    Position node=L->Next, temp = NULL;
    long tempvar;   //temp variable to store node WordFreq
   
    while(node != NULL)
    {
        temp=node; 
        while (temp->Next !=NULL)   //travel till the second last element 
        {
           if(temp->WordFreq < temp->Next->WordFreq)    // compare the WordFreq of the nodes 
            {
              tempvar = temp->WordFreq;
              temp->WordFreq = temp->Next->WordFreq;    // swap the WordFreq
              temp->Next->WordFreq = tempvar;
            }
         temp = temp->Next;    // move to the next element 
        }
        node = node->Next;    // move to the next node
    }
}


/*
* Function: InsertWord
* -------------------
* inserts the give Key in hashtable H
*
* Key: word to be inserted
* wordInInt: integer Key equivalent(Key converted to T9Keys)
* H: hashtable where it will be inserted the Key
*/
void InsertWord(wchar_t *Key, long wordInInt, HashTable H )
{
    Position Pos, NewCell;
    List L;
    Pos = FindWord( Key, wordInInt, H );
    //Key is not found
    if( Pos == NULL ){  
        NewCell = malloc( sizeof( struct ListNode ) );
        NewCell->Element = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(Key));

        if( NewCell == NULL )
            FatalError( "Out of space!!!" );
        
        else{
            L = H->TheLists[ HashWords( wordInInt, H->TableSize ) ];
            NewCell->Next = L->Next;
            NewCell->Element = Key;
            L->Next = NewCell;
        }
    }

    //Key is found in HashTable
    else
    {   
        //If the word is already in the HashTable, we dont want to repeat it
        return;
    }
}


/*
* Function: InserWordAccordingFrequency
* -------------------
* inserts the give Key in hashtable H according it usage frequency with SortLinkedList function help
*
* Key: word to be inserted
* wordFreq: Key usage frequency
* wordInInt: integer Key equivalent(Key converted to T9Keys)
* H: hashtable where it will be inserted the Key
*/
void InsertWordAccordingFrequency(wchar_t * Key, long wordFreq, long wordInInt, HashTable H )
{
    Position Pos, NewCell;
    List L;
    Pos = FindWord( Key, wordInInt, H );

    //Key is not found
    if( Pos == NULL ){  
        NewCell = malloc( sizeof( struct ListNode ) );
        NewCell->Element = (wchar_t*)malloc(sizeof(wchar_t*)*wcslen(Key));

        if( NewCell == NULL )
            FatalError( "Out of space!!!" );
           
        else{
            L = H->TheLists[ HashWords( wordInInt, H->TableSize ) ];
            NewCell->Next = L->Next;
            NewCell->Element = Key;
            NewCell->WordFreq = wordFreq;
            L->Next = NewCell;

            SortLinkedList(L);
        }
    }

    //Key is found in HashTable
    else
    {   
        //If the word is already in the HashTable, we dont want to repeat it
        return;
    }
}


/*
* Function: RetrieveWord
* -------------------
* print a word
*
* P: position of the word to be printed
*
* Returns: the word
*/
wchar_t* RetrieveWord( Position P ){
    return P->Element;
}


/*
* Function: DestroyWordsTable
* ---------------------------
* free the ram occupied from hashtable H
*
* H: hashtable
*/
void DestroyWordsTable( HashTable H ){
    int i;

    for( i = 0; i < H->TableSize; i++ )
    {
        Position P = H->TheLists[ i ];
        Position Tmp;

        while( P != NULL )
        {
            Tmp = P->Next;
            free( P );
            P = Tmp;
        }
    }

    free( H->TheLists );
    free( H );
}


/*
* Function: DeleteWord
* -------------------
* removes the give word from HashTable
*
* X: word to be inserted
* wordInInt: integer Key equivalent(Key converted to T9Keys)
* H: hashtable where it will be inserted the Key
*
* Returns: the updated hashtable
*/
HashTable DeleteWord(wchar_t * X, long wordInInt, HashTable T ){
    
    // Find the key of the Element X
    unsigned long key = HashWords(wordInInt, T->TableSize);

    //Key finded
    if(key != -1)
    {
        //If the key is finded we need to iterate over the List of that Key Hashtable Position to find the X
        Position prevP = T->TheLists[key];
        Position P = T->TheLists[key]->Next;

        //X finded 
        while(P != NULL)
        {
            if(wcscmp(P->Element, X) == 0 && P->Next != NULL)
            {   
                printf("Deleted [%ls] at index %ld from HashTable\n", P->Element, key);
                prevP->Next = P->Next->Next;
                return T;
            }

            else if(wcscmp(P->Element, X) && P->Next == NULL)
            {
                prevP->Next = NULL;
                printf("Deleted [%ls] at index %ld from HashTable\n", P->Element, key);
                return T;
            }

            prevP = P;
            P = P->Next;       
        }
    }

    //Key not finded 
    else
        FatalError("Element not present in HashTable");
    
    return T;
}


/*
* Function: PrintHashWordsTable
* -------------------
* show the hashtable and it content in terminal
*
* H: hashtable where it will be inserted the Key
*/
void PrintHashWordsTable(HashTable T)
{
    printf("* Printing HashTable *\n");

    for(int i=0 ; i < T->TableSize ; i++)
    {   
        Position P = T->TheLists[i]->Next;

        if(P != NULL)
        {
            printf("%d\t[", i);
            while(P != NULL)
            {
                P->Element[wcslen(P->Element)+1] = 0;
                printf("%ls", P->Element);
                printf(" %ld", wcslen(P->Element));
                printf(" %ld", P->WordFreq);
                P = P->Next;

                //If is not the last element
                if(P != NULL)
                    printf(", ");
            }
            printf("]\n");
        }
        /*else
            printf("%d\t[%s]\n", i, "--");*/
    }
}