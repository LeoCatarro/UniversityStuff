
#include <stdio.h>
#include "colorSquares.h"





int main(int argc, char **argv)
{
	FILE *fp = fopen("text", "r");
	int tamanho;
	
	fscanf(fp, "%d", &tamanho);
	
	printf("%d\n", tamanho);
	
	int tabuleiro[20][20];
	
	int i, j;
	for(i=0; i < tamanho;i++)
	{
		for(j=0; j< tamanho; j++)
		{
			fscanf(fp, "%1d", &tabuleiro[i][j]);
			
			printf("%1d", tabuleiro[i][j]);
		}
		printf("\n");
	}
		
		int numero_jogadas;
		
		fscanf(fp, "%d", &numero_jogadas);
		
		printf("%d\n", numero_jogadas);
		
		
  
	
	
	return 0;
}

