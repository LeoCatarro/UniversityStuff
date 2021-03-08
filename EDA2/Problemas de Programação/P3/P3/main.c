#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#include "pilha.h"

void notacao(char cod[])
{

	struct stack *pilha = stackNew(500); 
  int n1, n2;
  int aux = 0; //Auxiliar para o print

	for(int i=0 ; cod[i] != '\0' ;  i++)
    { 
      //OPERANDOS (caso cod[i] seja um algarismo)	
      if(cod[i] == '1' || cod[i] == '2' || cod[i] == '3' || cod[i] == '4' || cod[i] == '5' || cod[i] == '6' || cod[i] == '7' || cod[i] == '8' || cod[i] == '9')
      {
        push(pilha, cod[i] - '0'); 
      }
      
      //OPERADORES
      else
      { 
        //OPERADOR +
        if(cod[i] == '+')
        {
          n2 = pop(pilha);
          n1 = pop(pilha);

          push(pilha, n1 + n2);
        }

        //OPERADOR -
        if(cod[i] == '-')
        {
          n2 = pop(pilha);
          n1 = pop(pilha);

          push(pilha, n1 - n2);
        }
        
        //OPERADOR *
        if(cod[i] == '*')
        {
          n2 = pop(pilha);
          n1 = pop(pilha);

          push(pilha, n1 * n2);
        }

        //OPERADOR /
        if(cod[i] == '/')
        {
          n2 = pop(pilha);

          if(n2 == 0)
          {
            aux = 1;
            break;
          }
          n1 = pop(pilha);

          push(pilha, n1 / n2);
        }

        //OPERADOR ~
  		  if(cod[i] == '~')
        {
          push(pilha, -pop(pilha));
        }
      }  		
    }

    if(aux == 0)
    {  
      printf("%d\n", pop(pilha));
    }  
    if(aux == 1)
    {  
      printf("divisao por 0\n"); 
    }   	   
}


int main(void)
{
  char cod[1001]; //1000 elementos possíveis + '\0'(indicativo do fim da string)

  while(scanf("%s", cod) != EOF)
  {
    notacao(cod);
  }
  return 0;
}
