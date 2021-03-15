#include <stdio.h>

int main()
{
    int i, j, *p1, *p2, **pp1, **pp2;
    i=4;
    j=5;
    p1 = &i;
    p2 = &j;
    pp1 = &p2;
    pp2 = &p1;

    printf("i: %d\n", i);
    printf("*p2: %ld\n", *p2);
    printf("&i: %p\n", &i);
    printf("*pp1: %ld\n", *pp1);
    printf("*pp2: %ld\n", *pp2);
    printf("&(*p1): %p\n", &(*p1));
    printf("j: %d\n", j);
    printf("*p1: %ld\n", *p1);
    printf("*(&p1): %ld\n", *(&p1));
    return 0;    
}

/*
variavel: i     j     p1    p2    pp1   pp2
conteudo: 4     5     1000  1007  1053  1030
endereco: 1000  1007  1030  1053  1071  1079


expressao: i    *p2    &i    &p2    *pp1    *pp2    &(*p1)    j    *p1    *(&p1)
conteudo   4     5     end.  end.    end.   end.     end.      5     4    end.
*/