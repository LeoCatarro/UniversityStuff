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
/*
List MakeEmpty( List L )
{
    L=(List)malloc(sizeof(struct Node));
    //L->Element = NULL;
    L->Next = NULL;
    return L;
}*/

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
/*
void Delete( ElementType X, List L ){
}


void DeleteList( List L ) {
}
*/

Position Header( List L ) 
{
    return L;
}


Position First( List L ) 
{
    return L->Next;
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




int main()
{
    
    List l = CreateList();

    if(IsEmpty(l))
        printf("LIST IS EMPTY\n");

    Insert(3, l, Header(l));

    if(!IsEmpty(l))
        printf("LIST IS NOT EMPTY\n");

    IsEmpty(l);

    Insert(7, l, Header(l));
    Insert(235, l, Header(l));

    PrintList(l);

    Position pos = Find(3, l);
    printf("%d\n", pos->Element);

    pos = FindPrevious(235, l);
    printf("%d\n", pos->Element);

    return 0;
}