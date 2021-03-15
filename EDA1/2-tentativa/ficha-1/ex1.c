#include <stdio.h>

int main()
{
    int var1=5;
    char var2='a';
    int *ptr1= &var1;
    char *ptr2 = &var2;
    *ptr2='b';
    
    //a)
    printf("var1 tem o endereco %p e o valor %d\n", ptr1, var1 );
    printf("var2 tem o endereco %p e o valor %c\n\n", ptr2, var2 );
    
    //b)
    printf("char size: %ld\n", sizeof(char));
    printf("char* size: %ld\n", sizeof(char*));
    printf("int size: %ld\n", sizeof(int));
    printf("int* size: %ld\n\n", sizeof(int*));

    //c)
    printf("valor de ptr1 %d\n", *ptr1);
    printf("valor de ptr1+1 %d\n", *ptr1+1);
    printf("valor de ptr2  %d\n", *ptr2);
    printf("valor de ptr2+1 %d\n", *ptr2+1);

    printf("valor de ptr1 %ld\n", ptr1);
    printf("valor de ptr1+1 %ld\n", ptr1+1);
    printf("valor de ptr2  %ld\n", ptr2);
    printf("valor de ptr2+1 %ld\n\n", ptr2+1);

    return 0;
}