typedef int ElementType;

#ifndef _Queue_h
#define _Queue_h

struct QueueRecord;
typedef struct QueueRecord *Queue;

Queue CreateQueue( int MaxElements );
void DisposeQueue( Queue Q );

bool IsEmptyQueue( Queue Q );
bool IsFullQueue( Queue Q );
void MakeEmptyQueue( Queue Q );

ElementType Front( Queue Q );
void Enqueue( ElementType X, Queue Q );
ElementType Dequeue( Queue Q );
void PrintQueue(Queue Q);

#endif
