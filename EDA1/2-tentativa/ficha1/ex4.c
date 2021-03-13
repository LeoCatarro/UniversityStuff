#include <stdio.h>

int *vmaior(int *v1, int *v2)
{
    if(*v1 > *v2)
        return v1;
    else
        return v2;
}

int main() 
{
    int n1, n2;
    scanf("%d %d", &n1, &n2);
    
    printf("Maior: endereco=%p , valor=%d\n", &(*vmaior(&n1, &n2)), *vmaior(&n1, &n2));

    return 0;
}