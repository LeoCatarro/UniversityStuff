#include "fatal.h"
#include "hashsep.h"
#include <stdio.h>
#include <stdlib.h>


#define MinTableSize (10)
typedef unsigned int Index;
typedef Position List;


struct ListNode{
    ElementType Element;
    Position    Next;
};


struct HashTbl{
    int TableSize;
    List *TheLists;
};


Position FindInList(ElementType X, List L)
{
    Position node = L->Next;

    while(node != NULL)
    {
        if(node->Element == X)
            return node;
        
        node = node->Next;
    }

    return NULL;
}


void DeleteFromList( ElementType X, List L ){

    Position node = L;

    //Like a soft delete, jumps over the node with X in Element
    node->Next= node->Next->Next;
}





/* Return next prime; assume N >= MinTableSize */
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


/* Hash function for ints */
Index Hash( ElementType Key, int TableSize ){
    return Key % TableSize;
}

/* Initialize the Table, 
making the correspondent malloc() and allocate the array of lists and them Headers 
to use on HashTable positions */
HashTable InitializeTable( int TableSize ){
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


/* Find a Key in HashTable */
Position Find( ElementType Key, HashTable H )
{
    List L = H->TheLists[Hash( Key, H->TableSize )];
    Position P = L->Next;

    while( P != NULL && P->Element != Key )
        /* Probably need strcmp!! */
        P = P->Next;
    
    return P;
}

/* Insert the Element Key passed as argument in HashTable H */
void Insert( ElementType Key, HashTable H ){
    Position Pos, NewCell;
    List L;

    Pos = Find( Key, H );

    /* Key is not found */
    if( Pos == NULL ){  
        NewCell = malloc( sizeof( struct ListNode ) );

        if( NewCell == NULL )
            FatalError( "Out of space!!!" );
        
        else{
            L = H->TheLists[ Hash( Key, H->TableSize ) ];
            NewCell->Next = L->Next;
            NewCell->Element = Key;  /* Probably need strcpy! */
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

/* Print the Element in Node P */
ElementType Retrieve( Position P ){
    return P->Element;
}

/* Free the ram occupied from HashTable */
void DestroyTable( HashTable H ){
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

/* Removes the Element X from the HashTable */
HashTable Delete( ElementType X, HashTable T ){
    
    // Find the key of the Element X
    int key = Hash(X, T->TableSize);
    printf("%d\n", key);

    //Key finded
    if(key != -1)
    {
        //If the key is finded we need to iterate over the List of that Key Hashtable Position to find the X
        Position prevP = T->TheLists[key];
        Position P = T->TheLists[key]->Next;

        //X finded 
        while(P != NULL)
        {
            if(P->Element == X && P->Next != NULL)
            {   
                printf("Deleted %d at index %d from HashTable\n", P->Element, key);
                prevP->Next = P->Next->Next;
                return T;
            }

            else if(P->Element == X && P->Next == NULL)
            {
                prevP->Next = NULL;
                printf("Deleted %d at index %d from HashTable\n", P->Element, key);
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
HashTable MakeEmpty( HashTable T ){
}
*/

/* Display All HashTable in Terminal */
void PrintHashTable(HashTable T)
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
                printf("%d", P->Element);
                P = P->Next;

                //If is not the last element
                if(P != NULL)
                    printf(", ");
            }
            printf("]\n");
        }
        else
            printf("%d\t[%s]\n", i, "--");
    }
}


int main()
{
    HashTable H = InitializeTable(10);
    printf("TABLE SIZE: %d\n", H->TableSize);

    PrintHashTable(H);

    Insert(20, H);
    Insert(1, H);
    Insert(123432150, H);
    PrintHashTable(H);

    Insert(100, H);

    PrintHashTable(H);

    Insert(1000000, H);

    PrintHashTable(H);

    Delete(1, H);

    PrintHashTable(H);

    Delete(1000000, H);
    Delete(100, H);

    PrintHashTable(H);

    return 0;
}