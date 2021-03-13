#include <stdio.h>
#include <stdlib.h>
#include "list.h"

struct node
{
	int element;
	struct node *next;
};

struct list *list_new()
{
	struct node *head = malloc(sizeof(struct node));
	
};


bool list_insert(struct list *list, int value)
{

}


void list_print(list)
{

}


void list_destroy(list)
{

}