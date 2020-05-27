#include<stdio.h>

void funcaoTeste(char array[]){
    printf("teste a fazer print de dentro da funcao: %s \n",array);
}

int main(){
    
    char string[10];
    puts("insira o texto para a string:");
    scanf("%[0-9a-zA-Z]s", string);
    printf("texto inserido: %s \n",string);
    puts("###############");
    
    funcaoTeste(string);
}
