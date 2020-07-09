#include <stdio.h>
#include <stdlib.h>
#include "queue.h"

struct Process *process_new(int n, int entry, int array[SIZE])
{
    struct Process *p = malloc(sizeof(struct Process));

    if (p != NULL)
    {
        p->pid = n;
        p->entry = entry;
        
        p->io = 5;
        p->inst = 0;
        p->count = 0;

        p->f = p;
        p->s = NULL;
    }

    return p;
}

struct Process *p_new(int n, int entry)
{
    struct Process *p = malloc(sizeof(struct Process));

    if (p != NULL)
    {
        p->pid = n;
        p->entry = entry;

        p->count = 0;
        p->inst = 0;
        p->io = 5;
    }

    return p;
}

struct Queue *queue_new(int n)
{
    struct Queue *queue = malloc(sizeof(struct Queue));

    if (queue != NULL)
    {
        queue->size = n;
        queue->front = queue->end = 0;
        queue->arr = malloc(queue->size * sizeof(int));

        for (int i = 0; i < queue->size; i++)
        {
            queue->arr[i] = -1;
        }
    }
    return queue;
}

bool isFull (struct Queue *queue)
{
    return queue->size == queue->end - queue->front;
}

bool isEmpty(struct Queue *queue)
{
    for (int i = 0; i < queue->size; i++)
    {
        if (queue->arr[i] != -1)
        {
            return false;
        }
    }

    return true;
}

int inc(int pos, struct Queue *queue)
{
    return (pos + 1) % queue->size;
}

void enqueue (struct Process *p, struct Queue *queue)
{
    if (isFull(queue))
        return;

    queue->arr[queue->end] = p->pid;
    queue->end = inc(queue->end, queue);
}

int dequeue(struct Queue *queue)
{
    int n = 0;

    if (isEmpty(queue))
        return -1;
    
    n = queue->arr[queue->front];
    queue->arr[queue->front] = -1;
    queue->front = inc(queue->front, queue);

    return n;
}

void removeProcess(struct Queue *queue, struct Process *p)
{
    for(int i = 0; i < queue->size; i++)
    {
        if(queue->arr[i] == p->pid)
        {
            queue->arr[i] = -1;
        }
    }
}

void setPid(struct Process *p, int pid)
{
    p->pid = pid;
}

void setEntry(struct Process *p, int entry)
{
    p->entry = entry;
}

int end(struct Queue *queue)
{
    return queue->end;
}

int getPid(struct Process *p)
{
    return p->pid;       
}

int getEntry(struct Process *p)
{
    return p->entry;
}

bool findProcess(struct Process *p, struct Queue *queue)
{
    for(int i = 0; i < queue->size; i++)
    {
        if(queue->arr[i] == p->pid)
        {
            return true;
        }
    }

    return false;
}

struct Process *getRunProcess(struct Queue *queue, int size,  struct Process *arr[size])
{
    return compare(size, arr, queue->arr[queue->front]);
}

void instantCpu(struct Process *p, struct Queue *run, struct Queue *ready)
{
    if (p->inst == QUANTUM)
    {
        enqueue(p, ready);
        dequeue(run);
        p->inst = 0;
    }
    else
        p->inst++;
}

void instantIo(struct Process *p, struct Queue *ready, struct list *blocked)
{
    if (p->io == 0)
    {
        if(list_remove(blocked, p))
        {
            enqueue(p, ready);
        }       
    }
    else if(p->io > 0)
    {
        p->io--;
    }
}

void printQueue(struct Queue *q)
{
    for (int i = 0; i < q->size; i++)
    {
        if (q->arr[i] != -1)
            printf("P%d ", q->arr[i]);
    }

    for (int i = 0; i< q->size; i++)
    {
        if(q->arr[i] == -1)
            printf("    ");
    } 

}