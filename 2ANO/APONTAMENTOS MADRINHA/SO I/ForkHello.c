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
