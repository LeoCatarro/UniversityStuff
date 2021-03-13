#include <stdio.h>

void ordena(int *a, int *b, int *c)
{
    printf("A: %d | B: %d | C: %d", *a, *b, *c);
}

int main() 
{
    int n1, n2, n3;
    scanf("%d %d %d", &n1, &n2, &n3);
    ordena(&n1, &n2, &n3);
    return 0;
}