#include <stdio.h>
#include <stdlib.h>
#include <string.h>// usar char's e strings
#include <time.h>//para poder usar a funçao srand
#include "colorSquares.h"

#define SZ 20;

/*
int marcar(int sz, int tabuleiro[sz][sz], int x, int y)//vai contar o numero de quadrados do grupo
{
  int num_quadrados = 0;
  for(x= 0; x< sz; x++)
  {
	  for(y=0; y< sz; y++)
	  {
		  if(tabuleiro[x][y]==0)
		  {
			  num_quadrados++;
		}
	}
}
return num_quadrados;		  
		  
  
}

int movimentos(int sz, int tabuleiro[][20], int x, int y)
{
	
   if((x>=0 && x<sz) && (y>=0  && y<sz))
  {
    int g = tabuleiro[x][y];
    tabuleiro[x][y] = 0;  
    if(tabuleiro[x+1][y] == g){ movimentos(sz, tabuleiro, x+1, y);}
   else if (tabuleiro[x-1][y] == g){movimentos(sz, tabuleiro, x-1, y);}
   else if (tabuleiro[x][y+1] == g){ movimentos(sz, tabuleiro, x, y+1);}
   else if(tabuleiro[x][y-1] == g){ movimentos(sz, tabuleiro, x, y-1);}
  }
  return 0;
 } 

//pontuacao
int pontuacao(int num_quadrados)
{
  
  int pontos;
  pontos= (num_quadrados*(num_quadrados + 1))/2;
  return pontos;
}

//gravidade 

 void gravidade(int sz, int tabuleiro[sz][sz])
{
	int grav=0;
	while (grav<sz){
		for(int col=0; col<sz; col++)
		{
		for(int linha=0; linha<sz; linha++)
		{
			
			if(linha > 0 && tabuleiro[linha][col]==0)
			{
				tabuleiro[linha][col] =tabuleiro[linha-1][col];
				tabuleiro[linha-1][col]=0;
				//printf("%d",tabuleiro[linha][col] );
			}
				
		}
	}
		grav++;
}
	
		
}

void coluna (int sz, int tabuleiro[sz][sz]) // percorremos um coluna do array de cada vez enquanto percorremos as linhas 
{
	//raciocicio parecido ao da função gravidade
	int l, col;
	for(col=0; col<sz; col++)
	{
		int vazio = 0; //Apenas dentro do primeiro for, porque apenas corre consoante as colunas
		for (l=0; l<sz; l++)
		{
			if(tabuleiro[l][col] == 0)
			{
				vazio++;
			}
		}
		
	if(vazio==sz) // se o numero de 0s for igual ao tamanho do tabuleiro significa que podemos retirar essa colu
	 {
		for (int i	= 0; i<sz; i++)
		{
			int j, bx;
			j = col;
			bx = tabuleiro [i][j]; // funciona como auxiliar. Experimentamos sem auxiliar e acabava por não funcionar sempre
			
			while (j< sz-1)
			{
				tabuleiro [i][j]=tabuleiro[i][j+1];
				tabuleiro[i][j+1]= bx;
				j++;
			}
		}
	 }					
	
}
}	


void aleatorio(int sz, int tabuleiro[sz][sz])
{
	 srand(time(NULL));//função para gerar valores aleatorios
  
  for (int i = 0; i < sz; i++)
  {
    for (int j = 0; j < sz; j++)
    {
      tabuleiro[i][j] = rand() %4 + 1;//gerar a matriz com valores aleatórios
    }
  }
 

} 
 

//jogada
int jogada(int sz, int tabuleiro[sz][sz], int x, int y)
{
	
   int pontos=0;
   movimentos(sz, tabuleiro, y, x);
   coluna (sz, tabuleiro);
   gravidade(sz, tabuleiro);
   
  
   int num_quadrados = marcar(sz, tabuleiro, x, y);
   pontuacao(num_quadrados);
   pontos = pontos + pontuacao(num_quadrados);
   printf("Os seus pontos são: %d\n\n", pontos);
   
   return 0;
   
   
}

//Mostra o tabuleiro

void mostrar(int sz, int tabuleiro[sz][sz])
{
	
  for (int i = 0; i <sz; i++)//for para o numero de linhas
  {
    for (int j = 0; j < sz ; j++) //for para o numero de colunas
    {
      if(tabuleiro[i][j]==0)
      {
		  printf("-");// transformar o 0 em -.
	  }	  
      else {
		  printf("%d", tabuleiro[i][j]);} 
    }
    printf("\n");
  }
 } 



  

*/


void aleatorio(int sz, int tabuleiro[][20])
{
	 srand(time(NULL));//função para gerar valores aleatorios
  
  for (int i = 0; i < sz; i++)
  {
    for (int j = 0; j < sz; j++)
    {
      tabuleiro[i][j] = rand() %4 + 1;//gerar a matriz com valores aleatórios
    }
  }
 

} 


//main
int main()
{
  
  int sz;
  printf("Escolha o tamanho do tabuleiro: \n");
  scanf("%d", &sz);// Apenas é pedido um valor para o tamanho do tabuleiro porque o tabuleiro é quadrado (sz*sz)
  printf("\n");
  
  
  int tabuleiro[20][20];
  
  aleatorio(sz, tabuleiro); // recursiva da função aleatorio
  
  //a jogada funciona por coordenadas
  int linha_x, coluna_x;
  
  while(sz!=0)
  {
  mostrar(sz, tabuleiro);
  printf("\n");

  printf("escolha a linha: \n");
  scanf("%d", &linha_x);
  printf("\n");

  printf("Escolha a coluna :\n");
  scanf("%d", &coluna_x);
   
   coluna_x= sz-1-coluna_x; // inicialmente, comocamos na função mostrar, o for(i=sz-1; i>=0; i++), para o ponto de coordenadas (0,0) ser no canto inferior esquerdo, mas nao conseguiamos fazer a função gravidade e por isso é que fizemos esta alteração.
  
  jogada(sz, tabuleiro, linha_x, coluna_x);
  
  
  printf("\n");
}
  
  
  
}
