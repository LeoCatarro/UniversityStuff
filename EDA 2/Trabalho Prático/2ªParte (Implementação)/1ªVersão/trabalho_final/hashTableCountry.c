#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include "hashTableCountry.h"

#define STUDENTS_FILE "students.bin"
#define COUNTRY_FILE "country.bin"
#define MAX_SIZE 20000000

int size = 11;
int pos = 0;

typedef struct country //Total 20 bytes
{
    char p_id[3];
    int current,finished,quit,total; 
    bool isCountry;
}Country;

Country *country_new(char p_id[3])
{
    Country *country = malloc(sizeof(Country));

    for(int i = 0; i < 3; i++)
    {
        country->p_id[i] = p_id[i];
    }
    country->current = country->finished = country->quit = country->total = 0;
    country->isCountry = false;

    return country;
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

bool insertPos(FILE *file, Country *country)
{
    int offset = 1;

    // sizeof(Country) + 4 = 24 , para ser multiplo de 4
    int currentPos = (hash(country->p_id) * (sizeof(Country) + 4)) % MAX_SIZE; //divisao para nao sair out of bounds da HashTable
    int aux = currentPos;

    printf("%d\n", currentPos);

    Country *c = country_new(country->p_id);

    while(true)
    {
        if(currentPos >= MAX_SIZE)
            currentPos -= MAX_SIZE;


        //vai para a posicao calculada pela funcao de hash
        if(fseek(file, currentPos, SEEK_SET) == 0)
        {
            //output != 1 implica que aconteceu um erro ou que chegamos ao EOF 
            if(fread(country, sizeof(Country) + 4, 1, file) == 1)
            {

                if(country->isCountry == true && strcmp(c->p_id, country->p_id) != 0)
                {
                    currentPos = aux + (int) pow(offset, 2);
                    offset++;

                    pos = currentPos;
                }
                else if(strcmp(c->p_id, country->p_id) == 0)
                {
                    printf("%s + %s\n", c->p_id , country->p_id);
                    break;
                }
                //caso em que fez colisoes mas ja encontrou posicao
                else
                {
                    printf("%s\n", c->p_id);

                    return true;
                }
            }
            //a posicao inicial esta pronta para insercao
            else
            {
                printf("%s\n", c->p_id);

                return true;
            }
        }   
    }
    return false;
}

bool searchPos(FILE *file, Country *country)
{
    int offset = 1;
    int currentPos = (hash(country->p_id) * (sizeof(Country) + 4)) % MAX_SIZE; //divisao para nao sair out of bounds da HashTable
    int aux = currentPos;

    Country *c = country;

    while(true)
    {
        if(currentPos >= MAX_SIZE)
            currentPos -= MAX_SIZE;    

        //vai para a posicao calculada pela funcao de hash
        if(fseek(file, currentPos, SEEK_SET) == 0)
        {

            //output != 1 implica que aconteceu um erro ou que chegamos ao EOF 
            if(fread(country, sizeof(Country) + 4, 1, file) == 1)
            {
                if(country->isCountry == true)
                {
                    //printf("test\n");

                    if(strcmp(c->p_id, country->p_id) == 0)
                    {
                        printf("%s\n", country->p_id);
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

bool insertCountry(FILE *file, Country *country)
{
    if(insertPos(file, country) == true)
    {
        country->isCountry = true;

        if(fwrite(country, sizeof(Country) + 4, 1, file) == 1)
        {
            size++;
            return true;
        }
    }
    return false;
}

bool removeCountry(FILE *file, Country *country)
{
    if(searchPos(file, country) == true)
    {
        if(country->isCountry == false)
        {
            return false;
        }
        
        country->isCountry = false;

        if(searchPos(file, country) == true)
        {
            //se fwrite != 1 ocorreu um erro
            if(fwrite(country, sizeof(Country) + 4, 1, file) == 1)
            {
                size--;
                return true;
            }
        }
    }

    return false;
}

bool findCountry(FILE *file, Country *country)
{
    if(searchPos(file, country) == true)
    {
        //printf("test\n");
        return true;
    }
    return false;
}




int main(void)
{
    FILE *file = file_open(COUNTRY_FILE);
    //Country *country1 = country_new("FR");
    Country *country2 = country_new("ES");
    //Country *country3 = country_new("PT");

    if(insertCountry(file, country2) == true)
    {
        printf("inserted\n"); 
    }

    printf("%d\n", findCountry(file, country2));

    /*if(removeStudent(file, student) == true)
    {
        printf("removed\n");
    }*/

    printf("%d", pos);

    file_close(file);
}