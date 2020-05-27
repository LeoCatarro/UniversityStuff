#include <stdio.h>

int vAux[4];

int sub_soma(int soma, int size, int v[size])
{	
	//CASO SUBSEQUENCIA SEJA FORMADA POR 1 ELEMENTO
	for(int i=0 ; i<size ; i++)
	{
		if(v[i] == soma)
		{
			vAux[0] = i+1;
			return 1;
		}
	}

	// CASO SUBSEQUENCIA SEJA FORMADA POR 2 ELEMENTOS
	for(int j=0 ; j<size ; j++)
	{
		if(v[j] + v[j+1] == soma)
		{
			vAux[1] = j+1;
			vAux[2] = j+2;
			return 2;
		}
	}

	//CASO SUBSEQUENCIA FORMADA POR MAIS QUE 2 ELEMENTOS
	int somaAtual=0;

	for(int i=0 ; i<size ; i++)
	{
		for(int j=i ; j<size ; j++)
		{
			somaAtual = somaAtual + v[j];

			if(somaAtual == soma)
			{
				vAux[3] = i+1;
				vAux[4] = j+1;
				return 3;
			}
		}
		somaAtual = 0;
	}
	return 4;
}


int main()
{
	int num, res_soma;
	scanf("%d", &num);
	int vetor[num];

	for(int i=0 ; i<num ; i++)
	{
		scanf("%d", &vetor[i]);
	}

	scanf("%d", &res_soma);

	if(sub_soma(res_soma, num, vetor) == 1)
		printf("s[%d] = %d\n", vAux[0], res_soma);

	if(sub_soma(res_soma, num, vetor) == 2)
		printf("s[%d] + s[%d] = %d\n", vAux[1], vAux[2], res_soma);

	if(sub_soma(res_soma, num, vetor) == 3)
		printf("s[%d] + ... + s[%d] = %d\n", vAux[3], vAux[4], res_soma);

	if(sub_soma(res_soma, num, vetor) == 4)
		printf("nenhuma subsequencia soma %d\n", res_soma);

	return 0;
}