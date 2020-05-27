/*###################################################################################
					ESTADO DO TRABALHO:
					-->LEITURA DO INPUT CORRETA E TODA
					-->ORGANIZADO POR VARS E ARRAYS (PID, T_INICIO, CPUS, IOS)
					-->GUARDAR OS CPUS E IOS DE TODOS OS PROCESSOS EM ARRAY BIDIMENSIONAL	
					-->GUARDAR OS PIDS E T_ININCIO DE TODOS OS PROCESSOS EM ARRAYS				

					
					O QUE FALTA:
					-->IMPLEMENTAR FCFS
					-->IMPLEMENTAR RR
					-->COMENTAR TODO O TRABALHO
					-->OTIMIZAR CÓDIGO
##################################################################################*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 4  //Numero de Processos
#define MAX_CPU_IO 9  //Tamanho máximo do intArray que guarda os t_cpu e t_io de cada processo
#define MAX 128  //Tamanho maximo de cada linha de input

/*
void FCFS(int pid[N], int t_inicio[N], int cpus[N][5], int ios[N][4])
{

}
*/

int main()
{
	char input[MAX];  //String para guardar cada linha do input
	int pid[N];  //Array com os PID's dos processos
	int t_inicio[N];  //Array com os t_inicios dos processos
	char cpuio[17];  //String que guardas os tempos de CPU e espera por IO de cada processo
	int ind2=0;

	int cpus[N][5];
	int ios[N][4];
	
	while(fgets(input, MAX, stdin))  
	{
		sscanf(input, "%d %d %[^\n]", &pid[ind2], &t_inicio[ind2], cpuio);


		//Prints dos pid's e t_inicio's
		//printf("PID: %d\n", pid[ind2]);
		//printf("t_inicio: %d\n", t_inicio[ind2]);


		ind2++;	

		int intArray[MAX_CPU_IO];  //Array de inteiros para guardar os valores retirados da String cpuio 
		int ipos = 0; //Auxiliar para indicar o indice do intArray
		int count=0; //Auxiliar para saber o tamanho do intArray
		
		char *tok = strtok(cpuio, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      
																																	  
		while (tok)  //Continua pela String até charArray terminar																		
		{																													          
    																																  
    		if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											
    		{																														  
        		intArray[ipos++] = atoi(tok);  //Coverte para int e guarda-o no intArray                                             
   			}																														  
    		tok = strtok(NULL," ");  //Vai buscar o proximo elemento da String   																																											  
    		count++;																												 
		}
		
		 
		
		int index_cpus=0;
		int index_ios=0;
		int i=0;
		int j=0;
		int ind=0;
		


		//Ciclo para por na posição por os valores de CPU e IO dos processos num array bidimensional
		while( ( i < (count/2) ) && (ind<N))
		{
			cpus[ind][index_cpus] = intArray[j];
			ios[ind][index_ios] = intArray[j+1];
			index_cpus++;
			index_ios++;
			j=j+2;
			i++;
		}	
		cpus[ind][index_cpus] = intArray[count-1];
		index_cpus++;
		ind++;


		//Prints de cpu lenght e ios lenght
		//printf("cpu's lenght: %d\n", index_cpus);
		//printf("io's lenght: %d\n", index_ios);


		//Código para printar os valores dos arrays bidimensionais cpu's e io's
/*		int l=0;
		int c=0;

		while(l<N)
		{
			while(c<index_cpus)
			{
				printf("%d ", cpus[l][c]);
				c++;
			}
			l++;
		}

		printf("\n");

		l=0;
		c=0;

		while(l<N)
		{
			while(c<index_ios)
			{
				printf("%d ", ios[l][c]);
				c++;
			}
			l++;
		}
*/
		//Auxiliares para ficilitar percepção dos dados organizados
		//printf("\n");
		//printf("###################################");
		//printf("\n");
	}
	return 0;	
}

