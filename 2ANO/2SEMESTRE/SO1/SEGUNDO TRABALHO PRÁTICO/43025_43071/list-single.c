#include <stdio.h>
#include <stdlib.h>
#include "queue.h"

struct list *list_new()
{
    struct list *list = malloc(sizeof(struct list));

    if (list != NULL)
    {
        list->size = 0;
        list->first = NULL;
    }

    return list;
}

struct node *node_new(struct list *list, struct Process *p)
{
    struct node *node = malloc(sizeof(struct node));

    if (node != NULL)
    {
        node->p = p;
        node->next = list->first;
    }

    return node;
}

struct Process *list_insert(struct list *list, struct Process *p)
{
    struct node *node = node_new(list, p);

    if (node != NULL)
    {
        list->first = node;
        list->size++;

        return node->p;
    }

    return node->p;
}

void list_print(struct list *list)
{
    struct node *node = list->first;

    if (node != NULL)
    {
        printf("P%d ", getPid(node->p));
        node = node->next;
    }

    for (int i = 1; i < list->size - 1; i++)
    {
        if (node == NULL)
        {
            return;
        }
        
        printf("P%d ", getPid(node->p));
        node = node->next;
    }
        if(node != NULL)
            printf("P%d", getPid(node->p));
}

struct list *list_destroy(struct list *list)
{
    struct node *node = list->first;

    while (node != NULL)
    {
        struct node *next = node->next;

        free(node);

        node = next;
    }

    free(list);

    return list;
}

bool list_empty(struct list *list)
{
    return list->first == NULL;
}

bool list_find(struct list *list, struct Process *p)
{
    struct node *node = list->first;

    while (node != NULL)
    {
        if (getPid(node->p) == getPid(p))
        {
            return true;
        }

        node = node->next;
    }

    return false;
}

int list_length(struct list *list)
{
    return list->size;
}

bool list_remove(struct list *list, struct Process *p)
{
    struct node *node = list->first;
    struct node *prev;

    if (getPid(list->first->p ) == getPid(p))
    {
        list->first = node->next;

        list->size--;

        free(node);

        return true;
    }

    for (int i = 0; i < list->size; i++)
    {
        if (getPid(node->p) == getPid(p))
        {
            prev->next = node->next;

            list->size--;
            free(node);

            return true;
        }

        prev = node;
        node = node->next;
    }

    return false;
}

struct Process *nodeProcess(struct node *node)
{
    return node->p;
}

struct node *first(struct list *blocked)
{
    return blocked->first;
}

struct node *next(struct node *node)
{
    node = node->next;

    return node;
}