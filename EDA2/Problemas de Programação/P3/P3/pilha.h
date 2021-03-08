#include <stdbool.h>

//INTERFACE DA PILHA

struct stack;
struct stack* stackNew(int size);  //FEITO
bool isEmpty(struct stack *pilha);  //FEITO
void push(struct stack* pilha, int element);  //FEITO
int pop(struct stack *pilha);  //FEITO
int top(struct stack *pilha);  // FEITO

