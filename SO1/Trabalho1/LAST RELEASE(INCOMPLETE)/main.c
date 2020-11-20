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

#define N 4  //Numero de processos
#define MAX_CPU_IO 11  //Tamanho máximo do Array que guarda os PID's , t_inicio , t_cpu e t_io de cada processo
#define MAX 128  //Tamanho maximo de cada linha de input
#define C 11 //Cada processo pode ter no máximo 5CPU + 4IO + 1PID + 1t_inicio = 11 elementos


int matrix[N][C];  //Matriz para guardar os valores de cada processos. Um processo por linha
int l=0;
int c=0;

int WAITING[4];
int W_index=0;

void save_in_arr(int tamanho,int intArray[tamanho])  //Guarda o intArray numa linha de matrix, fazendo 1 linha para cada processo
{
	for(int i=0; i< MAX_CPU_IO ; i++)
	{
		if(i<tamanho)
		{
			matrix[l][i] = intArray[i];
			//printf("%d ", matrix[l][i]);  
		}
		else
		{
			matrix[l][i] = -1;
			//printf("%d ", matrix[l][i]);
		}	
	}
	//printf("\n");
	l++;	
}

void strline_to_int(int size, char inputLine[size])
{	
	int intArray[MAX_CPU_IO];  //Array para guardar os valores da inputLine num intArray
	int ipos=0; 
	int count=0;
				
	char *tok1 = strtok(inputLine, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      
																																	  
		while (tok1)  //Continua pela String até charArray terminar																		
		{																													          																															  
    		if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											
    		{																														  
        		intArray[ipos++] = atoi(tok1);  //Coverte para int e guarda-o no intArray
   			}																														  
    		tok1 = strtok(NULL," ");  //Vai buscar o proximo elemento da String  
    		count++;												 
		}
	save_in_arr(count, intArray);  //Chama a função para guardar os valores numa matriz[][], na linha l	
}

/***************************************************************
		ALGORITMOS PARA O ESCALONAMENTO DE PROCESSOS: FCFS & RR
****************************************************************/

void get_arrival_time(int instante, int line, int column)
{
	WAITING[W_index] = instante + matrix[line][column+1] -1;
	W_index++;
}




void FCFS(int matrix[N][C])
{
	int instante=0;
	int RUN[1];
	//int READY[3];
	//int R_index=0;
	int BLOCKED[3];
	int B_index=0;
	int line=0;
	int column=2;

	int i=0;


	int run_now = matrix[line][column];
	int block_now;

	//Primeira execução no RUN de todos os processos	
	while(line<4)
	{
		if(run_now > 0)
		{	
			if(block_now > 0)
			{
				if(B_index < 2)
				{	
					RUN[0] = matrix[line][0];
					printf("%d\t", instante); printf("RUN: %d\t", RUN[0]); printf("BLOCKED: "); while(i<B_index){ printf("%d\t", BLOCKED[i]); i++;}
					printf("\n");
					block_now--;
					run_now--;
					instante++;
				i=0;
				}

				if(B_index == 2)
				{	
					BLOCKED[B_index-2] = 200;
					i=0;	
					RUN[0] = matrix[line][0];
					printf("%d\t", instante); printf("RUN: %d\t", RUN[0]); printf("BLOCKED: "); while(i<B_index){ printf("%d\t", BLOCKED[i]); i++;}
					printf("\n");
					block_now--;
					run_now--;
					instante++;
					
				}
			}

			else
			{	
				i=0;	
				RUN[0] = matrix[line][0];
				printf("%d\t", instante); printf("RUN: %d\t", RUN[0]); printf("BLOCKED: \t\t\t", BLOCKED[0]);
				printf("\n");
				run_now--;
				B_index--;
				instante++;
			}

		}

		else
		{	
			get_arrival_time(instante, line, column);  //Função que me dá os arrival times apos 1ª ida ao RUN

				
			block_now = matrix[line][column+1];
			BLOCKED[B_index++] = matrix[line][0];	
			
			line++;
			run_now = matrix[line][column];
		}
	} 

	printf("\n");

	int j=0;

	while(j< W_index)
	{
		printf("%d ", WAITING[j]);
		j++;
	}	



/*
	W_index=0;
	line=0;
	column+=2;

	run_now = matrix[line][column];

	while(line<4)
	{
		if(run_now > 0)
		{	
			if(block_now > 0 && instante >= WAITING[W_index])
			{
				block_now--;

				RUN[0] = matrix[line][0];

				printf("%d\t", instante); printf("RUN: %d\t", RUN[0]); printf("BLOCKED: "); while(i<B_index){ printf("%d\t", BLOCKED[i]); i++;}
				printf("\n");
				run_now--;
				instante++;
				i=0;
			}
			else
			{	
				RUN[0] = matrix[line][0];
				printf("%d\t", instante); printf("RUN: %d\t", RUN[0]); printf("BLOCKED: \t\t\t", BLOCKED[0]);
				printf("\n");
				run_now--;
				B_index--;
				instante++;
			}
		}

		else
		{	
			get_arrival_time(instante, line, column);  //Função que me dá os arrival times apos 1ª ida ao RUN

				
			block_now = matrix[line][column+1];
			BLOCKED[B_index++] = matrix[line][0];	
			
			line++;
			run_now = matrix[line][column];
		}
	}
*/
	//110 & 300 FINISHED !!
	//Line == 0 || Line == 2
}	



int main()
{
	char input[MAX]; //Array para armazenar cada linha de input com string
	int linecount=0;  //Contador auxiliar para o nmr de linhas de input

	while(linecount<N) //Enquanto linecount < nmr processos(N), vai ler uma linha, converter os nmr para int e guardar na matriz bidimensional
	{                  
		fgets(input, MAX, stdin); //Lê uma linha de input como string e guarda-a em input 
		int line_size = strlen(input);  

		strline_to_int(line_size, input);  //Função que converte os valores no charArray para um intArray
		linecount++;  
	}

	FCFS(matrix);	

	return 0;
}	
	