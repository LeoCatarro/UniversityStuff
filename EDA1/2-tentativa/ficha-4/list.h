typedef int ElementType;

#ifndef _List_H
#define _List_H

typedef struct list;


struct list *list_new(void);  
bool list_insert(struct list *list, int value); 
void list_print(struct list *list); 
void list_destroy(struct list *list); 


void list_remove(struct list* list, int value); 
int list_lenght(struct list* list); 
bool list_empty(struct list* list);  
int list_find(struct list* list, int value); 
int list_nth(struct list* list, int n); 

#endif
