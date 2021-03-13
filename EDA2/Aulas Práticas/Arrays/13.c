//Exercicio 13.a)

#include <stdio.h>

#define NELEMENTOS 50000

int procura(int n, int s, int v[])
{
	for(int i=0 ; i<s ; i++)
	{
		if(v[i] == n)
		{
			return i;
		}
	}
	return -1;
}




int main()
{
	int v[NELEMENTOS];
	//int n1;

	for(int i=0, n=2 ; i<NELEMENTOS ; i++, n=n+2)
	{
		v[i] = n;
	}
	

	//printf("%d\n", h);



	for (int i = 0; i < NELEMENTOS; ++i)
  	{
  		v[i]= 2*i;	
  	}
  	int p = procura(4,NELEMENTOS, v);

  	if (p == -1)
      	printf("Nao encontrou %d\n", p);
    else if(v[p] != 2 * p)
      	printf("Encontrou %d na posição errada: %d\n", 2 * p, p);
	else
		printf("Encontrou %d na posiçao %d\n", ,2*p);
	return 0;
}
