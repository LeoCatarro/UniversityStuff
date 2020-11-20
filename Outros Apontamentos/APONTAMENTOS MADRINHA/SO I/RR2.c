#include<stdio.h> 

int main () {
 
 int  i=0,k=-1,n;
 int  NP,arrival_t, service_t;

 int Process[NP],ArrivalTime[NP],ServiceTime[NP],Burst[NP];
 
 //Input do numero de processos
 printf("\nInserir o numero de processos: ");
 scanf("%d", &NP);
 printf("\n");
 
 //ciclo para criar os array dos Processos, T.Chegada e T.Serviço
 do{
     printf("Inserir Tempo de Chegada e Tempo de Servico: ");
     scanf("%d %d", &arrival_t, &service_t);
     printf("\n");
     i=i+1;
     k=k+1;
     Process[k]=k+1;
     ArrivalTime[k]=arrival_t;
     ServiceTime[k]=service_t;
     Burst[k]=ServiceTime[k];
     }
 while(i<NP);

 //Tabela c/ inputs

 printf("\n\nProcesso* Tempo Chegada * Tempo Servico\n");
 printf("\n***************************************\n");
 
 int time,flag;
 int remain_time=NP;

 for(n=0;n<NP;n++) {
     printf("P[%d]\t* \t%d\t* \t%d\n\n",Process[n],ArrivalTime[n],ServiceTime[n]);    
 }

 //Escalonamento Round Robin

 printf("\n\nInstante* Processo \n");
 printf("\n*************************\n");
 
 for(time=-1,i=0;remain_time!=0;) {
           if(Burst[i] <= 1 && Burst[i]>0) {                         // Burst[i] <= Q=1
                     time = time + Burst[i];
                     printf(" %d\t* \tP%d\t* No instante %d foi atribuido ao CPU o processo P%d\n",time,Process[i],time,Process[i]);
                     Burst[i]=0;
                     flag = 1;
           }
           else if(Burst[i] > 0) {
                     Burst[i] = Burst[i] - 1;                        // Burst[i] - Q=1
                     time = time + 1;                                // time - Q=1   
                     printf(" %d\t* \tP%d\t* No instante %d foi atribuido ao CPU o processo P%d\n",time,Process[i],time,Process[i]);
           }
           if(Burst[i]==0 && flag==1) {
                     remain_time= remain_time - 1;                   // remain_time - Q=1 
		     flag=0;
           }
           if(i==NP-1) {
                     i=0;
           }
           else if(ArrivalTime[i+1] <= time) {
                     i=i+1;                     
           }
           else {
                     i=0;
           }
 }	       
 return 0;
}


