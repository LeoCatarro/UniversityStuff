#include <stdio.h>
#include <stdlib.h>

int horasMin( int min, int *horas, int *minutos ) 
{
    *horas = min/ 60.0;
    *minutos = min % 60;

    if(*horas > 24)
        return 1;
    else
        return 0;
}

int main()
{
    int min;
    int *horas = malloc(sizeof(int));
    int *minutos = malloc(sizeof(int));

    printf("Insira o total de minutos: ");
    scanf("%d", &min);

    if(horasMin(min, horas, minutos) == 1)
        printf("%d minutos correspondem a %dh:%dmin; é superior a um dia\n", min, *horas, *minutos);
    
    else
        printf("%d minutos correspondem a %dh:%dmin; não é superior a um dia\n", min, *horas, *minutos);
    
    return 0;
}