/*###################################################################################
					ESTADO DO TRABALHO:
					-->LEITURA DO INPUT CORRETA E TODA
					-->ORGANIZADO POR VARS E ARRAYS (PID, T_INICIO, CPUS, IOS)
					-->ORGANIZADO 1 ARRAY PARA CADA PROCESSO
					-->APENAS A FUNCIONAR PARA 4 PROCESSOS

					O QUE FALTA:
					-->IMPLEMENTAR FCFS
					-->IMPLEMENTAR RR
					-->COMENTAR TODO O TRABALHO
					-->OTIMIZAR CÓDIGO
##################################################################################*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 1
#define MAX_CPU_IO 11  //Tamanho máximo do Array que guarda os PID's , t_inicio , t_cpu e t_io de cada processo
#define MAX 999999 //Tamanho maximo de cada linha de input

/*
void FCFS(int count1, int count2, int count3, int count4, int p1[count1], int p2[count2], int p3[count3], int p4[count4])
{
	
}

*/

int main()
{
	char input[MAX];  //String para guardar cada linha do input
	//int process[N][MAX_CPU_IO];
	int l;
	int i=0;
	//int c;
	//int intArray[MAX_CPU_IO];
	//int count=0;

	
//##################################################################################################################################
/*	
	while(l<N)
	{
		while(c<MAX_CPU_IO)
		{
			process[l][c] = -1;
			//printf("%d ", process[l][c]);
			c++;
		}
		l++;
		//printf("\n");
	}
*/
	l=0;
	//c=0;

	while(l<N)
	{
		char arr[2];
		i=0;
		fgets(input, MAX, stdin);
		printf("%s", input);
		printf("\n");
		printf("%d\n", strlen(input));

		while(i<strlen(input))
		{
			if(input[i+1]== ' ')
			{
				for(int j=0; j<i ; j++)
				{
					arr[j] = input[j];
				}	
				int a = atoi(arr);
				printf("%d ", a);
				i=i+2;
			}
			else
			{	
				i++;	
			}
		}	
		l++;
	}	
		

	return 0;	
}