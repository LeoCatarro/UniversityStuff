#include "hashTable.h"

#define STUDENTS_FILE "students.bin"
#define COUNTRY_FILE "country.bin"

//Abre um ficheiro binario para escrita e leitura, caso este nao exista cria um novo
FILE *file_open(char *file_name)
{
    //abre file
    FILE *f = fopen(file_name, "rb+");

    //caso file exista retorna-o
    if(f != NULL)
        return f;

    //caso file nao exista cria
    f = fopen(file_name, "wb+");

    return f;
}

//Fecha um ficheiro binario
void file_close(FILE *file)
{
    fclose(file);
}

//Executa as operacoes de acordo com o input dado
bool parseInput(char c, char *id, char *p_id, Student *student, Country *country, FILE *file, FILE *cFile)
{
    if(c == 'I')
    {
        scanf("%s %s", id, p_id);

        student = student_new(id, p_id);
        country = country_new(p_id);

        //Estudante ja existe na tabela
        if(insertStudent(file, student) == false)
        {
            printf("+ estudante %s existe\n", id);  
        }
        //Cria um novo pais e atualiza os seus dados
        else if(searchPosCountry(cFile, country) == false)
        {
            strcpy(country->p_id, p_id);

            insertCountry(cFile, country);

            addActive(cFile, country);
        }
        //Pais ja existente e atualiza os seus dados
        else
        {
            addActive(cFile, country); 
        }      
    }
    else if(c == 'R')
    {
        scanf("%s", id);

        strcpy(student->id, id);

        student = student_new(id, getCountry(file, student));
        country = country_new(student->p_id);

        //Remove um estudante e atualiza os numeros dos paises
        if(removeStudent(file, student) == true)
        {
            removeActive(cFile, country);
        }
        else if(student->quit == false && student->finished == false)
        {
            printf("+ estudante %s inexistente\n", id);
        }
        else if(student->quit == true)
        {
            printf("+ estudante %s abandonou\n", student->id);
        }
        else if(student->finished == true)
        {
            printf("+ estudante %s terminou\n", student->id);
        }
    }
    else if(c == 'A')
    {
        scanf("%s", id);
            
        strcpy(student->id, id);

        student = student_new(id, getCountry(file, student));
        country = country_new(student->p_id);

        //Atualiza o estado do estudante como abandonado o curso caso este exista e nao tenha acabado ou abandonado
        //Se o estudante nao existir ou se ja tiver acabado/abandonado faz o print adequado
        if(abandoned(file, student) == false)
        {
            if(student->finished == false && student->quit == false)
                printf("+ estudante %s inexistente\n", id);
            else if(student->finished == true)
                printf("+ estudante %s terminou\n", id);
            else if(student->quit == true)
                printf("+ estudante %s abandonou\n", id);           
        }
        //Estudante existe e atualiza o estado do pais
        else
        {
            addAbandoned(cFile, country);
        }            
    }
    else if(c == 'T')
    {
        scanf("%s", id);
            
        strcpy(student->id, id);

        student = student_new(id, getCountry(file, student));
        country = country_new(student->p_id);

        //Atualiza o estado do estudante como acabado o curso caso este exista e nao tenha acabado ou abandonado
        //Se o estudante nao existir ou se ja tiver acabado/abandonado faz o print adequado
        if(finished(file, student) == false)
        {
            if(student->finished == false && student->quit == false)
                printf("+ estudante %s inexistente\n", id);
            else if(student->quit == true)
                printf("+ estudante %s abandonou\n", id);
            else if(student->finished == true)
                printf("+ estudante %s terminou\n", id);
        }
        //Estudante existe e atualiza o estado do pais
        else
        {  
            addFinished(cFile, country);
        }
    }
    else if(c == 'P')
    {
        scanf("%s", p_id);

        country = country_new(p_id);

        //Pais nao existente
        if(searchPosCountry(cFile, country) == false)
        {
            printf("+ sem dados sobre %s\n", p_id);
        }  
        else
        {
            //Pais existente mas sem dados
            if(country->total == 0)
                printf("+ sem dados sobre %s\n", p_id);
            else
                printf("+ %s - correntes: %d, diplomados: %d, abandonaram: %d, total: %d\n", p_id, country->active, country->finished, country->abandoned, country->total);
        }
    }
    else if(c == 'X')
        return false;

    return true;
}

int main(void)
{
    FILE *file = file_open(STUDENTS_FILE);
    FILE *cFile = file_open(COUNTRY_FILE);

    Country *country = country_new("--");
    Student *student = student_new("------", "--");

    char c;
    char id[ID_SIZE];
    char p_id[P_ID_SIZE];

    while(scanf("%c ", &c) != EOF)
    {        
        if(parseInput(c, id, p_id, student, country, file, cFile) == false)
            break;               
    }

    free(student);
    free(country); 

    file_close(file);
    file_close(cFile);

    return 0;
}    