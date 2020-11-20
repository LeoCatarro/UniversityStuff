/*

    Resolução da Ficha 05

*/

#include <stdio.h>
#include <stdbool.h>


//1)
int sucessor(int n)
{
    return n+1;
}

//2)
int quadradoDoSuccessor(int n)
{
    int s = sucessor(n);

    return s*s;
}

//3)
float velocidade(float v0, float a, float t)
{
    return v0 + (a*t);
}

//4)
float posicao(float p0, float v0, float a, float t)
{
    return p0 + (v0 * t) + (0.5*a*t*t);
}

//5)
int custosEnvio(int n, int primeira_copia, int custo_unitario)
{
    return primeira_copia + ((n-1)*custo_unitario);
}

//6)


//7)
float tempoDecorrido(float d, float v)
{      
    return d/v;
}

//8)
void horaChegada()
{
    int hora=0;
    printf("Troco: 1\n");
    hora = tempoDecorrido(2,7.5);
    printf("Hora de chegada: %d", hora);

    printf("Troco: 2\n");
    hora = tempoDecorrido(6,10.9);
    printf("Hora de chegada: %d", hora);

    printf("Troco: 3\n");
    hora = tempoDecorrido(2,7.5);
    printf("Hora de chegada: %d", hora);
}

//9)
int isTriangle(float n1, float n2, float n3)
{
    if(n1+n2 > n3 || n1+n3 > n2 || n2+n3 > n1)
    {
        //e triangulo!
        return 1;
    }
    //Nao, nao e um triangulo!
    return 0;
}

//10)
int triangulo(float n1, float n2, float n3)
{
    if(isTriangle(n1,n2,n3) == 1)
    {
        //Equilatero
        if(n1==n2 && n2==n3)
        {
            return 3;
        }
         //Isosceles
        else if(n1==n2 || n1==n3 || n2==n3)
        {
            return 2;
        }
        //Escaleno
        else if(n1!=n2 && n1!=n3 && n2!=n3)
        {
            return 1;
        }
        else
        {
            //Não há triangulo
            return -1;
        }
    }
    //Não há triangulo
    return -1;    
}



/*

    TESTES

*/
int main(void)
{
    //1)    
    printf("Sucessor de %d : %d\n", 5, sucessor(5));

    //2)    
    printf("Quadrado do sucessor de %d : %d\n", 5, quadradoDoSuccessor(5));

    //3)    
    printf("Velocidade: %f\n", velocidade(5,5,20));

    //4)
    printf("Posicao: %f\n", posicao(5,5,5,5));

    //5)
    printf("Custo de Envio de %d livros: %d\n", 5, custosEnvio(5,1,4));

    //6)
    //Não fiz!

    //7)
    printf("Tempo decorrido: %f\n", tempoDecorrido(5,2));

    //8) Mais ou menos!
    horaChegada();
    

    //9)
    printf("\n");
    printf("E triangulo? : %d\n", isTriangle(2,3,5));

    //10)
    //-1 = Não é Triangulo ; 1=Escaleno ; 2=Isosceles ; 3=Retangulo
    printf("Triangulo: %d\n", triangulo(2,2,2));
    printf("Triangulo: %d\n", triangulo(1,2,2));
    printf("Triangulo: %d\n", triangulo(1,2,3));
}
