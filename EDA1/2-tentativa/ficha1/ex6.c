#include <stdio.h>
#include <stdlib.h>

void rgb2hsv( int R, int G, int B, float *h, float *s, float *v )
{
    printf("Teste\n");
    float R1 = R/255;
    float G1 = G/255;
    float B1 = B/255;

    int sorted[3] = {R1, G1, B1};
    int max=0;

    //Check max of RGB
    for(int i=0; i<3 ; i++)
    {
        if(sorted[i] > max)
            max = sorted[i];
    }
    printf("Teste\n");
    int min = max;

    //Check min of RGB
    for(int i=0; i<3 ; i++)
    {
        if(sorted[i] < min)
            min = sorted[i];
    }

    

    //
    //HSV maths
    //
    //H maths
    if(max==R1 && G1>=R1)
    {
        printf("Teste1\n");
        *h = (float)(60 *((G1-B1) / (max-min)));
        printf("Teste2\n");
    }

    if(max==R1 && G1<R1)
    {
        *h = (float)((60*((G1-B1) / (max-min))) + 360);
    }

    if(max==B1)
    {
        *h = (float)((60*((B1-R1) / (max-min))) + 120);
    }
    
    if(max==G1)
    {
        *h = (float)(60*((R1-G1) / (max-min)));
    }

    

    //S maths
    *s = (float)((max-min)/max);


    //V maths
    *v = (float)max;

}

int main() 
{
    int R, G, B;
    float *h = malloc(sizeof(float));
    float *s = malloc(sizeof(float));
    float *v = malloc(sizeof(float));
    scanf("%d %d %d", &R, &G, &B);
    
    rgb2hsv(R, G, B, h, s, v);

    printf("RGB=(%d, %d, %d) <=> HSV=(%f, %f, %f)", R, G, B, *h, *s, *v);

    return 0;
}