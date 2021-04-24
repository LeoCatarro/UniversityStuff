/*
##########################################################
		ESTADO DO P5:
		->Não lê 1ª linha do input(try to fix it);
		->Correção do count, caso o i_ponto seja outro
##########################################################
*/

#include <stdio.h>
#include "list.h"


int main(void)
{
	struct list *l = list_new();
	list_insert(l, 2);
	list_print(l);

	printf("%d\n", l->head);
	return 0;
}