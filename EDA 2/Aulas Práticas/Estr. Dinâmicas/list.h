
typedef struct list list_t;
struct list *list_new();
bool list_insert(struct list *list, int value);
void list_print(list);
void list_destroy(list);