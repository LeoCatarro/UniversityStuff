typedef char ElementType;

#ifndef _Stack_h
#define _Stack_h


struct StackRecord;
typedef struct StackRecord *Stack;


Stack CreateStack( int MaxElements );
void DisposeStack( Stack S );

int IsEmpty( Stack S );
int IsFull( Stack S );
void MakeEmpty( Stack S );

void Push( ElementType X, Stack S );
ElementType Top( Stack S );
ElementType Pop( Stack S );

void PrintStack(Stack S);

int ParentesisMatch( char *s );
int ParentesisMatchUsingStack( char *s );

#endif  /* _Stack_h */