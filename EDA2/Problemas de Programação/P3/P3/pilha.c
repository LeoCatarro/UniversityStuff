#include <stdio.h>
#include <stdlib.h>

#include "pilha.h"

//IMPLEMENTAÇÃO DA PILHA

struct stack
{
	int size;
	int top;
	int* elementos;
};

struct stack* stackNew(int size)
{
	struct stack* pilha = malloc(sizeof(struct stack));
	pilha -> size = size;
	pilha -> top = -1 ;
	pilha -> elementos = malloc(size * sizeof(int));

	return pilha;
}

bool isEmpty(struct stack *pilha)
{
	if(pilha -> top == -1)
	{	
		return true;
	}
	else
	{	
		return false;
	}	
}

void push(struct stack *pilha, int element)
{		
	pilha -> elementos[++pilha -> top] = element;
}

int pop(struct stack *pilha)
{		
	if(pilha -> top != -1)
	{	
		return pilha->elementos[pilha->top--];	
	}
	return -1;
}

int top(struct stack *pilha)
{
	if(pilha -> top != -1)
	{	
		return pilha->elementos[pilha->top];	
	}
	return -1;	
}
