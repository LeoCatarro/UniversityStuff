#include "list.h"

struct list
{
    int size;
    struct node *first;
};

struct node
{
    Client *client;
    struct node *next;
};

Client *client_new(char *nick, char *pass, char *role, bool flag, int socket)
{
	Client *client = malloc(sizeof(Client));

	if (client != NULL)
	{
		strcpy(client->nick, nick);
		strcpy(client->pass, pass);
        strcpy(client->role, role);
        client->socket = socket;
        client->flag = flag;
	}

    return client;
}

struct list *list_new()
{
    struct list *list = malloc(sizeof(struct list));

    if (list != NULL)
    {
        list->size = 0;
        list->first = NULL;
    }

    return list;
}

struct node *node_new(struct list *list, Client *client)
{
    struct node *node = malloc(sizeof(Client));

    if (node != NULL)
    {
        node->client = client;
        node->next = list->first;
    }

    return node;
}

bool list_insert(struct list *list, Client *client)
{
    struct node *node = node_new(list, client);
    struct node *aux = list->first;


    if (node != NULL)
    {
        list->first = node;
        list->first->next = aux;
        list->size++;

        return true;
    }

    return false;
}

Client *parse_list(struct list *list, int i)
{
    struct node *node = list->first;

    if (i == 0)
        return node->client;
    else
    {
        int j=0;
        while(j<i)
        {
            node = node->next;
            j++;
        }
        return node->client;
    }     
}

void list_print(struct list *list)
{
    struct node *node = list->first;

    while(node != NULL)
    {
        printf("NICK: %s || PASS: %s || ROLE: %s || SOCKET: %d\n", node->client->nick, node->client->pass, node->client->role, node->client->socket);
        node = node->next;
    }

    /*
    if (node != NULL)
    {
        printf("NICK: %s || PASS: %s\n", node->client->nick, node->client->pass);
        node = node->next;
    }

    for (int i = 1; i < list->size - 1; i++)
    {
        if (node == NULL)
        {
            return;
        }
        
        printf("A NICK: %s || PASS: %s\n", node->client->nick, node->client->pass);
        node = node->next;
    }
    
    if(node != NULL)
    {
        printf("NICK: %s || PASS:%s\n", node->client->nick, node->client->pass);
    }*/
}

struct list *list_destroy(struct list *list)
{
    struct node *node = list->first;

    while (node != NULL)
    {
        struct node *next = node->next;

        free(node);

        node = next;
    }

    free(list);

    return list;
}

bool list_empty(struct list *list)
{
    return list->first == NULL;
}

Client *list_find(struct list *list, Client *client)
{
    struct node *node = list->first;


    while (node != NULL)
    {
        if (strcmp(node->client->nick, client->nick) == 0)
        {
            return node->client;
        }

        node = node->next;
    }

    return NULL;
}

void search_sockets(struct list *list, Client *client)
{
    struct node *node = list->first;

    while (node != NULL)
    {
        if(node->client->socket == client->socket)
        {
            node->client->socket++;
        }
    }
}

Client *update_client_socket(struct list *list, Client *client)
{
    struct node *node = list->first;

    while (node != NULL)
    {
        if (node->client->nick == client->nick)
        {
            node->client->socket = client->socket;
            return  node->client;
        }

        node = node->next;
    }

    return NULL;
}

Client *list_find_by_socket(struct list *list, int socket)
{
    struct node *node = list->first;

    while (node != NULL)
    {
        if (node->client->socket == socket)
        {
            return node->client;
        }

        node = node->next;
    }

    return NULL;
}

int list_length(struct list *list)
{
    return list->size;
}

bool list_remove(struct list *list, Client *client)
{
    struct node *node = list->first;
    struct node *prev;

    if (list->first->client->nick == client->nick)
    {
        list->first = node->next;

        list->size--;

        free(node);

        return true;
    }


    for (int i = 0; i < list->size; i++)
    {
        if (node->client->nick == client->nick)
        {
            prev->next = node->next;

            list->size--;
            free(node);

            return true;
        }

        prev = node;
        node = node->next;
    }

    return false;
}