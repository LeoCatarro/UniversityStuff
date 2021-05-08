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

    Delete(7, l);

    PrintList(l);

    //Insert at the beggining of the list
    Insert(2000, l, Header(l));

    //Insert next to a specific node
    Insert(3000, l, Find(235, l));

    //Insert at the final of the list
    Insert(1000, l, Last(l));

    PrintList(l);

    return 0;
}