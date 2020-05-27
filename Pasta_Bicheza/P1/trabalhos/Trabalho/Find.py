######################################################################################
# Função Extract:                                                                    
#   Função que extrai os valores de um fichero e coloca-os em listas para que possam 
#   ser manipulados mais facilmente pelo programa                                   
# Argumentos:
#   fname: FileName->nome do ficheiro de onde extrair os valores
#           Tipo: String
# Retorno:
#   Tuplo formado por um Dicionario e uma lista:
#       Dicionaro->Chaves:palavras a procurar ; Valores:Lista com a posição incial e
#        final da palavra (inicialmente é uma lista vazia)
#       Lista->lista com as linhas do ficheiro correspondentes a sopa de letras
######################################################################################
def Extract (fname):
    dic=dict()
    lst=list()
    try:
        f=open(fname)
        f.seek(0)
    except IOError:
        print('Ficheiro',fname,'Invalido')
        return dict(),list()
    for l in f:
        if l!='\n' and l!='':
            if l[:len(l)-1].isdigit():
                l=l[:len(l)-1]
                n=int(l)
            elif n>0:
                l=l[:len(l)-1]
                dic[l]=[[-1,-1],[-1,-1]]
                n-=1
            else:
                if l[len(l)-1].isalpha():
                    l=l[:len(l)]
                else:
                    l=l[:len(l)-1]
                lst.append(l.lower())
                    
    f.close()
    return dic,lst

        

######################################################################################
# Função Reverse:                                                                    
#   Função que inverte a ordem dos caracteres de uma string                                 
# Argumentos:
#   st: String->string a inverter
#           Tipo: String
# Retorno:
#   String inverssa a string st
######################################################################################
def Reverse (st):
    ls=list()
    
    for c in st:
        ls.append(c)
        
    ls.reverse()
    
    return ''.join(ls)
    
######################################################################################
# Função Find:                                                                    
#   Função que procura uma palavra numa string                                   
# Argumentos:
#   wrd: Word->palavra a procurar
#          Tipo: String
#        st: String->string na qual se procura a palavra
#          Tipo: String
# Retorno:
#   Um valor inteiro que corresponde a posicao inicial da palavra na string se a
#   palavra foi encontrada e -1 se a palavra não foi encontrada
######################################################################################
def Find (wrd,st):
    for c in range(len(st)):
        if len(st)-c >= len(wrd):
            if wrd == st[c:c+len(wrd)]:
                return c
            
    return -1

######################################################################################
# Função Transpos:                                                                    
#   Função que trasforma uma matriz na sua transposta                                   
# Argumentos:
#   lst: Lista->matriz de strings a transpor
#         Tipo: Lista
# Retorno:
#   Retorna uma lista de strings onde cada coluna da matriz inicial corresponde a
#   uma linha da nova matriz
######################################################################################
def Transpos (lst):
    rl=len(lst)*['']
    
    for c in range(len(lst)):
        for l in lst:
            rl[c]+=l[c]
            
    return rl
        
        


######################################################################################
# Função DiagSE:                                                                    
#   Função que retorna uma matriz onde as linhas correspomdem as linhas diagonais de
#   outra matriz(do canto superior esquerdo para o canto inferior direito)
# Argumentos:
#   lst: Lista->matriz de strings a converter
#         Tipo: Lista
# Retorno:
#   Retorna uma lista de strings onde cada linha corresponde a sequencia da diagonal
#   da lista incerida
######################################################################################
def DiagSE(lst):
    rlst=list()
    
    for c in range(len(lst)):
        st=''
        x=0
        y=c
        
        while True:
            l=lst[y]
            st+=l[x]
            x+=1
            y+=1
            if x>=len(lst[y-1]) or y>=len(lst):
                break
            
        rlst.append(st)
        
    rlst.reverse()
    
    for c in range(1,len(lst)):
        st=''
        x=c
        y=0
        
        while True:
            l=lst[y]
            st+=l[x]
            x+=1
            y+=1
            if x>=len(lst[y-1]) or y>=len(lst):
                break
            
        rlst.append(st)
        
    return rlst
               

######################################################################################
# Função DiagSW:                                                                    
#   Função que retorna uma matriz onde as linhas correspomdem as linhas diagonais de
#   outra matriz(do canto superior direito para o canto inferior esquerdo)
# Argumentos:
#   lst: Lista->matriz de strings a converter
#         Tipo: Lista
# Retorno:
#   Retorna uma lista de strings onde cada linha corresponde a sequencia da diagonal
#   da lista incerida
######################################################################################
def DiagSW(lst):
    rlst=list()
    
    for c in range(len(lst)):
        st=''
        x=0
        y=c
        
        while True:
            l=lst[y]
            st+=l[x]
            x+=1
            y-=1
            if x>=len(lst[y-1]) or y<0:
                break
            
        rlst.append(st)
    
    for c in range(1,len(lst)):
        st=str()
        x=c
        y=len(lst)-1
        
        while True:
            l=lst[y]
            st+=l[x]
            x+=1
            y-=1
            if x>=len(lst[y-1]) or y<0:
                break
            
        rlst.append(st)
        
    return rlst

######################################################################################
# Função DataForm:                                                                    
#   Função que formata um conjunto de cordenadas para que sejam mais facilmente lidas
#   pelo utilisador
# Argumentos:
#   data: Data->Lista de coordenadas
#          Tipo: lista
# Retorno:
#   Lista de coordenadas formatadas e um ponto cardeal. Exemplo: ['B1',C3','sudeste']
######################################################################################
def DataForm(data):
    lst=list()
    
    for c in data:
        lst.append(chr(c[0]+64)+str(c[1]))
        
    c1=data[0]
    c2=data[1]
    x=c1[0]-c2[0]
    y=c1[1]-c2[1]
    
    if x==0 and y>0:
        lst.append('norte')
    elif x==0 and y<0:
        lst.append('sul')
    elif x<0 and y==0:
        lst.append('este')
    elif x>0 and y==0:
        lst.append('oeste')
    elif x<0 and y>0:
        lst.append('nordeste')
    elif x<0 and y<0:
        lst.append('sudeste')
    elif x>0 and y>0:
        lst.append('nordoeste')
    else:
        lst.append('sudoeste')
        
    return lst
        

    
#FindMain
def mainFind():
    while True:
        filename=input('Nome do ficheiro:')+'.txt'
        chk=Extract(filename)
        if chk!=(dict(),list()):
            break
    palavras,matriz=chk
    matrizDiagSw=DiagSW(matriz)
    matrizDiagSe=DiagSE(matriz)
    matrizTransposta=Transpos(matriz)

    for p in palavras:
        reverseP=Reverse(p)
        cords=palavras[p]
        le=len(matriz)
        for c in range(1,le*2):
            if c<le+1:
                
                n=Find(p,matriz[c-1])+1
                if n!=0:
                    cords=[[n,c],[n+len(p)-1,c]]
                    break
                
                n=Find(reverseP,matriz[c-1])+1
                if n!=0:
                    cords=[[n+len(p)-1,c],[n,c]]
                    break

                n=Find(p,matrizTransposta[c-1])+1
                if n!=0:
                    cords=[[c,n],[c,n+len(p)-1]]
                    break

                n=Find(reverseP,matrizTransposta[c-1])+1
                if n!=0:
                    cords=[[c,n+len(p)-1],[c,n]]
                    break
                       
            n=Find(p,matrizDiagSe[c-1])+1
            if n!=0:
                if c<=le:
                    x=n
                    y=le-c+n
                else:
                    x=c-le+n
                    y=n
                cords=[[x,y],[x+len(p)-1,y+len(p)-1]]
                break

            n=Find(reverseP,matrizDiagSe[c-1])+1
            if n!=0:
                if c<=le:
                    x=n
                    y=le-c+n
                else:
                    x=c-le+n
                    y=n
                cords=[[x+len(p)-1,y+len(p)-1],[x,y]]
                break

            n=Find(p,matrizDiagSw[c-1])+1
            if n!=0:
                if c<=le:
                    x=n
                    y=c-n+1
                else:
                    x=c-le+n
                    y=le-n+1
                cords=[[x,y],[x+len(p)-1,y-len(p)+1]]
                break

            n=Find(reverseP,matrizDiagSw[c-1])+1
            if n!=0:
                if c<=le:
                    x=n
                    y=c-n+1
                else:
                    x=c-le+n
                    y=le-n+1
                cords=[[x+len(p)-1,y-len(p)+1],[x,y]]
                break
    
        palavras[p]=cords

    print('------------------')
    for p in palavras:
        lst=DataForm(palavras[p])
        print(p,':',lst[0],'-',lst[1],':',lst[2])

mainFind()            


























