#include "hashTable.h"

#define COUNTRY_SIZE 900009

unsigned long currentPosCountry = 0;

//Funcao para criar um novo pais com um dado ID
Country *country_new(char *p_id)
{
    Country *country = malloc(sizeof(Country));

    strcpy(country->p_id, p_id);

    country->active = country->finished = country->abandoned = country->total = 0;
    country->isCountry = false;

    return country;
}

//Gera o hashcode de um pais dado o seu ID
unsigned long hashCountry(char *s)
{
    unsigned long hashKey = 5381; //numero utilizado pelo algoritmo para começar
    int c;
    while((c = *s++)) // c vai ter o ASCII de cada char e *s++ percorre a string
    {
        hashKey = ((hashKey << 5) + hashKey) + c; //shift left 5 bits da hashkey + hashkey + valor ASCI de cada chat
    }  

    return hashKey % COUNTRY_SIZE; //divisao para nao sair out of bounds da HashTable
}

//Se o pais a ser procurado não existir na tabela, encontra uma posicao para inserir e retorna true
bool insertPosCountry(FILE *file, Country *country)
{
    currentPosCountry = hashCountry(country->p_id);
    unsigned long aux = currentPosCountry;
    unsigned long offset = sizeof(Country);

    Country *c = country_new(country->p_id);

    fseek(file, currentPosCountry * sizeof(Country), SEEK_SET);

    while(fread(c, sizeof(Country), 1, file) == 1)
    {
        if(currentPosCountry < 0 && aux < 0)
        {
            currentPosCountry *= -1;
            aux *= -1;
        }    
        if(strcmp(c->p_id, "\0") == 0)
        {
            free(c);
            return true;
        }
        else if(strcmp(c->p_id, country->p_id) == 0)
        {
            free(c);
            return false;
        }

        currentPosCountry = aux + pow(offset, 2);
        offset += offset;

        currentPosCountry = currentPosCountry % COUNTRY_SIZE;

        fseek(file, currentPosCountry * sizeof(Country), SEEK_SET);
    }

    return true;
}

//Procura um pais, se o encontrar retorna true. Caso contrario o pais nao existe e retorna false
bool searchPosCountry(FILE *file, Country *country)
{
    currentPosCountry = hashCountry(country->p_id);
    unsigned long aux = currentPosCountry;
    unsigned long offset = sizeof(Country);

    Country *c = country_new(country->p_id);

    fseek(file, currentPosCountry * sizeof(Country), SEEK_SET);

    while(fread(country, sizeof(Country), 1, file) == 1)
    {   
        if(currentPosCountry < 0 && aux < 0)
        {
            currentPosCountry *= -1;
            aux *= -1;
        }

        if(strcmp(c->p_id, country->p_id) == 0)
        {
            return true;
        }
        else if(strcmp(country->p_id, "\0") == 0)
        {
            return false;
        }

        currentPosCountry = aux + pow(offset, 2);
        offset += offset;

        currentPosCountry = currentPosCountry % COUNTRY_SIZE;

        fseek(file, currentPosCountry * sizeof(Country), SEEK_SET); 
    }

    return false;
}

//Insere um pais na tabela caso este nao esteja ja na tabela
bool insertCountry(FILE *file, Country *country)
{
    if(insertPosCountry(file, country) == true)
    {
        country->isCountry = true;

        if(fseek(file, currentPosCountry * sizeof(Country), SEEK_SET) == 0)
        {
            if(fwrite(country, sizeof(Country), 1, file) == 1)
            {
                return true;
            }
        }
    }
    return false;
}

//Incrementa o numero de estudantes ativos e o total em 1
void addActive(FILE *file, Country *country)
{    
        country->active++;
        country->total++;

        if(fseek(file, currentPosCountry * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file); 
        }
}

//Decrementa o numero de estudantes ativos e o total em 1
void removeActive(FILE *file, Country *country)
{
    if(searchPosCountry(file, country) == true)
    {
        country->active--;
        country->total--;

        if(fseek(file, currentPosCountry * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file); 
        }
    }
}

//Incrementa o numero de estudantes que abandonaram e decrementa os ativos em 1
void addAbandoned(FILE *file, Country *country)
{
    if(searchPosCountry(file, country) == true)
    {
        country->abandoned++;
        country->active--;

        if(fseek(file, currentPosCountry * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file);
        }
    }
}

//Incrementa o numero de estudantes que acabaram e decrementa os ativos em 1
void addFinished(FILE *file, Country *country)
{
    if(searchPosCountry(file, country) == true)
    {   
        country->finished++;
        country->active--;

        //fread avanca o pointer entao temos de voltar a posicao inicial
        if(fseek(file, currentPosCountry * sizeof(Country), SEEK_SET) == 0)
        {
            fwrite(country, sizeof(Country), 1, file);
        }
    }
}