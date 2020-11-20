#include <stdio.h>
#include <stdlib.h>

#include "queue.h"



struct queue* newQueue(unsigned capacity)
{
	struct queue* queue = (struct queue*) malloc(sizeof(struct queue));
	queue -> capacity = capacity;
	queue -> size = 0;
	queue -> front = 0;
	queue -> rear = capacity - 1;
	queue -> elements = (int*) malloc(sizeof(int) * queue -> capacity);

	return queue;
};


bool isFull(struct queue* queue)
{
	if(queue -> size == queue -> capacity)
	{
		return true;
	}
	return false;	
}


bool isEmpty(struct queue* queue)
{
	if(queue->size == 0)
	{
		return true;
	}
	return false;	
}


void enqueue(struct queue* queue, int elemento)
{
	if(isFull(queue) == true)
	{
		return;
	}
	queue->rear = (queue->rear + 1)% (queue->capacity); 
    queue->elements[queue->rear] = elemento; 
    queue->size = queue->size + 1; 
    printf("%d enqueued to queue\n", elemento);
}


int dequeue(struct queue* queue)
{
	if(isEmpty(queue) == true)
	{
		return -1;
	}
	
	int elemento = queue->elements[queue->front];
	queue->front = (queue->front + 1) % queue->capacity;	
	queue->size = queue->size - 1;

	return elemento; 
}


int front(struct queue* queue)
{
	if(isEmpty(queue) == true)
	{
		return -1;
	}
	return queue->elements[queue->front];	
}


int rear(struct queue* queue)
{
	if(isEmpty(queue) == true)
	{
		return -1;
	}
	return queue->elements[queue->rear];	
}


/*
//TESTES Á QUEUE
int main(void)
{
	struct queue *queue = newQueue(50);

	enqueue(queue, 20);
	enqueue(queue, 30);
	enqueue(queue, 40);
    enqueue(queue, 50);

    printf("%d\n", dequeue(queue)); 

	return 0;
}
*/
