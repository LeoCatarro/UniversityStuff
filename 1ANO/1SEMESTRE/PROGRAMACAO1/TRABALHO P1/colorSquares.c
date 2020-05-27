#include "colorSquares.h"

//FUNÇÃO MARCAR

int marcar(int sz, int tabuleiro[][20], int x, int y)
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


// FUNÇÃO MOVIMENTOS

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


//FUNÇÃO PONTUAÇÃO

int pontuacao(int num_quadrados)
{
  
  int pontos;
  pontos= (num_quadrados*(num_quadrados + 1))/2;
  return pontos;
}


//FUNÇÃO GRAVIDADE

 void gravidade(int sz, int tabuleiro[][20])
{
	int grav=0;
	while (grav<sz){
		for(int col=0; col<sz; col++){
		for(int linha=0; linha<sz; linha++){
			
			if(linha>0 && tabuleiro[linha][col]==0){
				tabuleiro[linha][col]=tabuleiro[linha-1][col];
				tabuleiro[linha-1][col]=0;
				
				}
				
		}
	}
		grav++;
	}
	
		
}




//FUNÇÃO COLUNA


void coluna (int sz, int tabuleiro[][20])
{
	
	int l, col;
	for(col=0; col<sz; col++)
	{
		int vazio = 0; 
		for (l=0; l<sz; l++)
		{
			if(tabuleiro[l][col] == 0)
			{
				vazio++;
			}
		}
		
	if(vazio==sz) 
	 {
		for (int i	= 0; i<sz; i++)
		{
			int j, bx;
			j = col;
			bx = tabuleiro [i][j]; 
			
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



//FUNÇÃO JOGADA

int jogada(int sz, int tabuleiro[][20], int x, int y)
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

//FUNÇÃO MOSTRAR

void mostrar(int sz, int tabuleiro[][20])
{
	
  for (int i = 0; i <sz; i++)
  {
    for (int j = 0; j < sz ; j++) 
    {
      if(tabuleiro[i][j]==0)
      {
		  printf("-");
	  }	  
      else {
		  printf("%d", tabuleiro[i][j]);
		  } 
    }
    printf("\n");
  }
 } 
