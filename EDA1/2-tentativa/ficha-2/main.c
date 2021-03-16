#include <stdio.h>
#include <stdlib.h>
#include "stackar.c"


int main()
{
	Stack stack = CreateStack(5);

	printf("IsEmpty? : %d\n", IsEmpty(stack));

	Push(5, stack);
	printf("%d\n", stack->Array[0]);
	
	printf("IsEmpty? : %d\n", IsEmpty(stack));
	printf("Top Element : %d\n", Top(stack));

	Push(12, stack);
	Push(5938, stack);
	Push(511, stack);

	printf("isFull? : %d\n", IsFull(stack));
	printf("Top Element : %d\n", Top(stack));

	PrintStack(stack);

	printf("POP : %d\n", Pop(stack));

	PrintStack(stack);

	char *str = "(1+2)9()";
	
	printf("Check Parentesis Balance: %d\n", ParentesisMatch(str));

	return 0;
}