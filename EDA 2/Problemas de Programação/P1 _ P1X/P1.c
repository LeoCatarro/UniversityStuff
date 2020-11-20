#include <stdio.h>

int PrimeFactors(int n){
	int num=2, count=0;
	
		while(n > 1){
			if(n % num == 0){
				n = n/num;
				count++;
			}
			else{
				num++;
			}
		}
		return count;
}

int main(){	
    int n, i=0; 

    scanf("%d", &n);
    int input[n]; 

    	for(i=0 ; i<n ; i++){
        	scanf("%d", &input[i]);
    	}
    	for(i=0 ; i<n ; i++){
        	printf("%d: %d\n", input[i] , PrimeFactors((int) input[i]));
    	}	
   
	return 0;
}

	

