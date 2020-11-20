#include <stdio.h>
#include <math.h>

double rais(double val){
    
    return sqrt(val);

}

double rais_sum(int n){
    
    double sum =0;
    
    for (double i =0; i<n; i++){
        
        sum += rais(i);
        
    }
    
    return sum;

}

int main(void){
    
    printf("sum = %lf", rais_sum(3));
    
    return 0;
}
