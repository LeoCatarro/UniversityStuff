#include <stdio.h>
#include <stdlib.h>

int main(void){
    
    FILE* ficheiro;
    char* linha = NULL;
    size_t len = 0;
    
    ficheiro = fopen("texto.txt","r");
    
    if(ficheiro == NULL){
        puts("Ficheiro não encontrado");
    }else{
        while( getline(&linha, &len, ficheiro) != -1 ){
            printf("Linha lida %s", linha);
        }
    }
    return 0;
}
