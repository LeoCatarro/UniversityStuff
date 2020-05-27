#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>

void *print_message_function( void *ptr );
   int  output;


int main()
{
     pthread_t thread1, thread2;
     int  x1,x2, soma;
     int  iret1, iret2;
     void* resultado;

     /* Create independent threads each of which will execute function */
     x1=1; 
     iret1 = pthread_create( &thread1, NULL, print_message_function, (void *) &x1 );
     x2=2;
     iret2 = pthread_create( &thread2, NULL, print_message_function, (void *) &x2);
     
     pthread_join( thread1, (void*) &resultado);
     printf("Thread 1 returns: %d and output is %d\n",iret1, *(int *)resultado);
     pthread_join( thread2, (void*) &resultado);
     printf("Thread 2 returns: %d and output is %d\n",iret1, *(int *)resultado);

     exit(0);
     return 0;
}

void *print_message_function(void *input)
{
     int* z = (int*) input;
     int  w = *z;
     output = w;
     printf("Thread %d is printing w ...\n", w);     
     pthread_exit(&output);
}
