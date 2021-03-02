#include "list.h"

void send_recv_client(int i, int sockfd)
{
	char send_buf[BUF_SIZE];
	char nick[BUF_SIZE];
	char recv_buf[BUF_SIZE];
	char aux[BUF_SIZE];
	char pass[BUF_SIZE];
	int nbyte_recvd;
	
	if (i == 0)
    {
		fgets(send_buf, BUF_SIZE, stdin);

		if (strcmp(send_buf , "quit\n") == 0)
			exit(0);
        else
		{	
			//printf("%s\n", send_buf);
			send(sockfd, send_buf, strlen(send_buf), 0);
		}
	}
    else
    {
		nbyte_recvd = recv(sockfd, aux, sizeof(aux), 0);
		aux[strcspn(aux, "\n")] = '\0';
		aux[nbyte_recvd] = '\0';

		if (strcmp(aux, "Accepted") == 0)
		{
			return;
		}
		else if (strcmp(aux, "Failed") == 0)
		{
			while(strcmp(aux, "Failed") == 0)
			{
				printf("Nick not available\n");
				printf("Nick: ");
				fgets(send_buf, BUF_SIZE, stdin);
				send(sockfd, send_buf, strlen(send_buf), 0);

				nbyte_recvd = recv(sockfd, aux, sizeof(aux), 0);
				//printf("AUX --> %s\n", aux);
				aux[nbyte_recvd] = '\0';
			}
			//printf("REACHED HERE\n");
			send(sockfd, send_buf, strlen(send_buf), 0);
			fgets(aux, PASS_SIZE, stdin);
			send(sockfd, aux, strlen(aux), 0);
			/*printf("AUX: %s", aux);
			printf("SEND_BUF: %s", send_buf);*/
		}
		/*else if (strcmp(aux, "WRONG_PASS") == 0)
		{
			while(strcmp(aux, "WRONG_PASS") == 0)
			{
				printf("Password is wrong\n");
				printf("Pass: ");
				fgets(send_buf, BUF_SIZE, stdin);
				send(sockfd, send_buf, strlen(send_buf), 0);

				nbyte_recvd = recv(sockfd, aux, sizeof(aux), 0);
				//printf("AUX --> %s\n", aux);
				aux[nbyte_recvd] = '\0';
			}
			//printf("REACHED HERE2\n");
			send(sockfd, send_buf, strlen(send_buf), 0);
			fgets(aux, PASS_SIZE, stdin);
			send(sockfd, aux, strlen(aux), 0);
			printf("AUX: %s", aux);
			printf("SEND_BUF: %s", send_buf);
		}
		else if (strcmp(aux, "REGISTER") == 0)
		{	
			printf("Nick: \n");
			//send(sockfd, nick, strlen(nick), 0);
			printf("Pass: \n");
			//send(sockfd, pass, strlen(pass), 0);

		}
		else if (strcmp(aux, "NEW_REGISTER") == 0)
		{
			printf("Nick: \n");
			//fgets(nick, BUF_SIZE, stdin);
			//send(sockfd, nick, strlen(nick), 0);
			printf("Pass: ");
			//fgets(nick, BUF_SIZE, stdin);
			//send(sockfd, pass, strlen(pass), 0);

		}*/
		else
		{
			nbyte_recvd = recv(sockfd, nick, sizeof(nick), 0);
			nick[strcspn(nick, "\n")] = '\0';
			nick[nbyte_recvd] = '\0';

			//printf("AUX %s\n", aux);


			printf("MSSG < %s >: %s\n" , aux, nick);
				
			fflush(stdout);

		}
	}
}
		
		
void connect_request_client(int *sockfd, struct sockaddr_in *server_addr)
{
	if ((*sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		perror("Socket");
		exit(1);
	}
	server_addr->sin_family = AF_INET;
	server_addr->sin_port = htons(PORT);
	server_addr->sin_addr.s_addr = inet_addr("127.0.0.1");
	memset(server_addr->sin_zero, '\0', sizeof server_addr->sin_zero);
	
	if(connect(*sockfd, (struct sockaddr *)server_addr, sizeof(struct sockaddr)) == -1) {
		perror("connect");
		exit(1);
	}
}
	
int main()
{
	int sockfd, fdmax, i;
	struct sockaddr_in server_addr;
	fd_set master;
	fd_set read_fds;
	char nick[NICK_SIZE];
	char start[BUF_SIZE];
	
	connect_request_client(&sockfd, &server_addr);
	FD_ZERO(&master);
        FD_ZERO(&read_fds);
        FD_SET(0, &master);
        FD_SET(sockfd, &master);
	fdmax = sockfd;

	printf("1 - LOGIN\n");
	printf("2 - REGISTER\n");

	fgets(start, BUF_SIZE, stdin);
	printf("NICK: \n");
	/*if (send(sockfd, nick, strlen(nick), 0) == -1)
	{
		perror("Send Failed");
	}*/

	send(sockfd, start, sizeof(start), 0);	
	
	while(1){
		read_fds = master;
		if(select(fdmax+1, &read_fds, NULL, NULL, NULL) == -1){
			perror("select");
			exit(4);
		}
		
		for(i=0; i <= fdmax; i++ )
			if(FD_ISSET(i, &read_fds))
			{
				send_recv_client(i, sockfd);
			}
	}
	printf("client-quited\n");
	close(sockfd);
	return 0;
}