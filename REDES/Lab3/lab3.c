#include <stdio.h>
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <string.h>
#include <netdb.h> 
#define PORT 13
#define h_addr h_addr_list[0]

int main(int argc, char const *argv[])
{
	int sock_x, num_bytes, length = 100, flags;
	char buffer_de_saida[100];
	struct hostent *host_entity;
	struct sockaddr_in serv_addr;

	serv_addr.sin_family = AF_INET; 
    serv_addr.sin_port = htons(PORT);

	host_entity = gethostbyname("time.nist.gov");
	bcopy((char*)host_entity -> h_addr, (char*)&serv_addr.sin_addr.s_addr, host_entity ->h_length);
	sock_x = socket(AF_INET, SOCK_STREAM, 0);
	connect(sock_x, (struct sockaddr*)&serv_addr, sizeof(serv_addr));
	num_bytes = recv(sock_x, buffer_de_saida, length, flags);
	printf("os dados recebidos (%d) sao: %s\n", num_bytes, buffer_de_saida);
	return 0;
}