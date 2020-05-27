#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "hashTable.h"

#define MAX_SIZE 20000000

int size = 0;

typedef struct student
{
    char id[7];
    char p_id[3];
    bool quit, finished;
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
    student->quit = student->finished = false;

    return student;
}

unsigned long hash(char *key)
{
    unsigned long hashKey = 5381; //numero utilizado pelo algoritmo para começar
    int c;
    while ((c = *key++)) // c vai ter o ASCII de cada char e *key++ percorre a string
    {
        hashKey = ((hashKey << 5) + hashKey) + c;
    }

    return hashKey % MAX_SIZE; //divisao para nao sair out of bounds da HashTable
}


int main(void)
{
    char id [7] = "asd123";

    int hashKey = hash(id);

    printf("%d\n", hashKey);
}