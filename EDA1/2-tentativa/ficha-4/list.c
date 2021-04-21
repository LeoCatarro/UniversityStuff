#include "list.h"
#include <stdlib.h>
#include "fatal.h"




struct List *list_new(void)
{
    List *list = malloc(sizeof(struct List));

    if(list != NULL)
    {
        list->head = NULL;
    }

    return list;
}

static struct Node* node_new(ElementType value, Node* next)
{
    Node *node = malloc(sizeof(struct Node));

    if(node != NULL)
    {
        node->element = value;
        node->next = next;
    }
    return node;
}


bool list_insert(List *list, ElementType value)
{
    Node *node = node_new(value, list->head);

    if(node == NULL)
        return false;

    list->head = node;
    return true;
}
void list_print(struct List *list) 
{
    Node *node = list->head;

    putchar('[');

    while(node != NULL)
    {
        printf("%d", node->element);

        if(node->next != NULL)
            printf(", ");

        node = node->next;  
    }
    putchar(']');  
}
/*
void list_destroy(struct List *list)
{
    
}

void list_remove(struct List* list, int value)
{
    
}

int list_lenght(struct List* list) 
{
    
}

bool list_empty(struct List* list) 
{
    
}

int list_find(struct List* list, int value)
{
    
}

int list_nth(struct List* list, int n)
{
    
}
*/

