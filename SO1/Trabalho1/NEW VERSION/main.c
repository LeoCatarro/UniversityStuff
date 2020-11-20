/*######################################################################################################
					ESTADO DO TRABALHO:
					-->LÊ O INPUT ATÉ 10 PROCESSOS
					-->ORGANIZA OS DADOS NUMA MATRIZ[][], ONDE CADA LINHA CORRESPONDE A UM PROCESSO
					-->CÓDIGO ORGANIZADO POR FUNÇÕES, PARA CLARIFICAR A PERCEPÇÃO DO MESMO

					O QUE FALTA:
					-->TENTAR TIRAR A matrix, l, c DE VARS GLOBAIS
					-->ALGORITMOS DE FCFS E RR PARA N PROCESSOS
########################################################################################################*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 4
#define MAX_CPU_IO 11  //Tamanho máximo do Array que guarda os PID's , t_inicio , t_cpu e t_io de cada processo
#define MAX 128  //Tamanho maximo de cada linha de input
#define L 10  //Cada linha corresponde a um processo, então max(L) = 10
#define C 11 //Cada processo pode ter no máximo 5CPU + 4IO + 1PID + 1t_inicio = 11 elementos

int matrix[L][C];
int l=0;
int c=0;

void save_in_arr(int tamanho,int intArray[tamanho])
{
	for(int i=0; i< MAX_CPU_IO ; i++)
	{
		if(i<tamanho-1)
		{
			matrix[l][i] = intArray[i];
			//printf("%d ", matrix[l][i]);  //Teste para printar os elementos na linha l e todas as colunas dessa linha
		}
		else
		{
			matrix[l][i] = -1;
			//printf("%d ", matrix[l][i]);  //Teste para printar os elementos na linha l e todas as colunas dessa linha
		}	
	}
	l++;	
}

void strline_to_int(int size, char cpuio[size])
{	
	int intArray[MAX_CPU_IO];
	int ipos=0; 
	int count=0;
				
	char *tok1 = strtok(cpuio, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      
																																	  
		while (tok1)  //Continua pela String até charArray terminar																		
		{																													          																															  
    		if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											
    		{																														  
        		intArray[ipos++] = atoi(tok1);  //Coverte para int e guarda-o no intArray
   			}																														  
    		tok1 = strtok(NULL," ");  //Vai buscar o proximo elemento da String  
    		count++;												 
		}
		save_in_arr(count, intArray);
/*
		TESTE PARA PRINT O ARRAY JA CONVERTIDO		
*/

/*		
		int i=0;

		while(i<count)
		{
			printf("%d ", p1[i]);
			i++;
		}
		printf("\n");
		printf("%d ", count);  //Size of the line intArray
*/			
}






int main()
{
	char input[MAX];
	int linecount=0;

	while(linecount<N)
	{
		fgets(input, MAX, stdin);
		int line_size = strlen(input);  //Size of line including " " and '\0';

		strline_to_int(line_size, input);
		printf("\n");
		linecount++;
	}	

	return 0;
}	
	