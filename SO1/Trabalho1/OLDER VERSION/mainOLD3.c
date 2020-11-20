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

#define MAX_CPU_IO 11  //Tamanho máximo do Array que guarda os PID's , t_inicio , t_cpu e t_io de cada processo
#define MAX 128  //Tamanho maximo de cada linha de input


void FCFS(int count1, int count2, int count3, int count4, int p1[count1], int p2[count2], int p3[count3], int p4[count4])
{
	int instante=0;

	while(instante<40)
	{
		while(p1[2]>0)
		{	
			if(instante==p1[1])
			{
				if(instante==p2[1])
				{
					if(instante==p3[1])
					{
						if(instante==p4[1])
						{
							printf("%d # READY %d %d %d # RUN %d # BLOCKED \n", instante, p2[0], p3[0], p4[0], p1[0]);
						}
						printf("%d # READY %d %d    # RUN %d # BLOCKED \n", instante, p2[0], p3[0], p1[0]);	
					}
					printf("%d # READY %d         # RUN %d # BLOCKED \n", instante, p2[0], p1[0]);	
				}
				//printf("%d # READY           # RUN %d # BLOCKED \n", instante, p1[0]);
			}
			p1[2]--;
			instante++;	
		}

		while(p2[2]>0)
		{
			if(p1[3]>0)
			{
				printf("%d # READY %d %d     # RUN %d # BLOCKED %d\n", instante, p3[0], p4[0], p2[0], p1[0]);
				p1[3]--;
				p2[2]--;
				instante++;
			}
			else
			{	
				printf("%d  # READY %d %d %d # RUN %d # BLOCKED \n", instante, p1[0], p3[0], p4[0], p2[0]);
				p2[2]--;
				instante++;
			}		
		}

		while(p1[4]>0)
		{
			if(p2[3]>0)
			{
				printf("%d  # READY %d %d     # RUN %d # BLOCKED %d\n", instante, p3[0], p4[0], p1[0], p2[0]);
				p2[3]--;
				p1[4]--;
				instante++;
			}
			else
			{
				printf("%d  # READY %d %d %d # RUN %d # BLOCKED \n", instante, p2[0], p3[0], p4[0], p1[0]);
				p1[4]--;
				instante++;
			}	
		}

		while(p2[4]>0)
		{
			if(p1[5]>0)
			{
				printf("%d  # READY %d %d     # RUN %d # BLOCKED %d\n", instante, p3[0], p4[0], p2[0], p1[0]);
				p1[5]--;
				p2[4]--;
				instante++;
			}
			else
			{
				printf("%d # READY %d         # RUN %d # BLOCKED %d\n", instante, p4[0], p3[0], p1[0]);
				p3[2]--;
				p2[4]--;
				instante++;
			}	
		}
		
		while(p3[2]>0)
		{
			if(p1[5]>0)
			{
				printf("%d # READY %d         # RUN %d # BLOCKED %d\n", instante, p4[0], p3[0], p1[0]);
				p3[2]--;
				p1[5]--;
				instante++;
			}
			else
			{
				printf("%d  # READY %d %d     # RUN %d # BLOCKED \n", instante, p1[0], p4[0], p3[0]);
				p3[2]--;
				p1[5]--;
				instante++;
			}	
		}

		while(p1[6]>0)
		{
			if(p3[3]>0)
			{
				printf("%d # READY %d         # RUN %d # BLOCKED %d\n", instante, p4[0], p1[0], p3[0]);
				p1[6]--;
				p3[3]--;
				instante++;
			}
			else
			{
				printf("%d  # READY %d %d     # RUN %d # BLOCKED \n", instante, p3[0], p4[0], p1[0]);
				p1[6]--;
				instante++;
			}	
		}	

		//P1 FINISHED

		while(p3[4]>0)
		{
			printf("%d # READY %d         # RUN %d # BLOCKED \n", instante, p4[0], p3[0]);
			p3[4]--;
			instante++;
		}

		while(p4[2]>0)
		{
			if(p3[5]>0)
			{
				printf("%d # READY          # RUN %d # BLOCKED %d\n", instante, p4[0], p3[0]);	
				p4[2]--;
				p3[5]--;
				instante++;
			}
			else
			{
				printf("%d # READY %d         # RUN %d # BLOCKED \n", instante, p3[0], p4[0]);
				p4[2]--;
				instante++;
			}	
		}

		while(p3[6]>0)
		{
			printf("%d # READY          # RUN %d # BLOCKED %d\n", instante, p3[0], p4[0]);
			p3[6]--;
			p4[3]--;
			instante++;
		}

		//P3 FINISHED

		while(p4[3]>0)
		{
			printf("%d # READY          # RUN    # BLOCKED %d\n", instante, p4[0]);
			p4[3]--;
			instante++;
		}

		while(p4[4]>0)
		{
			printf("%d # READY          # RUN %d # BLOCKED 0.\n", instante, p4[0]);
			p4[4]--;
			instante++;
		}		
	}	
}



int main()
{
	char input[MAX];  //String para guardar cada linha do input
	char cpuio[17];  //String que guardas os tempos de CPU e espera por IO de cada processo
	int p1[MAX_CPU_IO]; 
	int p2[MAX_CPU_IO]; 
	int p3[MAX_CPU_IO]; 
	int p4[MAX_CPU_IO]; 
	int count1=0; 
	int count2=0;
	int count3=0; 
	int count4=0;
	int ipos;
	//int ind;

	
//##################################################################################################################################
	//SCAN DA 1ª LINHA DO INPUT
	fgets(input, MAX, stdin);
	sscanf(input, "%[^\n]", cpuio);  //Lê cada linha do input como: 1 int, 1 int, o resto string
			
	ipos=0; 
	//ind=0;
				
	char *tok1 = strtok(cpuio, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      
																																	  
		while (tok1)  //Continua pela String até charArray terminar																		
		{																													          																															  
    		if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											
    		{																														  
        		p1[ipos++] = atoi(tok1);  //Coverte para int e guarda-o no intArray
   			}																														  
    		tok1 = strtok(NULL," ");  //Vai buscar o proximo elemento da String  
    		count1++;												 
		}
//##################################################################################################################################
	//SCAN DA 2ª LINHA DO INPUT	
	fgets(input, MAX, stdin);
	sscanf(input, "%[^\n]", cpuio);  //Lê cada linha do input como: 1 int, 1 int, o resto string
			
	ipos=0; 
	//ind=0;
			
	char *tok2 = strtok(cpuio, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      
																																	  
	while (tok2)  //Continua pela String até charArray terminar																		
	{																													          																															  
    	if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											
    	{																														  
        	p2[ipos++] = atoi(tok2);  //Coverte para int e guarda-o no intArray
   		}																														  
    	tok2 = strtok(NULL," ");  //Vai buscar o proximo elemento da String  																																											  
    	count2++;																												 
	}
//####################################################################################################################################
	//SCAN DA 3ª LINHA DO INPUT	
	fgets(input, MAX, stdin); 
	sscanf(input, "%[^\n]", cpuio);  //Lê cada linha do input como: 1 int, 1 int, o resto string
				
	ipos=0;
	//ind=0; 
			
	char *tok3 = strtok(cpuio, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      
																																	  
	while (tok3)  //Continua pela String até charArray terminar																		
	{																													          																															  
    	if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											
    	{																														  
        	p3[ipos++] = atoi(tok3);  //Coverte para int e guarda-o no intArray
   		}																														  
    	tok3 = strtok(NULL," ");  //Vai buscar o proximo elemento da String 																																											  
    	count3++;																												 
	}
//#######################################################################################################################################
	//SCAN DA 4ª LINHA DO INPUT	
	fgets(input, MAX, stdin);
	sscanf(input, "%[^\n]", cpuio);  //Lê cada linha do input como: 1 int, 1 int, o resto string
		
	ipos=0; 
	//ind=0;
			
	char *tok4 = strtok(cpuio, " ");  //Vai buscar o primeiro elemento do String, cujos são separado por " " (espaços)      

	while (tok4)  //Continua pela String até charArray terminar																		
	{																													          																															  
    	if (ipos < MAX_CPU_IO)  //Verifica se nao excede o tamanho, para evitar overflow! 											
    	{																														  
        	p4[ipos++] = atoi(tok4);  //Coverte para int e guarda-o no intArray
   		}																														  
    	tok4 = strtok(NULL," ");  //Vai buscar o proximo elemento da String   																																											  
    	count4++;																												 
	}
	FCFS(count1, count2, count3, count4, p1, p2, p3, p4);
	return 0;	
}