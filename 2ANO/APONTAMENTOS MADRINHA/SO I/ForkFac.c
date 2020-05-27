#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

void factorial(int n){
	
	int temp=1;
	pid_t pid=0;
	
	while(pid==0){
		temp*=n;
		n--;
		if(n==1){
			printf("resultado e %d \n",temp);
			break;
		}else{
			pid = fork();
		}
	}

}

int main(void){
	factorial(4);
}
