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
	printf("\n# Top of the Stack #\n");
	
	for(int i=0 ; i<=S->TopOfStack ; i++)
		printf("%d\n", S->Array[i]);

	printf("# End Of Stack #\n\n");
}


int ParentesisMatch( char *s )
{	
	int leftCurve = 0;
	int rightCurve = 0;
	int leftRect = 0;
	int rightRect = 0;
	int leftBrace = 0;
	int rightBrace = 0;

	//Check '(' && ')' occurrences
	for(int i=0; s[i] != '\0' ; i++)
	{
		switch (s[i])
		{
		case '(':
			leftCurve++;
			break;

		case '[':
			rightCurve++;
			break;

		case '{':
			leftBrace++;
			break;

		case ')':
			rightCurve++;
			break;
		case ']':
			rightRect++;
			break;

		case '}':
			rightBrace++;
			break;

		default:
			break;
		}
	}

	return( (leftCurve == rightCurve && leftRect==rightRect && leftBrace==rightBrace) ? 1 : 0);
}