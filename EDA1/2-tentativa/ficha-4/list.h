#include <stdbool.h>

typedef int ElementType;

#ifndef _List_H
#define _List_H

struct Node;
typedef struct Node *PtrToNode;
typedef PtrToNode List;
typedef PtrToNode Position;

List CreateList();
List MakeEmpty( List L );
bool IsEmpty( List L );
bool IsLast( Position P, List L );

Position Find( ElementType X, List L );
Position FindPrevious( ElementType X, List L );

void Insert( ElementType X, List L, Position P );
void Delete( ElementType X, List L );
void DeleteList( List L );

Position Header( List L );
Position First( List L );
Position Advance( Position P );
ElementType Retrieve( Position P );
void PrintList(List L);

#endif
