#include <stdio.h>
#include <math.h>

void PrimeFactors(unsigned int n)
{
	unsigned int num=2;
	
	while(n>2 && num <= (sqrt(n) + 2) )
	{	
		if(n%num == 0)
		{
			n=n/num;
			printf(" %u", num);
		}
		else
			num++;		
	}
	if(n != 0 && n != 1)
	printf(" %u", n);
}

int main()
{	   
	int number;
    scanf("%d", &number); 
    unsigned int vetor[number];

    for(int i=0 ; i<number ; i++)
    {	
    	scanf("%u", &vetor[i]);	
    }	
    for(int i=0 ; i<number ; i++)
    {	 
    	printf("%u:", vetor[i]);
    	PrimeFactors(vetor[i]);	    
        printf("\n");
    }	     	
	return 0;
}