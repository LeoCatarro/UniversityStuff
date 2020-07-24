#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "queue.h"

#define P_SIZE 200
#define DATA_SIZE 10

#define MAX_SIZE 500

int count = 0;
int iterator = 0;
int var = -1;

typedef struct program
{
    int mem[P_SIZE];
    int arr[DATA_SIZE];
    struct Process *process;
}Program;

Program *program_new(int mem[P_SIZE], int id, int entry)
{
    Program *p = malloc(sizeof(Program));

    struct Process *process = p_new(id, entry);

    if(p != NULL)
    {
        for(int i = 0; i < P_SIZE; i++)
        {
            p->mem[i] = mem[i];
        }

        for(int i = 0; i < DATA_SIZE; i++)
        {
            p->arr[i] = 0;
        }

        p->process = process;

        p->process->s = NULL;
    }

    return p;
}

int nLines(FILE *fp, char input[MAX_SIZE][4])
{
    int count = 0;  // Line counter (result) 
    char c[4];  // To store a character read from file
    c[4] = '\0';

    char a[3];
    a[3] = '\0';

    // Check if file exists 
    if (fp == NULL) 
    { 
        printf("Could not open file %s", "/home/ds/Desktop/SO_2/test.txt"); 
        return 0; 
    } 
  
    int i = 0;
  
    while(scanf("%s %s", c, a) != EOF)
    {
        if(strcmp(c, "INI") == 0)
        {
            count++;
        }
        
        strcpy(input[i], c);
        strcpy(input[i+1], a);


        i+=2;
    }
  
    return count;
}
void moveBlocked(struct list *blocked, struct Process *p)
{
    list_insert(blocked, p);
}

void blockedReady(struct list *blocked, struct Queue *ready)
{
    struct node *node = first(blocked);
    struct Process *p;

    while (node != NULL)
    {
        p = nodeProcess(node);
        instantIo(p, ready, blocked);
        node = next(node);  
    }
}

/*
compara um PID de um Processo do array de Processos com a Queue de int PIDs
Se este existir retorna o Processo correspondente a esse PID
*/

struct Process *compare(int size, struct Process *arr[size], int pid)
{

    for (int i = 0; i < size; i++)
    {
        if (getPid(arr[i]) == pid)
        {
            return arr[i];
        }
    }

    return arr[0];
}

void print(struct Queue *ready, struct Queue *run, struct list *blocked, int count)
{
    printf("%d ", count);

    if(var != -1)
        printf(" stdout %d", var);
    else
        printf(" stdout ");

    printf(" READY "); 

    printQueue(ready);

    printf("RUN ");

    printQueue(run);

    printf("BLOCKED ");

    list_print(blocked);

    printf("\n");
}

void updateScheduler(struct Queue *run, struct Queue *ready, struct list *blocked, int lines, struct Process *arr[lines], int counter, int aux)
{
    struct Process *p;

    /*if(list_find(blocked, arr[counter])== true)
        list_remove(blocked, arr[counter]);
                
    else if(findProcess(arr[counter], ready) == true)
        dequeue(ready);

    else if(findProcess(arr[counter], run) == true)
        dequeue(run);*/

    //Entrar no Run inicial
    if (isEmpty(run) == true && isEmpty(ready) == false)
    {
        enqueue(compare(lines, arr, dequeue(ready)), run);
        p = getRunProcess(run, lines, arr);

        //printf("1 P%d\n", p->pid);

        for(int i = 0; i < lines; i++)
        {
            if(p->pid == arr[i]->pid)
            {
                iterator = i;
            }
        }
    }
    //remover do Run se tCPU == 0 e por no READY
    if (isEmpty(run) == false)
    {
        //printf("PROCESS ON RUN P%d\n", p->pid);
        instantCpu(p, run, ready);
    }
    //Se foi removido entao tem de entrar logo outro
    if (isEmpty(run) == true && isEmpty(ready) == false)
    {
        enqueue(compare(lines, arr, dequeue(ready)), run);
        p = getRunProcess(run, lines, arr);

        //printf("2 P%d\n", p->pid);

        for(int i = 0; i < lines; i++)
        {
            if(p->pid == arr[i]->pid)
            {
                iterator = i;
            }
        }

        //caso em que entrou um processo no RUN logo apos a remocao de outro e tem de se decrementar o tCPU
        if (isEmpty(run) == false)
        {
            instantCpu(p, run, ready);
        }
    }

    //passagem Blocked -> Ready
    if(list_empty(blocked) == false)
    {
        blockedReady(blocked, ready);

        //Se foi removido do Blocked->Ready e RUN empty--> entao tem de entrar logo outro
        if (isEmpty(run) == true && isEmpty(ready) == false)
        {
            enqueue(compare(lines, arr, dequeue(ready)), run);
            p = getRunProcess(run, lines, arr);

            //printf("3 P%d\n", p->pid);

            for(int i = 0; i < lines; i++)
            {
                if(p->pid == arr[i]->pid)
                {
                    iterator = i;
                }
            }

            //caso em que entrou um processo no RUN logo apos a remocao de outro e tem de se decrementar o tCPU
            if (isEmpty(run) == false)
            {
                instantCpu(p, run, ready);
            }
        }
    }
    //Entrar no READY com entry
    for (int i = aux; i < lines; i++)
    {
        if (getEntry(arr[i]) == count)
        {
            enqueue(arr[i], ready);
            aux++;
        }
    }

    //printf("%d\n", iterator);

    print(ready, run, blocked, count);

    count++;    
}



bool updateProgram(int lines, Program *program[lines], struct list *blocked, struct Queue *ready, struct Queue *run, struct Process *arr[lines], int counter, int aux1)
{
    int aux = -1;

    int n = 0;

    Program *p = program[iterator];

    for(int i = p->process->count; i < P_SIZE - 1;)
    {    
        p = program[iterator];

        for(int i = 0; i < P_SIZE -1; i++)
        {
            if(p->mem[i] == -1)
                n = i;
        }

        switch(p->mem[i])
        {
            case -1:    i = P_SIZE;
                        if(findProcess(p->process, run) == true)
                        {   
                        i = P_SIZE;
                        dequeue(run);
                        }
                        else if(findProcess(p->process, ready) == true)
                        {
                        i = P_SIZE;
                        dequeue(ready);
                        } 
                        updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                        break;

            case 0: if(findProcess(p->process, run) == true)
                    {
                        aux = p->mem[i+1];
                        p->arr[aux] = 0;
                        p->process->count += 2;
                        i += 2;
                    }
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                    break;

            case 1: if(findProcess(p->process, run) == true)
                    {
                        aux = p->mem[i+1];
                        p->arr[aux]++;
                        p->process->count += 2;
                        i += 2;
                    }
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                    break;

            case 2: if(findProcess(p->process, run) == true)
                    {
                        aux = p->mem[i+1];
                        p->arr[aux]--;
                        i += 2;
                    }
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                    break;

            case 3: p->process->f = p->process;
                    if(p->process->s == NULL)
                    {
                        p->process->s = p->process->f;
                        p->mem[i+1] = 0;
                    }
                    else
                    {
                        p->process->f = p->process->s;
                        p->process->s = p->process;
                        p->mem[i+1] = p->process->pid;
                    }
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                    i += 2;
                    //chamar funcao para atualizar o escalonador com novo processo
                    break;

            case 4: if(findProcess(p->process, run) == true)
                    {
                        aux = p->mem[i+1];
                        p->process->count += aux;
                        i += aux;
                    }
                    if(i >= n)
                        printf("Erro de segmentacao do processo P%d\n", p->process->pid);
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);      
                    break;           

            case 5: if(findProcess(p->process, run) == true)
                    {
                        aux = p->mem[i+1];
                        p->process->count -= aux;
                        i -= aux;
                    }
                    if(i < 0)
                        printf("Erro de segmentacao do processo P%d\n", p->process->pid);
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);      
                    break;

            case 6: if(findProcess(p->process, run) == true)
                    {
                        //printf("test1\n");
                        p->process->io = 5;
                        if(isEmpty(run) == false)
                            dequeue(run);    
                        moveBlocked(blocked, p->process);
                        p->process->count += 2;
                        i += 2;
                    }
                    else if(findProcess(p->process, ready) == true)
                    {
                        //printf("test\n");

                        p->process->io = 5;
                        if(isEmpty(ready) == false)
                            dequeue(ready);    
                        moveBlocked(blocked, p->process);
                        p->process->count += 2;
                        i += 2;
                    }
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                    break;

            case 7: if(findProcess(p->process, run) == true)
                    {
                        aux = p->mem[i+1];
                        if(aux == 0)
                        {
                            p->process->count += 2;
                            i += 2;
                        }    
                    }
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);        
                    break;
                        
            case 8: if(findProcess(p->process, run) == true)
                    {
                        aux = p->mem[i+1];
                        var = p->arr[aux];
                        p->process->count += 2;
                        i += 2;
                    }
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                    break;

            case 9: if(findProcess(p->process, run) == true)
                    {   
                        i = P_SIZE;
                        dequeue(run);
                    }
                    else if(findProcess(p->process, ready) == true)
                    {
                        i = P_SIZE;
                        dequeue(ready);
                    }    
                    updateScheduler(run, ready, blocked, lines, arr, counter, aux1);
                    break;

            default: return false;

        }
    }

    return true;
}


int main(void) 
{ 
    char input[MAX_SIZE][4];
    int temp[P_SIZE];
    
    for(int i = 0; i < MAX_SIZE; i++)
    {
        strcpy(input[i], "-");
    }
    
    for(int i = 0; i < P_SIZE; i++)
    {
        temp[i] = -1;
    }


    FILE *fp = fopen("/home/leocatarro/Desktop/43025_43071/teste1.txt", "r"); 
    int lines = nLines(fp, input);    

    struct Queue *run = queue_new(lines);
    struct Queue *ready = queue_new(lines);
    struct list *blocked = list_new();
    
    Program *array[lines];

    int nPrograms = 0;
    int aux1 = 0;

    int i = 0;
    int j = 0;

    while(i < MAX_SIZE)
    {
        if(strcmp(input[i], "-") != 0)
        {
            j = 0;

            if(strcmp(input[i], "INI") == 0)
            {
                i++;

                aux1 = atoi(input[i++]);
            }
            
            while(strcmp(input[i], "INI") != 0 && strcmp(input[i], "-") != 0)
            {
                if(strcmp(input[i], "ZER") == 0) temp[j] = 0;

                else if(strcmp(input[i], "INC") == 0) temp[j] = 1;

                else if(strcmp(input[i], "DEC") == 0) temp[j] = 2;

                else if(strcmp(input[i], "FRK") == 0) temp[j] = 3;

                else if(strcmp(input[i], "JFW") == 0) temp[j] = 4;

                else if(strcmp(input[i], "JBK") == 0) temp[j] = 5;

                else if(strcmp(input[i], "DSK") == 0) temp[j] = 6;

                else if(strcmp(input[i], "JIZ") == 0) temp[j] = 7;

                else if(strcmp(input[i], "PRT") == 0) temp[j] = 8;

                else if(strcmp(input[i], "HLT") == 0) temp[j] = 9;

                else
                    temp[j] = atoi(input[i]);        

                i++;
                j++;
            }

            Program *program = program_new(temp, nPrograms, aux1);

            for(int i = 0; i < P_SIZE; i++)
            {
                temp[i] = -1;
            }

            array[nPrograms] = program;

            nPrograms++;
        }
        else
            i++;
    }

    struct Process *arr[lines];

    for(int i = 0;  i < lines; i++)
    {
        arr[i] = array[i]->process;
    }

    int aux = 0;
    int counter = 0;


    for (int i = 0; i < lines; i++)
    {
        if (getEntry(arr[i]) == count)
        {
            enqueue(arr[i], ready);
            aux = i;
            aux++;
        }
    }

    printf("Round Robin Quantum %d\n", QUANTUM);

    while (isEmpty(ready) == false || isEmpty(run) == false || list_empty(blocked) == false)
    {
        //printf("%d\n", count);

        if(updateProgram(lines, array, blocked, ready, run, arr, counter, aux) == false)
        {
            printf("Instrução invalida\n");
            break;
        }
        
    }

    list_destroy(blocked);
    fclose(fp);
}
