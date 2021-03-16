#include "stackar.h"
#include "fatal.h"
#include <stdlib.h>


#define EmptyTOS ( -1 )
#define MinStackSize ( 5 )


struct StackRecord
{
    int Capacity;
    int TopOfStack;
    ElementType *Array;
};



Stack CreateStack( int MaxElements )
{
    Stack S;	

	if( MaxElements < MinStackSize )
		Error( "Stack size is too small" );

	S = malloc( sizeof( struct StackRecord ) );
	if( S == NULL )
		FatalError( "Out of space!!!" );

	S->Array = malloc( sizeof( ElementType ) * MaxElements );
	if( S->Array == NULL )
		FatalError( "Out of space!!!" );

	S->Capacity = MaxElements;
	S->TopOfStack = EmptyTOS;
	MakeEmpty( S );

	return S;
}



void DisposeStack( Stack S )
{
    if( S != NULL )
    {
        free( S->Array );
        free( S );
    }
}


int IsEmpty( Stack S )
{
	return(S->TopOfStack==EmptyTOS ? 1 : 0);
}


int IsFull( Stack S )
{
	return(S->TopOfStack == S->Capacity ? 1 : 0);
}


void MakeEmpty( Stack S )
{
	free(S->Array);
}


void Push( ElementType X, Stack S )
{
	if(IsFull(S) != 1)
	{
		S->TopOfStack++;
		S->Array[S->TopOfStack] = X;
	}
	else
		printf("[Error] : Stack is Full!");
}


ElementType Top( Stack S )
{
	return S->Array[S->TopOfStack];
}


ElementType Pop( Stack S )
{
	ElementType element = Top(S);
	S->TopOfStack--;

	return element;
}

void PrintStack(Stack S)
{
	//int Stack
	printf("\n# Current Stack #\n");
	
	for(int i=0 ; i<=S->TopOfStack ; i++)
		printf("%d\n", S->Array[i]);

	printf("# End Of Stack #\n");
}



int main()
{
	Stack stack = CreateStack(5);

	printf("IsEmpty? : %d\n", IsEmpty(stack));

	Push(5, stack);
	printf("%d\n", stack->Array[0]);
	
	printf("IsEmpty? : %d\n", IsEmpty(stack));
	printf("Top Element : %d\n", Top(stack));

	Push(12, stack);
	printf("%d\n", stack->Array[0]);
	printf("%d\n", stack->Array[1]);
	Push(5938, stack);
	Push(511, stack);

	printf("isFull? : %d\n", IsFull(stack));
	printf("Top Element : %d\n", Top(stack));

	PrintStack(stack);

	printf("POP : %d\n", Pop(stack));

	PrintStack(stack);

	return 0;
}