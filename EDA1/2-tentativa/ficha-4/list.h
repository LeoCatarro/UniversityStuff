#include <stdbool.h>

typedef int ElementType;

#ifndef _List_H
#define _List_H

typedef struct Node
{
    ElementType element;
    struct Node* next;
}Node;

typedef struct List
{
    struct Node* head;
}List;

struct List *list_new(void);  
bool list_insert(List *list, int value); 
void list_print( List *list); 
void list_destroy(List *list); 


void list_remove(List* list, int value); 
int list_lenght(List* list); 
bool list_empty(List* list);  
int list_find(List* list, int value); 
int list_nth(List* list, int n); 

#endif
