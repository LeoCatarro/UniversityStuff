#include <stdio.h>
#include <stdlib.h>

#include "list.h"

//############################################################################################################################
//NOTA: ESTA IMPLEMENTAÇÃO FAZ USO DA IMPLEMENTAÇÃO DO PROFESSOR VASCO(disponibilizada na parte das soluções dos exercícios)!#
//############################################################################################################################


//***********************************************************

struct node
{
    int element;
    struct node* next;
};


struct list
{
    struct node* head;
};

//*********************************************************

//CONSTRUTOR PARA NÓS
static struct node* node_new(int value, struct node* next)
{
    struct node* node = malloc(sizeof(struct node));

    if(node != NULL)
    {
        node->element = value;
        node->next = next;
    }
    return node;  
}

//*******************************************************

//CRIA E DEVOLVE UMA NOVA LISTA
struct list* list_new(void)
{
    struct list* list = malloc(sizeof(struct list));

    if(list != NULL)
    {
        list->head = NULL;
    }
    return list;  
}

//*******************************************************

//INSERE UM NOVO VALOR NO INICIO DA LISTA
bool list_insert(struct list* list, int value)
{
    struct node* node = node_new(value, list->head);

    if(node == NULL)
    {
        return false;
    }
    list -> head = node;
    return true;  
}

//******************************************************
// FAZ PRINT DA LISTA

void list_print(struct list* list)
{
    struct node* node = list->head;

    putchar('[');

    while(node != NULL)
    {
        printf("%d", node->element);

        if(node->next != NULL)
        {
            putchar(' ');
        }
        node = node->next;  
    }
    putchar(']');  
}

//******************************************************

//LIBERTA A MEMORIA DA LISTA
void list_destroy(struct list* list)
{
    struct node* node = list->head;

    while(node != NULL)
    {
        struct node* next = node->next;
        free(node);
        node = next;
    }
    free(list);  
}

//*******************************************************

//VERIFICA SE A LISTA ESTÁ VAZIA
bool list_empty(struct list* list)
{
    struct node* node = list->head;

    if(node == NULL)
    {
      return true;
    }
    return false;  
}

//******************************************************
//REMOVE UMA OCORRENCIA DO VALOR NA LISTA
void list_remove(struct list* list, int value)
{
    struct node* tmp = list->head;
    struct node* prev = NULL;

    while((tmp->element != value))
    {
        if(tmp->next == NULL)
        {
          break;
        }
        prev = tmp;
        tmp = tmp->next;
    }

    if(tmp == list->head)
    {
        list->head = list->head->next;
    }
    else
    {
        prev->next = tmp->next;
    } 
}

//*****************************************************
//DEVOLVE O TAMANHO DE UMA LISTA
int list_lenght(struct list* list)
{
    int count = 0;
    struct node* current = list->head;

    while(current != NULL)
    {
        count++,
        current = current->next;
    }
    return count;  
}

//****************************************************
//DEVOLVE A POSIÇÃO DE UMA OCORRENCIA DO VALOR NA LISTA
int list_find(struct list* list, int value)
{
    int index= list_lenght(list) - 1;
    struct node* tmp = list->head;

    while(tmp != NULL)
    {
        if(tmp->element == value)
        {
            return index;
        }  
        index--;
        tmp = tmp->next;
    }
    return -1; 
}

//******************************************************
//DEVOLVE O N-ÉSIMO ELEMENTO DA LISTA

int list_nth(struct list* list, int n)
{
    struct node* tmp = list->head;
    int elemento = 0;

    while(list_lenght(list) -1 - n > elemento)
    {
      if(tmp != NULL)
      {  
          tmp = tmp->next;
          elemento++;
      }  
    }
    return tmp->element;  
}

/*
int list_nth(struct list* list, int n)
{
    struct node* tmp;
    
    while(n>=0)
    {
        tmp = tmp->next;
    }    
}*/
//#####################################################
/*
// TESTES
int main(void)
{
    struct list* l = list_new();

    list_insert(l, 500);
    list_insert(l, 200);
    list_insert(l, 700);
    list_insert(l, 100);
    list_insert(l, 900);
    list_insert(l, 800);
   

    list_print(l);

    printf("\n");

    printf("LENGHT: %d", list_lenght(l));

    printf("\n");

    list_remove(l, 500);

    list_print(l);

    printf("\n");

    printf("LENGHT: %d\n", list_lenght(l));

    printf("FIND: %d\n", list_find(l, 800));

    printf("NTH: %d\n", list_nth(l, 1));

    printf("NTH: %d\n", list_nth(l, 2));


    return 0;

}
*/