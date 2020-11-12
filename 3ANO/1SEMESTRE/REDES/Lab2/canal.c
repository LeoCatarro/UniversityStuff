#include <stdio.h>
#include <string.h>
#include <math.h>

void canal(char *, char*);	
void main(){
char x[100], y[100];

strcpy ( x , "ABCDEFGHIJKLMNOPQR1234567890abcdefghijklmnopqrstuvxyz  :-))))))");

canal(x,y);

printf("\n x(input)  %s\n y(output) %s \n",x,y);
}

void canal( char* in, char* out ){
int i = 1, erro=0;
while (*in != '\0')
	{
        	erro=0;      
		i=(i+5)%7;
		printf(" %i ",i);
		if(i>3){erro=0;}else{erro=pow(2,i);} //função que gera os erros
		*out = (*in)^(char)(erro);
		out++;
		in++;
		i++;
	}
	*out = '\0';
}
