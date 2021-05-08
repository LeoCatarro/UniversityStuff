#include <stdio.h>
#include "list.c"

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

    Insert(500, l, Header(l));

    PrintList(l);

    return 0;
}