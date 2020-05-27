/*###################################################################################
					ESTADO DO TRABALHO:
					-->LEITURA DO INPUT CORRETA E TODA
					-->ORGANIZADO POR VARS E ARRAYS (PID, T_INICIO, CPUS, IOS)					

					
					O QUE FALTA:
					-->TERMINAR IMPLEMENTAR FCFS
					-->IMPLEMENTAR RR
					-->COMENTAR TODO O TRABALHO
					-->OTIMIZAR CÓDIGO
##################################################################################*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_CPU_IO 9  //Tamanho máximo do intArray que guarda os t_cpu e t_io de cada processo
#define MAX 128  //Tamanho maximo de cada linha de input


void FCFS(int PID, int t_inicio, int count, int intArray[count])
{	
	int cpus[5];
	int ios[4];
	int index_cpus=0;
	int index_ios=0;

	int i=0;
	int w=0;

	while(i < (count/2))
	{
		cpus[index_cpus] = intArray[w];
		ios[index_ios] = intArray[w+1];
		index_cpus++;
		index_ios++;
		w=w+2;
		i++;
	}	
	cpus[index_cpus] = intArray[count-1];

	printf("%d\n", index_cpus);
	printf("%d\n", index_ios);
	

	int l=0;

	while(l<index_cpus+1)
	{
		printf("%d ", cpus[l]);
		l++;
	}
	printf("\n");
	l=0;

	while(l<index_ios)
	{
		printf("%d ", ios[l]);
		l++;
	}
	printf("\n");	
}


int main()
{
	char input[MAX];  //String para guardar cada linha do input
	int PID;  //PID de cada processo
	int t_inicio;  //Tempo de Inicio de cada Processo
	char cpuio[17];  //String que guardas os tempos de CPU e espera por IO de cada processo
	
	
	while(fgets(input, MAX, stdin))  //
	{
		sscanf(input, "%d %d %[^\n]", &PID, &t_inicio, cpuio);
		
		printf("PID:%d\n", PID);
		printf("t_inicio:%d\n", t_inicio);	

		int i=0;	
		int intArray[MAX_CPU_IO];  //Array de inteiros para guardar os valores retirados da String cpuio 
		int ipos = 0;
		int count=0;
		
		char *tok = strtok(cpuio, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      ##########
																																	  //#
		while (tok)  //Continua pela String até charArray terminar																		#
		{																													          //#
    																																  //#
    		if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											#
    		{																														  //#  Com ajuda de código visto em : 
        		intArray[ipos++] = atoi(tok);  //Coverte para int e guarda-o no intArray                                                #  https://arduino.stackexchange.com/questions/22116/how-to-convert-array-of-chars-to-array-of-ints
   			}																														  //#
    		tok = strtok(NULL," ");  //Vai buscar o proximo elemento da String   														#
    		printf("%d ",intArray[i]); //Printa o valor em int do elemento da string já no intArray									#
    		i++;																													  //#
    		count++;																												  //#
		}
		printf("\n");																										 //##########																													
		printf("%d\n", count);
		FCFS(PID, t_inicio, count, intArray);
		//printf("\n");		
	}

	return 0;	
}

