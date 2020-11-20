#include <stdbool.h>


struct processo
{
	int PID;
	int t_inicio;
	int t_cpu;
	int tEspera_IO;
};

struct queue
{
	int front;
	int rear;
	int size;
	int *elements;
};

struct queue* newQueue(int capacity);
bool isFull(struct queue* queue);
bool isEmpty(struct queue* queue);
void enqueue(struct queue* queue, int elemento);
int dequeue(struct queue* queue);
int front(struct queue* queue);
int rear(struct queue* queue);