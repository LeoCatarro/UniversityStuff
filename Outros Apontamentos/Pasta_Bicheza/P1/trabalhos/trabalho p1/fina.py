nplv=[]
palavras=[]
posicoes=[]
################################################################################
#ficheiro - Esta funcao utiliza o ficheiro fornecido pelo utilizador para criar
#duas matrizes que contêm as palavras a procurar e a grelha onde procurar
#
#Valor de retorno:
#uma lista contendo a grelha e a lista com as palavras
################################################################################
def ficheiro():
    ficheiro=str(input('Indique o nome do ficheiro para ler: '))
    abrir=open(ficheiro)
    ler=abrir.readlines()
    npal=ler[0]
    npal1=int(npal)
    numlinha=0
    ultimapal=ler[-1]
    for n in ler:
        numlinha=numlinha+1
        numlinhaf=numlinha
    if (len(ultimapal))>25:
        print('A sopa de letras não pode ter mais do que 25 linhas, por favor introduza um ficheiro válido!')
        procurar(ficheiro())
    else:

        for i in range(npal1+1,numlinhaf):
            p=ler[i]
            y=' '.join(p)
            x=y.split()
            nplv.append(x)
            posicoes.append([])

        for o in range(1,npal1+1): 
            pal=ler[o]
            mais=pal.upper()
            joi=' '.join(mais)
            spli=joi.split()
            palavras.append(spli)
    return [palavras,nplv]

#####################################################################################
#atribuir - Esta funcao utiliza as listas da grelha para criar uma lista com posicoes
#
#Argumentos:
#ficheiro - funcao anterior para obter as listas la formadas
#Valor de retorno:
#None
#Nota: adiciona posicoes a uma matriz identica a matriz da grelha
#####################################################################################         
def atribuir(ficheiro):
    numcoli=len(nplv)
    cout=1
    for t in posicoes:
        count=0
        for pose in range(numcoli):
            letra=chr(97+count)
            t.append(letra + str(cout))
            count=count+1
        cout=cout+1         
    
#####################################################################################
#procurar - Esta funcao procura as palavras fornecidas no ficheiro na grelha
#
#Argumentos:
#ficheiro - funcao anterior para obter as listas la formadas
#Valor de retorno:
#None
#####################################################################################    
def procurar(ficheiro):
    nplv=ficheiro[1]
    palavras=ficheiro[0]
    for linha in range(len(nplv)):
        print(nplv[linha])
    for linha_pal in range(0,len(palavras)):
        primeira_letra=palavras[linha_pal][0]
        for linha_nplv in range(0,len(nplv)):
            for numlet_nplv in range(0,len(nplv[linha_nplv])):
                if primeira_letra==nplv[linha_nplv][numlet_nplv]:
                    este(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    oeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    sul(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    norte(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    sudoeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    noroeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    sudeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    nordeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv)
                    
#####################################################################################
#este,oeste,sul,norte,sudoeste,noroeste,sudeste,nordeste - Estas funcoes sao a 
#continuacao da funcao procurar e serve para procurar as palavras na matriz palavras 
#na grelha em todos os sentidos
#
#Argumentos:
#posicoes - matriz contendo as posicoes definidas na funcao atribuir
#palavras - matriz contendo as palavras a procurar
#linha_pal - posicao das listas na matriz palavras
#linha_nplv - posicao das listas na matriz nplv, que e a matriz que contem a grelha
#numlet_nplv - percorre as posicoes das colunas da matriz nplv
#Valor de retorno:
#palavras encontradas na matriz nplv, juntamente com a posicao onde começa e acaba e
#o sentido em que esta escrita
#Nota: adiciona posicoes a uma matriz identica a matriz da grelha
#####################################################################################
def este(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (numlet_nplv+counter)<=len(nplv[linha_nplv])-1:
            if letra_pal==nplv[linha_nplv][numlet_nplv+counter]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv][numlet_nplv+count-1]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', este')                         
    
def oeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (numlet_nplv-counter)>=0:
            if letra_pal==nplv[linha_nplv][numlet_nplv-counter]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv][numlet_nplv-count+1]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', oeste') 

def sul(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (linha_nplv+counter)<=len(nplv)-1:
            if letra_pal==nplv[linha_nplv+counter][numlet_nplv]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv+counter-1][numlet_nplv]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', sul')

def norte(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (linha_nplv-counter)>=0:
            if letra_pal==nplv[linha_nplv-counter][numlet_nplv]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv-counter+1][numlet_nplv]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', norte')

def noroeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (linha_nplv-counter)>=0 and (numlet_nplv-counter)>=0:
            if letra_pal==nplv[linha_nplv-counter][numlet_nplv-counter]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv-counter+1][numlet_nplv-count+1]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', noroeste')
                    
def sudoeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (linha_nplv+counter)<=len(nplv)-1 and (numlet_nplv-counter)>=0:
            if letra_pal==nplv[linha_nplv+counter][numlet_nplv-counter]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv+counter-1][numlet_nplv-count+1]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', sudoeste')

def sudeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (linha_nplv+counter)<=len(nplv)-1 and (numlet_nplv+counter)<=len(nplv[linha_nplv])-1:
            if letra_pal==nplv[linha_nplv+counter][numlet_nplv+counter]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv+counter-1][numlet_nplv+count-1]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', sudeste')

def nordeste(posicoes,palavras,linha_pal,linha_nplv,numlet_nplv):
    counter=1
    count=1
    for letras_pal in range(1,len(palavras[linha_pal])):
        letra_pal=palavras[linha_pal][letras_pal]
        if (numlet_nplv+counter)<=len(nplv[linha_nplv])-1 and (linha_nplv-counter)>=0:
            if letra_pal==nplv[linha_nplv-counter][numlet_nplv+counter]:
                count=count+1
                counter=counter+1
                if count==len(palavras[linha_pal]):
                    atribuir(ficheiro)
                    posicao0=posicoes[linha_nplv][numlet_nplv]
                    posicao1=posicoes[linha_nplv-counter+1][numlet_nplv+count-1]
                    print((''.join(palavras[linha_pal])).lower(),':',posicao0,'-',posicao1,', nordeste')
procurar(ficheiro())
                
    
                    
                             
                                
                                
                                
                    
        
    
                        

                        


