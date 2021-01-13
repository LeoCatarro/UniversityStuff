#include <stdio.h>

//EXERCICIO 2:
int conta_alunos(FILE *file){
    int nmralunos=0;
    char str[999];
    if (file) {
        while (fscanf(file, "%s", str) != EOF)
            if (str != '/n')
                nmralunos++;
    }
    return nmralunos;
}

//EXERCICIO 3:
struct aluno {
    char nome[100];
    char nmr[5];
    char nota[2];
}aluno1;

/*
void processa_notas(FILE *file){
    char str[999];
    int i=0;
    struct aluno aluno1;
    while(fscanf(file, "%s", str)){
        for(i; i<str ; i++){
            if(str[i] == ';'){
                aluno1.nome == str[i];
            }
            else{
                i++;
            }
        }
    }
}
*/

int main(){
    FILE * file;
    file = fopen( "C:\\Users\\Leonardo\\Desktop\\Ex2\\notas.txt" , "r");
    printf("%d", conta_alunos(file));
    fclose(file);
    return 0;
}