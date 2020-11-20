
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void)
{
    pid_t pid, pid2; 
    
    pid = fork();
    pid2 = fork(); 

    puts("Hello"); 
}


/* Pergunta: Veja quantos "Hello" obtém e porquê.


Tem-se o Pai (Pid_t) que vai gerar 2 Filhos (Pid e Pid2).
O Filho Pid vai ter um output de 0, no entanto, o Pid2 tem output de 1.
Logo vai-se ter um total de 4 "Hello"'s:

				Pai: Pid_T ("Hello")
			             /        \
				    /          \
			      pid ("Hello")   pid2 ("Hello")
				   /            \
			       Output 0      Output 1 ("Hello")
*/

				






 	





