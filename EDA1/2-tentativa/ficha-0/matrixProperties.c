#include "stdio.h"

void isDiagonal(int n, int matrix[n][n])
{
    printf("TODO()\n");
}

/*
    Função --> int isSymmetric(int n, int matrix[n][n])
    
    Verifica se uma matrix N x N é Simétrica, ou seja, em termos de lógica se a triangular superior e inferior são iguais
    Passos:
        - É copiada matrix para auxMatrix trocando as triangulares
        - Caso as 2 matrizes sejam iguais, então a matrix passada como argumento é simétrica
*/
int isSymmetric(int n, int matrix[n][n])
{
    int auxMatrix[n][n];

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
            if(matrix[i][j] != auxMatrix[i][j])
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
    scanf("%d", &n);
    
    int matrix[n][n];

    printf("Data insert into matrix(line by line):\n");

    //Fill matrix with data
    for(int i=0 ; i<n ; i++)
    {
        for(int j=0 ; j<n ; j++)
        {
            scanf("%d", &matrix[i][j]);
        }
    }

    printf("Result: %d\n", isSymmetric(n, matrix));
    
    return 0;
}