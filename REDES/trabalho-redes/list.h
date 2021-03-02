#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <errno.h>


/*#ifndef LIST_H
#define LIST_H*/
#define BUF_SIZE 1024
#define PORT 5555
#define NICK_SIZE 10
#define PASS_SIZE 10
#define ROLE_SIZE 6
#define CLIENTS_FILE "clients.bin"

struct list;
struct node;

typedef struct client {
    char nick[NICK_SIZE];
    char pass[PASS_SIZE];
    char role[ROLE_SIZE];
    int socket;
    bool flag;
} Client;


Client *client_new(char *nick, char *pass, char *role, bool flag, int socket);

struct list *list_new();
bool list_insert(struct list *list, Client *client);
void list_print(struct list *list);
Client *parse_list(struct list *list, int i);
struct list *list_destroy(struct list *list);
bool list_empty(struct list *list);
Client *list_find(struct list *list, Client *client);
void search_sockets(struct list *list, Client *client);
Client *update_client_socket(struct list *list, Client *client);
Client *list_find_by_socket(struct list *list, int socket);
bool list_remove(struct list *list, Client *client);
int list_length(struct list *list);
