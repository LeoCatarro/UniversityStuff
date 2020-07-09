# include <stdbool.h>
# include "list.h"

#define SIZE_S 19
#define QUANTUM 3

struct Queue
{
    int size;
    int *arr;
    int front;
    int end;
};

struct Process 
{
    int pid;
    int entry;
    int io;
    int inst;
    int count;
    struct Process *f, *s;
};

struct Queue *queue_new(int n);
struct Process *process_new(int n, int entry);
bool isFull(struct Queue *queue);
bool isEmpty(struct Queue *queue);
void enqueue (struct Process *p, struct Queue *queue);
int dequeue (struct Queue *queue);
int end (struct Queue *queue);
int getPid(struct Process *p);
int getEntry(struct Process *p);
void printQueue(struct Queue *q);
struct Process *getRunProcess(struct Queue *queue,int size, struct Process *arr[size]);
void instantCpu(struct Process *p, struct Queue *run, struct Queue *ready);
struct Process *compare(int size, struct Process *arr[size], int pid);
void moveBlocked(struct list *blocked, struct Process *p);
void printProcess(struct Process *p);
void instantIo(struct Process *p, struct Queue *ready, struct list *blocked);
int dequeueReady(struct Queue *ready, struct Process *p);
void setPid(struct Process *p, int pid);
void setEntry(struct Process *p, int entry);
struct Process *p_new(int n, int entry);
void removeProcess(struct Queue *queue, struct Process *p);
bool findProcess(struct Process *p, struct Queue *queue);