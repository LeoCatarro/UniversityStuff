#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <wchar.h>
#include <wctype.h>

#include "fatal.h"
#include "hashtable_keys.h"

#define MinTableSize (10)
typedef unsigned int Index;
typedef Position List;


struct ListNode{
    wchar_t Element;
    Position Next;
};


struct HashTbl{
    int TableSize;
    List *TheLists;
};


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
* Function: HashKeys
* -------------------
* hash the given Key 
*
* Key: T9Key
* TableSize: hashtable size
*
* Returns: an index of hashtable when it will be inserted the characters that T9Key represents
*/
Index HashKeys( wchar_t Key, int TableSize ){
    return Key % TableSize;
}


/* 
* Function: InitializeKeysTable
* -----------------------------
* initialize the hashtable
* making the correspondent malloc() and allocate the array of lists and them Headers to use on HashTable positions 
*
* TableSize: size of the initialized hashtable
*
* Returns: hashtable
*/
HashTable InitializeKeysTable( int TableSize ){
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
* Function: FindKey
* -----------------
* finds a key inside an hashtable 
*
* Key: key to be finded
* H: hashtable to find Key
*
* Returns: index of the Key in Hashtable H
*/
int FindKey( wchar_t Key, HashTable H )
{
    for(int i=0 ; i < H->TableSize ; i++)
    {   
        Position P = H->TheLists[i]->Next;

        while(P != NULL)
        {
            if(P->Element == Key)
                return i;
            else
                P = P->Next;
        }
    }
    return -1;
}


/*
* Function: FindNthKey
* --------------------
*  Find the key in the given index 
*
* Key: key to be finded
* Index: index of hashtable
* H: hashtable to find Key
*
* Returns: the Position of Key in Hashtable H
*/
Position FindNthKey(wchar_t Key, int Index, HashTable H )
{
    List L = H->TheLists[Index];
    Position P = L->Next;

    while( P != NULL && P->Element != Key)
        P = P->Next;
    
    return P;
}


/*
* Function: InserNthKey
* ---------------------
* inserts the give Key in the give Index on hashtable H
*
* Key: word to be inserted
* Index: index to insert the Key
* H: hashtable where it will be inserted the Key
*/
void InsertNthKey(wchar_t Key, int Index, HashTable H ){
    Position Pos, NewCell;
    List L;

    Pos = FindNthKey(Key, Index, H);

    /* Key is not found */
    if( Pos == NULL ){  
        NewCell = malloc(sizeof(struct ListNode ));

        if( NewCell == NULL )
            FatalError( "Out of space!!!" );
        
        else{
            L = H->TheLists[Index];
            NewCell->Next = L->Next;
            NewCell->Element = Key;
            L->Next = NewCell;
        }
    }

    /* Key is found in HashTable */
    else
    {   
        //If the key is found in HT, we need to create another node 
        //to insert the element inside the list of the current hashtable position
        NewCell = malloc( sizeof( struct ListNode ) );
        Pos->Next = NewCell;
        NewCell->Element = Key;
        NewCell->Next = NULL;
    }
}


/*
* Function: RetrieveKey
* ---------------------
* print a key
*
* P: position of the key to be printed
*
* Returns: the key
*/
wchar_t RetrieveKey( Position P ){
    return P->Element;
}


/*
* Function: DestroyKeysTable
* --------------------------
* free the ram occupied from hashtable H
*
* H: hashtable
*/
void DestroyKeysTable( HashTable H ){
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
* Function: PrintHashKeysTable
* ----------------------------
* show the hashtable and it content in terminal
*
* T: hashtable of keys
*/
void PrintHashKeysTable(HashTable T)
{
    printf("* Printing Keys HashTable *\n");

    for(int i=0 ; i < T->TableSize ; i++)
    {   
        Position P = T->TheLists[i]->Next;

        if(P != NULL)
        {
            printf("%d:\t", i);
            while(P != NULL)
            {
                printf("%lc ", P->Element);
                P = P->Next;
            }
            printf("\n");
        }
    }
    printf("\n");
}


/*
* Function: InsertT9Keys
* ----------------------
* inserts the T9 Keys and the characters for each key in hashtable
*
* T: Hashtable to save the T9Keys
*/
void InsertT9Keys(HashTable T)
{
    //Key 2
    InsertNthKey(L'ç', 2, T);
    InsertNthKey(L'ã', 2, T);
    InsertNthKey(L'â', 2, T);
    InsertNthKey(L'à', 2, T);
    InsertNthKey(L'á', 2, T);
    InsertNthKey(L'c', 2, T);
    InsertNthKey(L'b', 2, T);
    InsertNthKey(L'a', 2, T);

    //Key 3
    InsertNthKey(L'ê', 3, T);
    InsertNthKey(L'é', 3, T);
    InsertNthKey(L'f', 3, T);
    InsertNthKey(L'e', 3, T);
    InsertNthKey(L'd', 3, T);

    //Key 4
    InsertNthKey(L'í', 4, T);
    InsertNthKey(L'i', 4, T);
    InsertNthKey(L'h', 4, T);
    InsertNthKey(L'g', 4, T);

    //Key 5
    InsertNthKey(L'l', 5, T);
    InsertNthKey(L'k', 5, T);
    InsertNthKey(L'j', 5, T);

    //Key 6
    InsertNthKey(L'õ', 6, T);
    InsertNthKey(L'ô', 6, T);
    InsertNthKey(L'ó', 6, T);
    InsertNthKey(L'o', 6, T);
    InsertNthKey(L'n', 6, T);
    InsertNthKey(L'm', 6, T);

    //Key 7
     
    InsertNthKey(L's', 7, T);
    InsertNthKey(L'r', 7, T);
    InsertNthKey(L'q', 7, T);
    InsertNthKey(L'p', 7, T);

    //Key 8
    InsertNthKey(L'ú', 8, T);
    InsertNthKey(L'v', 8, T);
    InsertNthKey(L'u', 8, T);
    InsertNthKey(L't', 8, T);
    
    //Key 9
    InsertNthKey(L'z', 9, T);
    InsertNthKey(L'y', 9, T);
    InsertNthKey(L'x', 9, T);
    InsertNthKey(L'w', 9, T);
    
    
    
}


/*
* Function: StringToIntAccordingT9Keys
* ------------------------------------
* converts word to the corresponding sequence of integers(t9-keys)
*
* word: word to be converted
* KeysTable: Hashtable to save the T9Keys
*
* Returns: converted words to T9Keys
*/
unsigned long StringToIntAccordingT9Keys(wchar_t *word, HashTable KeysTable)
{
    unsigned long result = 0;

    for(int i=0; i<wcslen(word); i++)
    {
        int index = FindKey(word[i], KeysTable);
        result += index*(pow(10, wcslen(word)-i-1));
    }
    return result;
}