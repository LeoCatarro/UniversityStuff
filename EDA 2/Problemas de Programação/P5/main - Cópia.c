/*
##########################################################
		ESTADO DO P5:
		->Não lê 1ª linha do input(try to fix it);
		->Correção do count, caso o i_ponto seja outro
##########################################################
*/

#include <stdio.h>
//#include "list.h"

struct pares
{
	//int counter[20];
	int j_pt[20];
};


int exists_on_jList(int insert, int ponto, struct pares prs[ponto], int size)
{
	for(int i=0; i<size; i++)
	{
		if(prs[ponto].j_pt[i] == insert)
		{
			return 1;
		}	
	}
	return 0;	
}

int exists_on_iList(int i_of_point , int index, int list[index])
{
	for(int i=0 ; i<index ; i++)
	{
		if(list[i] == i_of_point)
		{
			return 1;
		}	
	}
	return 0;	
}


int main(void)
{
	char lineinput[20];
	int count=0;
	int i_ponto;
	int i_list[20];
	int index=0;

	struct pares prs[20];

		char operador;
		scanf("%c ", &operador);

		if(operador == 'p')
		{
			int be_inserted;
			scanf("%d", &i_ponto);
			scanf("%d", &be_inserted);
			
			if(exists_on_jList(be_inserted, i_ponto, prs, count) == 0)  //Verifica se o j do par (i,j) já consta na "lista" de j's --> Output: "1" caso esteja na lista , "0" caso contrario
			{	
				prs[i_ponto].j_pt[count] = be_inserted;
			}	
				
		}

		if(operador == 'q')
		{
			scanf("%d", &i_ponto);

			//PRINTS
			printf("%d %d", i_ponto, count);

			for(int i=0; i<count; i++)
			{
				printf(" %d", prs[i_ponto].j_pt[i]);
			}
			printf("\n");
		}

	while(fgets(lineinput, sizeof(lineinput), stdin))
	{
		char operador;
		scanf("%c ", &operador);

		if(operador == 'p')
		{
			int be_inserted;
			scanf("%d", &i_ponto);

			if(exists_on_iList(i_ponto, index, i_list) == 0)
			{
				i_list[index] = i_ponto;
				index++;
				count=0;
			}

			scanf("%d", &be_inserted);
			
			if(exists_on_jList(be_inserted, i_ponto, prs, count) == 0)  //Verifica se o j do par (i,j) já consta na "lista" de j's --> Output: "1" caso esteja na lista , "0" caso contrario
			{	
				prs[i_ponto].j_pt[count] = be_inserted;
				count++;
			}	
				
		}

		if(operador == 'x')
		{
			int be_deleted;
			scanf("%d", &i_ponto);
			scanf("%d", &be_deleted);

			for(int i=0 ; i<count ; i++)
			{
				if(prs[i_ponto].j_pt[i] == be_deleted)
				{
					for(int j=i ; j<count-1 ; j++)
					{
						prs[i_ponto].j_pt[j] = prs[i_ponto].j_pt[j+1];
					}	
				}	
			}
			count--;	
		}	
		
		if(operador == 'q')
		{
			scanf("%d", &i_ponto);

			//PRINTS
			printf("%d %d", i_ponto, count);

			for(int i=0; i<count; i++)
			{
				printf(" %d", prs[i_ponto].j_pt[i]);
			}
			printf("\n");
		}
	}	
	return 0;
}