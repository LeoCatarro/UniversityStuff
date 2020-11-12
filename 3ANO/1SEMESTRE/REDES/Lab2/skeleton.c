#include <stdio.h>
#include <string.h>
#include <math.h>

#define size_gerador 8
#define size_hamming 4

unsigned char m_geradora[size_gerador] = {0b1101, 0b1011, 0b1000, 0b0111, 0b0100, 0b0010, 0b0001};
unsigned char m_hamming[size_hamming] = {0b1010101, 0b0110011, 0b0001111};

/*
void canal( char* in, char* out )
{
int i = 1, erro=0;
while (*in != '\0')
	{
        	erro=0;      
		i=(i+5)%7;
		if(i>3){erro=0;}else{erro=pow(2,i);} //função que gera os erros
		*out = (*in)^(char)(erro);
		out++;
		in++;
		i++;
	}
	*out = '\0';
}
*/	

/*
    Byte Stuffing
*/
void bytestuff(char* input, char* output)
{
   int i=0; //input index
   int j=0; //output index

   while(input[i] != '\0')
   {
       if(input[i] == 'H')
       {
           output[j] = input[i];
            output[j+1] = '&';
            j+=2;
       }
       
       else
       {
           output[j] = input[i];
           j++;
       }
       i++;   
   }
}

/*
    Byte Destuffing
*/
void bytedestuff(char* input, char* output)
{
    int i=0;
    int j=0;

    while(input[i] != '\0')
    {
        if(input[i] != '&')
        {
            output[j] = input[i];
            j++;
        }

        i++;
    }
}

/*
    Hamming Code
*/
void char_to_binary(char c, unsigned char* first, unsigned char* second)
{
    int j=0;
    int k=0;

    for (int i = 7; i >= 0; --i)
    {
        if(i > 3)
        {
            first[j] = (c & (1 << i)) ? '1' : '0';
            j++;
        }

        else
        {
            second[k] = (c & (1 << i)) ? '1' : '0';
            k++;
        }    
    }

    //Testes de print
    printf("First:\n");
    for(int i=0 ; first[i]!='\0'; i++)
    {
        printf("%c", first[i]);
    }
    printf("\nSecond:\n");

    for(int i=0 ; second[i]!='\0'; i++)
    {
        printf("%c", second[i]);
    }
    printf("\n");
    
}


int count_ones_for_XOR(int n)
{ 
    // array to store binary number 
    int binaryNum[32]; 
    //var to store number of '1' in array
    
    // counter for binary array 
    int i = 0; 
    while (n > 0) { 
  
        // storing remainder in binary array 
        binaryNum[i] = n % 2; 
        n = n / 2; 
        i++; 
    } 

    int count=0;

    for(int i=0 ; i<4 ; i++)
    {
        if(binaryNum[i] == 1)
            count++;
    }

    for(int i=0 ; binaryNum[i] != '\0' ; i++)
    {
        printf("%d", binaryNum[i]);
    }

    if(count % 2 == 0)
        return 0;

    else
        return 1;    
}        


int binary_to_int(unsigned char* input)
{
    int result=0;

    for(int i=0 ; input[i] != '\0' ; i++)
    {
        if(input[i] == '1')
        {
            result += pow(2, (strlen(input)-i)-1);  //Conversão para decimal do numero em binario presente no array
        }
    }
    return result;
}


void hcode(unsigned char* input, unsigned char* output)
{  
    char first[5];
    char second[5];
    char and_result[8];

    for(int i=0 ; input[i]!='\0' ; i++)
    {
        char_to_binary(input[i], first, second);
        
        int left = binary_to_int(first);
        int right = binary_to_int(second);

        printf("\nLeft:%d Right:%d\n", left, right);

        for(int j=0 ; j<6 ; j++)    //Numero de linhas da matriz Geradora (NÃO PERCEBI BEM PORQUE j<6 em vez de j<7)
        {
            int a = m_geradora[j] & left;
            printf("\nAND result:%d\n", a);

            int x = count_ones_for_XOR(a);
            printf("\nXOR result:%d\n", x);
        }  
    }
}
        
// hdecode(char*, char*);


int main()
{
    char a[100], b[200], c[200];

    //strcpy ( a , "ABCDEFGHHHHHIJKLMNOPQR1234567890abcdefghijklmnopqrstuvxyz  :-))))))");

    strcpy(a, "A");

    //hcode(a,b);
    //bytestuff(b,c);
    //canal(c,d);
    //bytedestuff(d,e);
    //hdecode(e,f);

    /*printf("\n a %s",a);
    printf("\n b %s",b);
    printf("\n c %s",c);
    printf("\n d %s",d);
    printf("\n e %s",e);
    printf("\n f %s",f);*/

    
    /*unsigned char resposta[2];

   
    resposta[1] = code & code;*/

    //printf("%d\n", 0b0011 & 0b1111);
    

    //hcode(m_geradora,a);
    /*
    bytestuff(a,b);

    for(int i=0 ; b[i] != '\0'; i++)
    {
        printf("%c", b[i]);
    }

    printf("\n");

    bytedestuff(b,c);

    for(int i=0 ; c[i] != '\0'; i++)
    {
        printf("%c", c[i]);
    }*/

    hcode(a,b);



    return 0;
}


