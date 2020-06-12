#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "hashTable.h"

#define STUDENTS_FILE "students.bin"
#define COUNTRY_FILE "country.bin"
#define MAX_SIZE 20000000

int size = 11;
int pos = 0;

typedef struct student //Total 7 + 3 + 1 + 1 +1 =  13 bytes
{
    char id[7];
    char p_id[3];
    bool quit, finished;
    bool isStudent;
}Student;

Student *student_new(char id[7], char p_id[3])
{
    Student *student = malloc(sizeof(Student));

    for(int i = 0; i < 7; i++)
    {
        student->id[i] = id[i];
    }
    for(int i = 0; i < 3; i++)
    {
        student->p_id[i] = p_id[i];
    }
    student->quit = student->finished = student->isStudent = false;

    return student;
}

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

void file_close(FILE *file)
{
    fclose(file);
}

unsigned long hash(char *key)
{
    unsigned long hashKey = 5381; //numero utilizado pelo algoritmo para começar
    int c;
    while((c = *key++)) // c vai ter o ASCII de cada char e *key++ percorre a string
    {
        hashKey = ((hashKey << 5) + hashKey) + c; //shift left 5 bits da hashkey + hashkey + valor ASCI de cada chat
    }

    return hashKey; 
}

bool insertPos(FILE *file, Student *student)
{
    int offset = 1;
    int currentPos = (hash(student->id) * (sizeof(Student) + 3)) % MAX_SIZE; //divisao para nao sair out of bounds da HashTable
    int aux = currentPos;

    printf("%d\n", currentPos);

    Student *s = student_new(student->id, student->p_id);

    while(true)
    {
        if(currentPos >= MAX_SIZE)
            currentPos -= MAX_SIZE;


        //vai para a posicao calculada pela funcao de hash
        if(fseek(file, currentPos, SEEK_SET) == 0)
        {
            //output != 1 implica que aconteceu um erro ou que chegamos ao EOF 
            if(fread(student, sizeof(Student) + 3, 1, file) == 1)
            {

                if(student->isStudent == true && strcmp(s->id, student->id) != 0)
                {
                    currentPos = aux + (int) pow(offset, 2);
                    offset++;

                    pos = currentPos;
                }
                else if(strcmp(s->id, student->id) == 0)
                {
                    printf("%s + %s\n", s->id, student->id);
                    break;
                }
                //caso em que fez colisoes mas ja encontrou posicao
                else
                {
                    printf("%s\n", s->id);

                    return true;
                }
            }
            //a posicao inicial esta pronta para insercao
            else
            {
                printf("%s\n", s->id);

                return true;
            }
        }   
    }
    return false;
}

bool searchPos(FILE *file, Student *student)
{
    int offset = 1;
    int currentPos = (hash(student->id) * (sizeof(Student) + 3)) % MAX_SIZE; //divisao para nao sair out of bounds da HashTable
    int aux = currentPos;

    Student *s = student;

    while(true)
    {
        if(currentPos >= MAX_SIZE)
            currentPos -= MAX_SIZE;    

        //vai para a posicao calculada pela funcao de hash
        if(fseek(file, currentPos, SEEK_SET) == 0)
        {

            //output != 1 implica que aconteceu um erro ou que chegamos ao EOF 
            if(fread(student, sizeof(Student) + 3, 1, file) == 1)
            {
                if(student->isStudent == true)
                {
                    //printf("test\n");

                    if(strcmp(s->id, student->id) == 0)
                    {
                        printf("%s\n", student->id);
                        return true;
                    }

                    currentPos = aux + (int) pow(offset, 2);
                    offset++;

                    pos = currentPos;
                }
            }
            else
                break;
        }   
    }
    return false;
}

//ALTERAL POINTER

bool insertStudent(FILE *file, Student *student)
{
    if(insertPos(file, student) == true)
    {
        student->isStudent = true;

        if(fwrite(student, sizeof(Student) + 3, 1, file) == 1)
        {
            size++;
            return true;

        }
    }
    return false;
}

bool removeStudent(FILE *file, Student *student)
{
    if(searchPos(file, student) == true)
    {
        if(student->isStudent == false)
        {
            return false;
        }
        
        student->isStudent = false;

        if(searchPos(file, student) == true)
        {
            //se fwrite != 1 ocorreu um erro
            if(fwrite(student, sizeof(Student) + 3, 1, file) == 1)
            {
                size--;
                return true;
            }
        }
    }

    return false;
}

bool findStudent(FILE *file, Student *student)
{
    if(searchPos(file, student) == true)
    {
        //printf("test\n");
        return true;
    }
    return false;
}




int main(void)
{
    FILE *file = file_open(STUDENTS_FILE);
    //Student *student = student_new("BACD01", "PT");
    Student *student2 = student_new("ABCD01", "ES");
    /*Student *student3 = student_new("ABCD03", "FR");*/

    if(insertStudent(file, student2) == true)
    {
        printf("inserted\n"); 
    }


    //printf("%d\n", findStudent(file, student2));

    /*if(removeStudent(file, student) == true)
    {
        printf("removed\n");
    }*/

    printf("%d", pos);

    file_close(file);
}