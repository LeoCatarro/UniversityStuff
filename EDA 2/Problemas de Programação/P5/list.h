#include <stdbool.h>

struct list; //DONE

//IMPLEMENTADOS PELO PROFESSOR
struct list *list_new(void);  //DONE
bool list_insert(struct list *list, int value); //DONE
void list_print(struct list *list); //DONE
void list_destroy(struct list *list); //DONE

//IMPLEMENTADOS POR MIM
void list_remove(struct list* list, int value); //DONE
int list_lenght(struct list* list); //DONE
bool list_empty(struct list* list);  //DONE
int list_find(struct list* list, int value); //DONE
int list_nth(struct list* list, int n); //DONE