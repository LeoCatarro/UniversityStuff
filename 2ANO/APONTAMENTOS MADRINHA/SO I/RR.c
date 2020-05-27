#include <stdio.h>
#include <stdlib.h>

#define Queue_base_size 50

//class Proceco
struct proceco{
    int id;
    int t_chegada;
    int t_servico;
};

struct proceco *make_proceco(int i, int t1, int t2){
    struct proceco *proc  = malloc(sizeof(struct proceco));
    proc->id = i;
    proc->t_chegada = t1;
    proc->t_servico = t2;
    return proc;
}

//class Queue
struct queue{
    int header;
    int footer;
    struct proceco *arr[Queue_base_size];
};

struct queue *new_queue(){
    struct queue *temp  = malloc(sizeof(struct queue));
    temp->header = 0;
    temp->footer = 0;
    return temp;
}

int is_empty(struct queue *q){
    return q->header == q->footer;
}

void add(struct queue *q, struct proceco *item){
    int footer = q->footer;
    q->arr[footer] = item;
    q->footer += 1;
    if (q->footer >= Queue_base_size)
        q->footer = 0;
}

struct proceco *dequeue(struct queue *q){
    if (is_empty(q))
        return NULL;
        
    int header = q->header;
    q->header += 1;
    if (q->header >= Queue_base_size)
        q->header = 0;
    return q->arr[header];
}

struct proceco *next(struct queue *q){
    if(is_empty(q))
        return NULL;
    return q->arr[q->header];
}

//main
int main( void ){

    struct queue *ready = new_queue();
    
    struct proceco *run = NULL;
    
    struct queue *todo_list = new_queue();
    
    int time = 0;
    
    int t_chegada = 0;
    int t_servico = 0; 
    int c = 1;
    
    //input
    while (scanf("%d %d", &t_chegada, &t_servico) != EOF){
        
        if(t_servico == 0)
            break;    
        
        add(todo_list, make_proceco(c, t_chegada, t_servico));
        
        c += 1;
        
    }
    
    if(is_empty(todo_list))
        return 0;
    
    while( !(is_empty(todo_list) && is_empty(ready) && run == NULL) ){

        //chegada de procecos
        if(!is_empty(todo_list)){
            
            struct proceco *temp = next(todo_list);
            
            while(temp->t_chegada <= time){
                
                temp = dequeue(todo_list);
                add(ready, temp);
                
                if(is_empty(todo_list))
                    break;
                
                temp = next(todo_list);
                
            }
        
        }

        if(run != NULL){
            
            run->t_servico -= 1;            
            
            //tira do run
            if(run->t_servico > 0)
                add(ready, run);

            run = NULL;
        
        }

        //mete o procimo
        if (!is_empty(ready)){
            
            run = dequeue(ready);
            
        }

        //output
        if(run != NULL){
            printf("%2d %2d\n", time, run->id);
        }else{
            printf("%2d  0\n", time);
        }

        //timer
        time += 1;
        
    }
    
    return 0;
    
}
