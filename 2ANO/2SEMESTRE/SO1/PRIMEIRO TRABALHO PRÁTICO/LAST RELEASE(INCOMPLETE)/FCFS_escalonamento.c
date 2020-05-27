#include <stdio.h>
#include <stdlib.h>
#define MAX_INSTRUCOES 9
#define MAX_ARRAY 50

typedef struct {
	int PID;
	int burstTime;
	int t_chegada;
	int waitTime;

}Processo;

typedef struct{
	int tamanho;
	int primeiro;
	int ultimo;
	int capacidade;
	Processo *processos;


}Fila;

void criarFila(Fila *f, int c) {

	f->capacidade = c;
	f->processos = (Processo*) malloc (f->capacidade * sizeof(Processo));
	f->primeiro = 0;
	f->ultimo = -1;
	f->tamanho = 0;

}

void inserir(Fila *f, Processo *processo) {

	f->tamanho++;
	f->ultimo=(f->ultimo+1)%f->capacidade;
	f->processos[f->ultimo]=*processo;

}

void remover(Fila *f) { // pega o item do comeÃ§o da fila

	f->tamanho--;
	f->primeiro=(f->primeiro+1)%f->capacidade;

}
/*
int estaVazia(Fila *f){ // retorna verdadeiro se a fila estÃ¡ vazia

	return (f->nItens==0);
}

int estaCheia(Fila *f){ // retorna verdadeiro se a fila estÃ¡ cheia

	return (f->nItens == f->capacidade);
}

*/
void mostrarFila(Fila *f){

	int cont, i, espacos = 0;

	for(cont=0, i = f->primeiro; cont < f->tamanho; cont++){

		printf("%d ",f->processos[i++]);

		if(i == f->capacidade){
			i=0;
		}
		espacos++;
	}
	while(espacos < 6){
		printf("    ");
		espacos++;
	}
}

int dgtlen(int n){
  int c;

  if(n == 0){

    return 1;

  }else{
    for(c = 0; n; n /= 10){
      c++;
    }
  }
  return c;
}

void ready_check(Fila *f, Processo lista[], int instante){
	int i;
	for(i=0; i < f->capacidade; i++){
		if(instante==lista[i].t_chegada) {
			inserir(f, &lista[i]);
		}
	}
}

void organizaFila(Fila *f){
	int i;
	Processo temp=f->processos[f->primeiro];
	for(i=f->primeiro; i<f->ultimo; i++) {
		f->processos[i]=f->processos[i+1];
	}
	f->processos[f->ultimo]=temp;
}

void run(Processo *p){
	printf("| RUN %d", p->PID);
	p->burstTime--;
}


int main () {

  int totalBurstTime = 0;
	Fila fila;
	Fila ready;
	Fila blocked;
  FILE *file;
  int count_processos = 1;
  int *Vetor = NULL;
  int Linhas = 0;

  file = fopen("input1.txt", "r");

  if(file == NULL)
  {
    printf("Ficheiro nao encontrado.\n");
  }

  while(feof(file) == 0)
  {
    Vetor = (int *) realloc(Vetor, (Linhas + 1) * sizeof(int));
    fscanf(file, "%d", &Vetor[Linhas]);
    Linhas++;
  }

  printf("                                              SIMULADOR DE ESCALONAMENTO                                             \n");
  printf("_____________________________________________________________________________________________________________________\n");

  printf("\n");

  if(dgtlen(Vetor[0])!=3){

    printf("Nao foram introduzidos processos / Insira os processos corretamente com a identificacao do procsessos corretamente.");

  }else{

    for(int i = 0 ; i < Linhas; i++){
      printf("%d ", Vetor[i]);
        if(dgtlen(Vetor[i+1]) == 3){
          printf("\n");
          count_processos ++;
      }
    }
  }

	Processo processo[count_processos];

  printf("\n");
  printf("_____________________________________________________________________________________________________________________\n");
  printf("\n");
  printf("Foram inseridos %d processos\n", count_processos);
  printf("\n");
  printf("O ficheiro foi lido e os dados apresentados e guardados com sucesso.\n");
  printf("\n");


  criarFila(&fila, count_processos);
	criarFila(&ready, count_processos);
  criarFila(&blocked, count_processos);
  //criarFila(&run,1);

//calculo do burst time total
  for(int i = Linhas-1; i > 0; i = i-2){
    if(dgtlen(Vetor[i]) != 3){
      totalBurstTime = totalBurstTime + Vetor[i];
    }else{
      i--;
      totalBurstTime = totalBurstTime + Vetor[i];
    }
  }
	count_processos = 0;

	for(int i = 0; i < Linhas; i++){
		if(dgtlen(Vetor[i])==3){
			processo[count_processos].PID = Vetor[i];
			processo[count_processos].t_chegada = Vetor[i+1];
			processo[count_processos].burstTime = Vetor[i+2];
			count_processos++;
		}
	}

	for(int instante = 0; instante < totalBurstTime; instante++){

		ready_check(&fila,processo,instante);

		printf("\n%3d|", instante);

		ready = fila;
		remover(&ready);

		printf(" READY "); mostrarFila(&ready);

		if(fila.tamanho!=0){
			run(&fila.processos[fila.primeiro]);//run current process
			printf("                 ");
	 	}else{
			printf("| RUN "); printf("                    ");
		}

		if(fila.processos[fila.primeiro].burstTime==0){
			remover(&fila);
		}

    printf(" | BLOCKED "); mostrarFila(&blocked);
  }
  fclose(file);

  return 0;
}
