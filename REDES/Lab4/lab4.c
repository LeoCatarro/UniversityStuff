#include <stdio.h>
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <string.h>
#include <netdb.h>
#include <time.h> 
#define PORT 1300

//Função para devolver a data
void  strdate(char *buffer, int len)
{
    time_t now = time(NULL);
    struct tm *ptm = localtime(&now);

    if(ptm == NULL)
    {
        puts("The localtime() function failed");
        return;
    }

    strftime(buffer, len, "%c\n", ptm);
}



int main(int argc, char const *argv[]) 
{ 

    struct sockaddr_in address; 
    char buffer[1024] = {0};
    int valread, opt=1, server_fd;
    char *hello = "Hello from server"; 
    int addrlen = sizeof(address); 

    //Criação do Socket
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);


    //Ajuda na manipulação de opções para o socket
    setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt)); 

    address.sin_family = AF_INET; 
    address.sin_addr.s_addr = INADDR_ANY; 
    address.sin_port = htons( PORT );


    //Coloca a porta 1300 no Socket
    int s_bind = bind(sockfd, (struct sockaddr *)&address, sizeof(address)); 


    //Poe o server em modo passivo(espera que o client se conecte ao servidor)
    int listen_s = listen(sockfd, 3);


    //Extrai a 1a conexão da queue
    int new_socket = accept(sockfd, (struct sockaddr *)&address, (socklen_t*)&addrlen);


    strdate(buffer, 1024);


    send(new_socket , buffer , strlen(buffer)+1 , 0 ); 
    printf("Message sent\n"); 
} 
