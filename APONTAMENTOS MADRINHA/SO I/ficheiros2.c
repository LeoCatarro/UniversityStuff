#include <stdio.h>
#include <stdlib.h>

int main(void){
    
    FILE *ficheiro;
    ficheiro = fopen("file.txt","a");
    
    char texto[] = "A escrita em ficheiros é bastante simples \n";
    fprintf(ficheiro,"%s",texto);
    printf("Foi escrito %s",texto);
    
    fclose(ficheiro);
    
    return 0;
}
