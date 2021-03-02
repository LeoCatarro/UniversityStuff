#include "list.h"


FILE *file_open(char *file_name)
{
    //abre file
    FILE *f = fopen(file_name, "rb+");

    //caso file exista retorna-o
    if(f != NULL)
        return f;

    //caso file nao exista cria
    f = fopen(file_name, "wb+");

    return f;
}

void file_close(FILE *file)
{
    fclose(file);
}

void send_to_all(struct list *list, int j, int i, int sockfd, int nbytes_recvd, char *recv_buf, fd_set *master)
{
	if (FD_ISSET(j, master)){
		if (j != sockfd && j != i) {
			Client *client = list_find_by_socket(list, i);
			printf("[Server]: NICK %s Sent The MSSG : %s\n", client->nick, recv_buf);
			send(j, client->nick, sizeof(client->nick), 0);
			send(j, recv_buf, nbytes_recvd, 0);
		}
	}
}

void load_from_disk(FILE *file, struct list *list)
{
	file = file_open(CLIENTS_FILE);

	int i = 0;

	printf("[Server]: Loading Clients...\n");

	Client *client = client_new("", "", "", true, 0);

	fseek(file, 0, SEEK_SET);

	while(fread(client, sizeof(Client), 1, file) == 1)
	{	
		if(strcmp(client->nick, "") == 0)
			break;

		printf("[Server]: NICK: %s || PASS: %s SOCKET: %d\n", client->nick, client->pass, client->socket);

		list_insert(list, client_new(client->nick, client->pass, client->role, client->flag, client->socket));
		
		printf("[Server]: Load Sucessfull\n");
		printf("------------------------------\n");

		i++;
		fseek(file, i * sizeof(Client), SEEK_SET);
	}
	printf("[Server]: # LIST LOADED #\n");
	list_print(list);
	printf("####################################\n");

	file_close(file);
}


void copy_to_disk(FILE *file, struct list *list)
{
	file = file_open(CLIENTS_FILE);

	printf("[Server]: # LISTA ATUAL #\n");
	list_print(list);
	printf("####################################\n");

	Client *client = client_new("", "", "", true, 0);

	for (int i = 0; i < list_length(list); i++)
	{
		client = parse_list(list, i);
		printf("[Server]: Add Client: %s | %s | %s | %d\n", client->nick, client->pass, client->role, client->socket);

		if(fseek(file, i * sizeof(Client), SEEK_SET) == 0)
		{
			if(fwrite(client, sizeof(Client), 1, file) == 1)
					printf("[Server]: Copy Sucessfull\n");
		}
	}
	printf("[Server]: # LISTA COPIADA PARA O DISCO #\n");
	list_print(list);
	printf("####################################\n");

	file_close(file);

	return;
}
		
void send_recv_server(FILE *file, struct list *list, int i, fd_set *master, int sockfd, int fdmax)
{
	int nbytes_recvd, j;
	char recv_buf[BUF_SIZE], buf[BUF_SIZE];

	
	if ((nbytes_recvd = recv(i, recv_buf, BUF_SIZE, 0)) <= 0) {
		if (nbytes_recvd == 0) {
			printf("socket %d hung up\n", i);
		}else {
			perror("recv");
		}
		close(i);
		FD_CLR(i, master);
	}
	else
	{ 	
		recv_buf[nbytes_recvd] = '\0';

		//printf("BUFFER --> %s\n", recv_buf);
		
		if(strcmp(recv_buf, "UPDATE_SERVER\n") == 0)
		{
			printf("[Server]: UPDATING SERVER...\n");
			copy_to_disk(file, list);
			return;
		}
		else if(strcmp(recv_buf, "Accepted\n") == 0)
		{
			printf("FINNALY!!!");
		}
		
		for(j = 0; j <= fdmax; j++){
			send_to_all(list, j, i, sockfd, nbytes_recvd, recv_buf, master );
		}
	}	
}
		
void connection_accept(FILE *file, struct list *list, fd_set *master, int *fdmax, int sockfd, struct sockaddr_in *client_addr)
{
	socklen_t addrlen;
	int newsockfd, nbyte_recvd;
	char nick[NICK_SIZE];
	char pass[PASS_SIZE];
	
	addrlen = sizeof(struct sockaddr_in);
	if((newsockfd = accept(sockfd, (struct sockaddr *)client_addr, &addrlen)) == -1) {
		perror("[Server]:Accept");
		exit(1);
	}else {
		FD_SET(newsockfd, master);
		if(newsockfd > *fdmax){
			*fdmax = newsockfd;
		}

		printf("[Server]: New connection from %s on port %d with socket %d\n",inet_ntoa(client_addr->sin_addr), ntohs(client_addr->sin_port), newsockfd);

		nbyte_recvd = recv(newsockfd, nick, sizeof(nick), 0);
		nick[strcspn(nick, "\n")] = '\0';
		nick[nbyte_recvd] = '\0';
		nbyte_recvd = recv(newsockfd, pass, sizeof(pass), 0);
		pass[strcspn(pass, "\n")] = '\0';
		pass[nbyte_recvd] = '\0';

		Client *client = client_new(nick, pass, "admin", true, newsockfd);
		Client *client2 = client_new("", "", "", true, newsockfd);

		client2 = list_find(list, client);

		if(client2 == NULL)
		{
			send(newsockfd, "Accepted", sizeof("Accepted"), 0);

			list_insert(list, client);
			copy_to_disk(file, list);
			list_print(list);

			fflush(stdout);	

			return;

		}
		else
		{	
			while(strcmp(client2->nick, client->nick) == 0)
			{
				send(newsockfd, "Failed", sizeof("Failed"), 0);
				
				nbyte_recvd = recv(newsockfd, nick, sizeof(nick), 0);
				nick[strcspn(nick, "\n")] = '\0';
				nick[nbyte_recvd] = '\0';

				client = client_new(nick, "", "admin", true, newsockfd);
			}
			send(newsockfd, "Accepted", sizeof("Accepted"), 0);

			nbyte_recvd = recv(newsockfd, nick, sizeof(nick), 0);
			nick[strcspn(nick, "\n")] = '\0';
			nick[nbyte_recvd] = '\0';

			nbyte_recvd = recv(newsockfd, pass, sizeof(pass), 0);
			pass[strcspn(pass, "\n")] = '\0';
			pass[nbyte_recvd] = '\0';

			client = client_new(nick, pass, "admin", true, newsockfd);

			list_insert(list, client);
			copy_to_disk(file, list);
			list_print(list);
			
			fflush(stdout);		

			return;	
		}
	}
}
	
void connect_request_server(int *sockfd, struct sockaddr_in *my_addr)
{
	int yes = 1;
		
	if ((*sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		perror("[Server]: Socket");
		exit(1);
	}
		
	my_addr->sin_family = AF_INET;
	my_addr->sin_port = htons(PORT);
	my_addr->sin_addr.s_addr = INADDR_ANY;
	memset(my_addr->sin_zero, '\0', sizeof my_addr->sin_zero);
		
	if (setsockopt(*sockfd, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int)) == -1) {
		perror("[Server]: setsockopt");
		exit(1);
	}
		
	if (bind(*sockfd, (struct sockaddr *)my_addr, sizeof(struct sockaddr)) == -1) {
		perror("[Server]: Unable to bind");
		exit(1);
	}
	if (listen(*sockfd, 10) == -1) {
		perror("[Server]: listen");
		exit(1);
	}
	printf("\n[Server]: TCPServer Waiting for client on port %d\n", PORT);
	fflush(stdout);
}

int main()
{
	FILE *file = file_open(CLIENTS_FILE);

	fd_set master;
	fd_set read_fds;
	int fdmax, i;
	int sockfd = 0;
	struct sockaddr_in my_addr, client_addr;

	struct list *list = list_new();

	FD_ZERO(&master);
	FD_ZERO(&read_fds);
	connect_request_server(&sockfd, &my_addr);
	FD_SET(sockfd, &master);
	
	fdmax = sockfd;

	load_from_disk(file, list);		//Load dos clients já criados anteriormente
	
	while(1){
		read_fds = master;
		if(select(fdmax+1, &read_fds, NULL, NULL, NULL) == -1)
		{
			perror("select");
			exit(4);
		}
		
		for (i = 0; i <= fdmax; i++)
		{
			if (FD_ISSET(i, &read_fds))
			{
				if (i == sockfd)
                {
					connection_accept(file, list, &master, &fdmax, sockfd, &client_addr);
                }
                else
				{
					send_recv_server(file, list, i, &master, sockfd, fdmax);
				}	
			}
		}
	}
	return 0;
}