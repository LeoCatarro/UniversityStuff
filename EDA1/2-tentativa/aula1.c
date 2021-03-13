#include "stdio.h"

int isSymmetric(int n, int[n][n] matrix)
{
    auxMatrix[n][n];

    //Copy matrix to auxMatrix
    for(int i=0 ; i<n ; i++)
    {
        for(int j=0 ; j<n ; j++)
        {
            auxMatrix[j][i] = matrix[i][j];
        }
    }

    //Check if is symmetric
    for(int i=0 ; i<n ; i++)
    {
        for(int j=0 ; j<n ; j++)
        {
            if(matriz[i][j] != auxMatrix[i][j])
            {
                //Not symmetric
                return 0;
            }
        }
    }
    //Symmetric
    return 1;
}

int main()
{   
    int n;
    
    printf("Size of Matrix:\n");
    scanf("%d", n);
    
    int [n][n] matrix;

    print("Data insert into matrix:\n");

    //Fill matrix with data
    for(int i=0 ; i<n ; i++)
    {
        for(int j=0 ; j<n ; j++)
        {
            scanf("%d", matrix[i][j]);
        }
    }

    if( n<=10 )
        printf("%d\n", isSymmetric(n, matrix));
    
    else
        printf("Size should be less then 10");

    return 0;
}