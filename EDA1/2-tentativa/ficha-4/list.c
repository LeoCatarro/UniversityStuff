#include <stdlib.h>
#include "list.h"
#include "fatal.h"


struct Node{
    ElementType Element;
    Position    Next;
};


List CreateList()
{
    List L = (List)malloc(sizeof(struct Node));
    
    return L;

}


List MakeEmpty( List L )
{
    Position p = Header(L);

    while(p != NULL)
    {
        p = p->Next;
        free(p);
    }
    return L;
}


bool IsEmpty( List L ){
    if(First(L) == NULL)
        return true;
    else
        return false;
}


bool IsLast( Position P, List L )
{
    if(P->Next == NULL)
        return true;
    
    return false;
}


Position Find( ElementType X, List L )
{
    Position node = Header(L);

    while(node != NULL)
    {
        if(node->Element == X)
            return node;
        
        node = node->Next;
    }

    return NULL;
}


Position FindPrevious( ElementType X, List L ) 
{
    Position node = Header(L);

    while(node != NULL)
    {
        Position nextNode = node->Next;
        if(nextNode->Element == X)
            return node;
        
        node = node->Next;
    }

    return NULL;
}


void Insert( ElementType X, List L, Position P ) 
{
    Position node = malloc(sizeof(struct Node));

    node->Element = X;
    node->Next = P->Next;
    P->Next = node;
    
}

void Delete( ElementType X, List L ){

    Position node = FindPrevious(X, L);

    //Like a soft delete, jumps over the node with X in Element
    node->Next= node->Next->Next;
}


void DeleteList( List L ) {
    free(L);
}

Position Header( List L ) 
{
    return L;
}


Position First( List L ) 
{
    return L->Next;
}


Position Last( List L )
{
    Position currNode = First(L);

    while(currNode != NULL)
    {
        if(currNode->Next == NULL)
            return currNode;
        else
            currNode = currNode->Next;
    }
    return NULL;
}


Position Advance( Position P ) 
{
    return P->Next;
}


ElementType Retrieve( Position P ) 
{
    return P->Element;
}


void PrintList(List L)
{
    Position node = First(L);

    putchar('[');

    while(node != NULL)
    {
        printf(" %d ", node->Element);
        node = node->Next;
    }

    printf("]\n");
}