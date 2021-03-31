#include <stdlib.h>
#include <stdbool.h>
#include "queue.h"
#include "fatal.h"


#define MinQueueSize ( 5 )

struct QueueRecord{
    int Capacity;
    int Front;
    int Rear;
    ElementType *Array;
};


/* FUNCOES AUXILIARES */
/* numero de elementos na fila */
int size( Queue Q ){
    int size = (Q->Capacity-Q->Front+Q->Rear) % Q->Capacity;
    return size;
}


/* Comentário: sinceramente nao percebi a utilidade da função */
/* indice do proximo elemento  */
int successor( int i, Queue Q ){
    printf("\t TODO() \t\n");
    return 0;
}


/* FUNCOES DE MANIPULACAO DE QUEUES */
Queue CreateQueue( int MaxElements ){
    Queue Q;

    if( MaxElements < MinQueueSize )
        Error( "Queue size is too small" );

    Q = malloc( sizeof( struct QueueRecord ) );
    if( Q == NULL )
        FatalError( "Out of space!!!" );

    Q->Array = malloc( sizeof( ElementType ) * MaxElements );
    if( Q->Array == NULL )
        FatalError( "Out of space!!!" );

    Q->Capacity = MaxElements+1;
    MakeEmptyQueue( Q );

    return Q;
}


void DisposeQueue( Queue Q ){
    if( Q != NULL ){
        free( Q->Array );
        free( Q );
    }
}


bool IsEmptyQueue( Queue Q ){
    return(Q->Front==Q->Rear ? true : false);
}


bool IsFullQueue( Queue Q ){
    int Qsize = size(Q);
    return (Q->Capacity == Qsize-1 ? true : false);
}


void MakeEmptyQueue( Queue Q ){
    Q->Front = 0;
    Q->Rear = 0;
}


ElementType Front( Queue Q ){
    return Q->Array[Q->Front];
}


void Enqueue( ElementType X, Queue Q ){
    if (size(Q) <= Q->Capacity-1)
    {
        Q->Array[Q->Rear] = X;
        Q->Rear++;
    }
    else
        Error("Queue is Full!");
}


ElementType Dequeue( Queue Q ){
    ElementType dequeued;

    if(!IsEmptyQueue(Q))
    {
        dequeued = Q->Array[Q->Front]; 
        Q->Front++;
        return dequeued;
    }
    else
        Error("Queue is Empty!");
}

//Função que recebe uma Queue e retorna uma queue invertida
Queue inverte( Queue Q )
{
    return Q;
}