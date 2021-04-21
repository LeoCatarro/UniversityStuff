#include <stdlib.h>
#include "list.h"

int main()
{
    List *list = list_new();

    list_insert(list, 7);
    list_insert(list, 3);
    list_insert(list, 235);

    list_print(list);

    return 0;
}