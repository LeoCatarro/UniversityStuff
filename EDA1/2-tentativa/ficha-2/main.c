#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "stackar.c"


bool Palindrome(char *txt)
{

	printf("%ld\n", strlen(txt));

	Stack A = CreateStack(strlen(txt));
	Stack B = CreateStack(strlen(txt));

	for(int i=0 ; i<strlen(txt); i++)
	{
		Push(txt[i], A);
		Push(txt[strlen(txt)-i-1], B);
	}

	while(!IsEmpty(A))
	{
		if(Pop(A) != Pop(B))
			return false;
	}
	return true;
}


int main()
{
	
	char *txt = "socorrammesubinoonibusemmarrocos";
	//char *txt = "abcdef";
	
	printf("%d\n", Palindrome(txt));


	//Stack stack = CreateStack(10);
/*
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
*/
	//char *str = "(1+2)9({}";
	
	//printf("Check Parentesis Balance: %d\n", ParentesisMatch(str));
	//printf("Check Parentesis Balance: %d\n", ParentesisMatchUsingStack(str));

	return 0;
}