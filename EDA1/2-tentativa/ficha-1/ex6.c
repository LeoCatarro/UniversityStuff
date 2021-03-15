#include <stdio.h>
#include <stdlib.h>
#include <math.h>       //Used fmod() function: This function returns the remainder of dividing x/y.

void rgb2hsv( int R, int G, int B, float *h, float *s, float *v )
{
    double R1 = R/255.0;
    double G1 = G/255.0;
    double B1 = B/255.0;

    double sorted[3] = {R1, G1, B1};
    double max=0;

    //Check max of RGB
    for(int i=0; i<3 ; i++)
    {
        if(sorted[i] > max)
            max = sorted[i];
    }
    
    double min = max;

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
    double diff = max - min;

    if(max == min)
        *h = 0;

    if(max == R1)
        *h = fmod((60 * ((G1 - B1) / diff) + 360), 360.0);      
    
    if(max == G1)
        *h = fmod((60 * ((B1 - R1) / diff) + 120), 360.0);

    if(max == B1)
       *h = fmod((60 * ((R1 - G1) / diff) + 240), 360.0);
    
    //S maths
    *s = ((max-min)/max);

    //V maths
    *v = max;

}

int main() 
{
    int R, G, B;
    float *h = malloc(sizeof(float));
    float *s = malloc(sizeof(float));
    float *v = malloc(sizeof(float));

    printf("Insira as componenete RGB: ");
    scanf("%d %d %d", &R, &G, &B);
    
    rgb2hsv(R, G, B, h, s, v);

    printf("RGB=(%d, %d, %d) <=> HSV=(%f, %f, %f)\n", R, G, B, *h, *s, *v);

    return 0;
}