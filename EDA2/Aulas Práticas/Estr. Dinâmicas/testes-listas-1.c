#include <stdio.h>

#include "list.h"

#define VALORES 10

int main(void)
{
  <TIPO_DE_UMA_LISTA> l;

  // cria lista
  printf("+ cria lista\n");

  l = list_new();

  // testa list_print() de uma lista vazia
  printf("elementos da lista (vazia): "); list_print(l); printf("\n");

  // testa list_insert()
  printf("+ insere valores:"); fflush(stdout);

  for (int i = 1; i <= VALORES; ++i)
    {
      printf(" %d", i); fflush(stdout);

      if (!list_insert(l, i))
	{
	  printf("\nERRO: problema ao inserir %d\n", i);

	  return 1;
	}
    }

  printf("\n");

  // testa list_print() de uma lista com elementos
  printf("elementos da lista: "); list_print(l); printf("\n");

  // apaga a lista
  list_destroy(l);

  printf("+ bye\n");

  return 0;
}
