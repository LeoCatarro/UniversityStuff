#include "List.h"
#include <stdlib.h>
#include "fatal.h"


struct Node
{
    ElementType element;
    struct Node* next;
};

struct List
{
    struct Node* head;
};


struct List *list_new(void)
{
    List *list = malloc(sizeof(struct List));

    if(list != NULL)
    {
        list->head = NULL;
    }

    return list;
}

static struct node* node_new(ElementType value, struct node* next)
{
    Node *node = malloc(sizeof(struct Node));

    if(node != NULL)
        node->element = value;
        node->next = next;

    return node;
}


bool list_insert(struct List *list, int value)
{
    
}
void list_print(struct List *list) 
{
    
}

void list_destroy(struct List *list)
{
    
}

void List_remove(struct List* list, int value)
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