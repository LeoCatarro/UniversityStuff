#include <stdbool.h>

struct list
{
    int size;
    struct node *first;
};

struct node
{
    struct Process *p;
    struct node *next;
};

struct list *list_new();
struct Process *list_insert(struct list *list, struct Process *p);
void list_print(struct list *list);
struct list *list_destroy(struct list *list);
bool list_empty(struct list *list);
bool list_find(struct list *list, struct Process *p);
bool list_remove(struct list *list, struct Process *p);
int list_length(struct list *list);
struct node *first(struct list *l);
struct node *next(struct node *node);
struct Process *nodeProcess(struct node *node);
