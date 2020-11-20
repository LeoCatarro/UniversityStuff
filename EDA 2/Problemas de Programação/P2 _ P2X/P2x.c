#include <stdio.h>
/*
		if(v[i] + v[i+1] == soma)
		{
			printf("s[%d] = %d", i, soma);
		}
		else
		{
			v[i] = v[i] + v[i+1];
			i++;
		}
		*/






void sub_soma(int soma, int size, int v[size])
{

	for(int i=0 ; i<size ; i++)
	{
		
		if(v[i] == soma)
		{
			printf("s[%d] = %d", i, soma);
		}
		else
		{
			for(int j=0 ; j<size ; j++)
			{
				if(v[i]+v[i+1] == soma)
				{
					printf("s[%d] + s[%d] = %d", i, i+1, soma);
				}
				else
					i++; 
			}	
		}	
	}	
}


int main()
{

	int num, res_soma;
	scanf("%d", &num);
	int vetor[num];

	for(int i=0 ; i<num ; i++)
	{
		scanf("%d ", &vetor[i]);
	}
	scanf("%d", &res_soma);

	sub_soma(res_soma, num, vetor);
	return 0;
}