#include <stdio.h>



void troca(int *n1, int *n2)
{

  int aux = *n1;
  *n1 = *n2;
  *n2 = aux;

}
int main(void)
{
  int a, b;

  // lê dois valores inteiros
  printf("insira dois inteiros: "); scanf("%d %d", &a, &b);

  // mostra-os
  printf("antes da troca: a = %d, b = %d\n", a, b);

  troca(&a,&b);	// troca os valores das variáveis

  // mostra os valores depois da troca
  printf("depois da troca: a = %d, b = %d\n", a, b);

  return 0;
}