
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void)
{
   pid_t pid, pid2;
  
   pid = fork();
   if (pid > 0) {
     pid2 = fork();
     puts("Hello");
   }

  puts("Hello"); 
}
