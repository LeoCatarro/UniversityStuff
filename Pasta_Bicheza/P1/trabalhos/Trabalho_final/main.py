import random 
#######################################################################################################################################################################
# main - Vai iniciar o programa, perguntando o que se deseja fazer. Encontrar as palavras numa sopa de letras ou gerar uma nova sopa de letras, dependendo da
#        escolha do usuário, vai chamar as funções necessarias a realizar aquilo que é requerido do programa.
#
# Agumentos:
#    Nenhuns.   
#
# Valores de retorno:
#    Valor de retorno da função que for chamada.
#
#######################################################################################################################################################################

def main():
    opçao = str(input('''Que opção deseja deseja realizar?\nOpção 1:
    Encontrar as palavras.\nOpção 2:\n    Gerar sopa de letras.\nOpção 3:\n    Terminar o programa.\nOpção: '''))
    if opçao == "1" or opçao.lower() == "encontrar as palavras":
        encontra(palavras_e_grelha())
    elif opçao == "2" or opçao.lower() == "gerar sopa de letras":
        ficheiro = open(str(input('Qual o nome e tipo do ficheiro a abrir: ')))
        display_grelha(ficheiro)
    elif opçao == "3" or opçao.lower() == "terminar o programa":
        exit()
    else:
        print("por favor insira um valor válido!\n")
        main()
        
#######################################################################################################################################################################
# palavras_e_grelha - esta função vai abrir o ficheiro onde se encontram as palavras e a grelha, vai adicionar as palavras e a grelha, cada uma a uma nova lista.
# 
# Argumentos:
#     Nenhuns.
#
# Valores de retorno:
#     palavras - lista com as palavras que a função vai procurar.
#     grelha - matriz em que cada lista representa uma linha da grelha, sendo cada valor dessa linha uma letra da sopa de letras.
#
#######################################################################################################################################################################
        
def palavras_e_grelha():
    file = open(str(input("Indique o nome do ficheiro: ")))
    numero_palavras = int(file.readline())
    palavras = []
    for i in range(numero_palavras):
        palavras.append(file.readline().strip().lower())
    grelha = file.readlines()
    for i in range(len(grelha)):
                grelha[i] = list(grelha[i].lower().strip())
    return [palavras, grelha]
    file.close()

#######################################################################################################################################################################
# encontra - Vai percorrer a grelha letra a letra, à procura da primeira letra da palavra que está atualmente a ser procurada, se encontrar a primeira letra,
#            vai procurar pela segunda letra dessa mesma palavra nas letras adjacentes, no  sentido do relógio, se a encontrar, tendo em conta o comprimento da palavra,
#            nessa direção vai atribuir as restantes letras a uma string para comparar com a palavra que se está à procura, se a string for igual, adiciona ao dicionario.
# Argumentos:
#     palgre - lista com uma lista de palavras e com a matriz
#
#
# Valor de retorno:
#     Nenhum.
#
#######################################################################################################################################################################


def encontra(palgre):
        words=palgre[0]
        matriz=palgre[1]
        dicpal={}
        alt=len(matriz)
        larg=len(matriz[0])
        for pal in words:
                for linha in range(len(matriz)):
                        for col in range(len(matriz[linha])):
                                f=matriz[linha][col].find(pal[0])
                                if f>=0:
                                        word=""
                                        if 0 <= linha-(len(pal)-1) < alt and matriz[linha-1][col] == pal[1]:
                                                for n in range(len(pal)):
                                                        word += matriz[linha-n][col]
                                                if word == pal:
                                                        dicpal[pal]=("%c%d-%c%d, norte"%(col+97,linha+1,col+97,linha-len(pal)))
                                                word = ""
                                        if 0 <= col+(len(pal)-1) < larg and 0 <= linha-(len(pal)-1) < alt and matriz[linha-1][col+1] == pal[1]:
                                                for n in range(len(pal)):
                                                        word+=matriz[linha-n][col+n]
                                                if word == pal:
                                                        dicpal[pal]=("%c%d-%c%d, nordeste"%(col+97,linha+1,col+97+len(pal)-1,linha+len(pal)))
                                                word = ""
                                        if 0 <= col+(len(pal)-1) < larg and matriz[linha][col+1] == pal[1]:
                                                for n in range(len(pal)):
                                                        word += matriz[linha][col+n]
                                                if word == pal:
                                                        dicpal[pal] = ("%c%d-%c%d, este"%(col+97,linha+1,col+97+len(pal)-1,linha+1))
                                                word = ""
                                        if 0 <= col+len(pal) < larg and 0 <= linha+len(pal) < alt and matriz[linha+1][col+1] == pal[1]:
                                                for n in range(len(pal)):
                                                        word += matriz[linha+n][col+n]
                                                if word == pal:
                                                        dicpal[pal ]= ("%c%d-%c%d, sudeste"%(col+97,linha+1,col+97+(len(pal)-1),linha+(len(pal))))
                                                word = ""
                                        if 0 <= linha+(len(pal)) < alt and matriz[linha+1][col] == pal[1]:
                                                for n in range(len(pal)):
                                                        word += matriz[linha+n][col]
                                                if word == pal:
                                                        dicpal[pal] = ("%c%d-%c%d, sul"%(col+97,linha+1,col+97,linha+len(pal)))
                                                word = ""
                                        if 0 <= col-(len(pal)-1) < larg and 0 <= linha+(len(pal)-1) < alt and matriz[linha+1][col-1] == pal[1]:
                                                for n in range(len(pal)):
                                                        word += matriz[linha+n][col-n]
                                                if word == pal:
                                                        dicpal[pal] = ("%c%d-%c%d, sudoeste"%(col+97,linha+1,col+97-len(pal)-1,linha+(len(pal))))
                                                word = ""                
                                        if 0 <= col-(len(pal)-1) < larg and matriz[linha][col-1] == pal[1]:
                                                for n in range(len(pal)):
                                                        word += matriz[linha][col-n]
                                                if word == pal:
                                                        dicpal[pal] = ("%c%d-%c%d, oeste"%(col+97,linha+1,col+97-(len(pal)-1),linha+1))
                                                word = ""
                                        if 0 <= col-(len(pal)-1) < larg and 0 <= linha-(len(pal)-1)+1<alt and matriz[linha-1][col-1] == pal[1]:
                                                for n in range(len(pal)):
                                                        word += matriz[linha-n][col-n]
                                                if word == pal:
                                                        dicpal[pal] = ("%c%d-%c%d, noroeste"%(col+97,linha+1,col+97-(len(pal)-1),linha+1-(len(pal)-1)))
                                                word = ""
                                                
        for chave in dicpal:
                print(chave+" - "+dicpal[chave])
        for linhas in matriz:
                print(' '.join(linhas).upper())
        main()
        

#######################################################################################################################################################################
# get_grelha - Esta é a função responsavél por obter as dimensões da futura sopa de letras, e sucessivamente criar uma grelha com as mesmas dimensões.
#
# Argumentos:
#     ficheiro - Variavél associada ao ficheiro aberto.
#
# Valor de retorno:
#     grelha - Grelha com as dimensões especificadas na primeira linha do ficheiro aberto em que cada posição é preenchida com um #.
#
#######################################################################################################################################################################

def get_grelha(ficheiro):
    ficheiro.seek(0)
    dimensoes = ficheiro.readline().split()
    grelha = []
    for x in range(int(dimensoes[0])):
        grelha.append([])
        for y in range(int(dimensoes[1])):
            grelha[x].append('#')
    return grelha

#######################################################################################################################################################################
# get_words - Esta função vai ler as palavras presentes no ficheiro, e vai adiciona-las a uma lista previamente vazia, chamada ler, de modo a ser posteriormente
#             utilizada quando for necessário misturar as palavras na grelha.
#
# Argumento:
#     ficheiro - variavél associada ao ficheiro aberto.
#
# Valor de retorno:
#     ler - lista onde se encontram as palavras a inserir na grelha.
#
#######################################################################################################################################################################

def get_words(ficheiro):
    ficheiro.seek(0)
    ficheiro.readline()
    ler = []
    for linha in ficheiro:
        ler.append(linha.replace("\n", ""))
    return(ler)

#######################################################################################################################################################################
# adicionar_palavras - É nesta parte do programa que se vai introduzir as palavras obtidas na função get_words, dentro da grelha obtida na função get_grelha.
#                      Vai se ter em conta as dimensões da grelha e das palavras, de modo a que não fique nenhuma palavra cortada, e também de modo a que nenhumas
#                      palavras se sobreponham.
#Argumento:
#     fich - variavél associada ao ficheiro aberto.
#
#Valor de retorno:
#     grelha - matriz com as palavras já colocadas e com # no resto das posições 
#
#######################################################################################################################################################################

def adicionar_palavras(ficheiro):
    grelha = get_grelha(ficheiro)
    palavras = get_words(ficheiro)
    for palavra in palavras:
        feito = 0
        while feito == 0:
            direcao = random.randint(1, 4)
            if direcao == 1:
                sentido=random.randint(1, 2)
                px = []
                py = []
                if sentido == 1:
                    starty = random.randint(len(palavra), len(grelha) -1)
                    startx = random.randint(0, len(grelha[0]) -1 )
                    funciona = 1
                    for xd in range(len(palavra)):
                        px.append((starty - xd))
                        py.append(startx)
                        if grelha[startx][(starty - xd)] == "#":
                            pass
                        else:
                            funciona = 0
                    if funciona == 1:
                        for xd in range(len(palavra)):
                            grelha[py[xd]][px[xd]] = palavra[xd].upper()
                        feito = 1
                elif sentido == 2:
                    starty = random.randint(0, len(grelha) - 1 - len(palavra))
                    startx = random.randint(0, len(grelha[0]) - 1)
                    funciona = 1
                    for xd in range(len(palavra)):
                        px.append((starty + xd))
                        py.append(startx)
                        if grelha[startx][(starty + xd)] == "#":
                            pass
                        else:
                            funciona = 0
                    if funciona == 1:
                        for xd in range(len(palavra)):
                            grelha[py[xd]][px[xd]] = palavra[xd].upper()
                        feito = 1
            elif direcao == 2:
                sentido=random.randint(1, 2)
                px = []
                py = []
                if sentido == 1:            
                    starty = random.randint(0, len(grelha)-1)
                    startx = random.randint(len(palavra), len(grelha[0])-1)
                    funciona = 1
                    for xd in range(len(palavra)):
                        px.append((starty))
                        py.append(startx-xd)
                        if grelha[(startx-xd)][starty] == "#":
                            pass
                        else:
                            funciona = 0
                    if funciona == 1:
                        for xd in range(len(palavra)):
                            grelha[py[xd]][px[xd]] = palavra[xd].upper()
                        feito = 1
                elif sentido == 2:
                    starty = random.randint(0, len(grelha)-1)
                    startx = random.randint(0, len(grelha[0])-1-len(palavra))
                    funciona = 1
                    for xd in range(len(palavra)):
                        px.append((starty))
                        py.append(startx+xd)
                        if grelha[startx+xd][starty] == "#":
                            pass
                        else:
                            funciona = 0
                    if funciona == 1:
                        for xd in range(len(palavra)):
                            grelha[py[xd]][px[xd]] = palavra[xd].upper()
                        feito = 1
            elif direcao == 3:
                sentido=random.randint(1, 2)
                px = []
                py = []
                if sentido == 1:            
                    starty = random.randint(len(palavra), len(grelha)-1)
                    startx = random.randint(len(palavra), len(grelha[0])-1)
                    funciona = 1
                    for xd in range(len(palavra)):
                        px.append((starty-xd))
                        py.append(startx-xd)
                        if grelha[(startx-xd)][(starty-xd)] == "#":
                            pass
                        else:
                            funciona = 0
                    if funciona == 1:
                        for xd in range(len(palavra)):
                            grelha[py[xd]][px[xd]] = palavra[xd].upper()
                        feito = 1
                elif sentido == 2:
                    starty = random.randint(0, len(grelha)-1-len(palavra))
                    startx = random.randint(0, len(grelha[0])-1-len(palavra))
                    funciona = 1
                    for xd in range(len(palavra)):
                        px.append((starty+xd))
                        py.append(startx+xd)
                        if grelha[startx+xd][starty+xd] == "#":
                            pass
                        else:
                            funciona = 0
                    if funciona == 1:
                        for xd in range(len(palavra)):
                            grelha[py[xd]][px[xd]] = palavra[xd].upper()
                        feito = 1
                elif direcao == 4:
                    sentido=random.randint(1, 2)
                    px = []
                    py = []
                    if sentido == 1:            
                        starty = random.randint(0, len(grelha)-1-len(palavra))
                        startx = random.randint(len(palavra), len(grelha[0])-1)
                        funciona = 1
                        for xd in range(len(palavra)):
                            px.append((starty+xd))
                            py.append(startx-xd)
                            if grelha[(startx+xd)][(starty-xd)] == "#":
                                pass
                            else:
                                funciona = 0
                        if funciona == 1:
                            for xd in range(len(palavra)):
                                grelha[py[xd]][px[xd]] = palavra[xd].upper()
                            feito = 1
                    elif sentido == 2:
                        starty = random.randint(len(palavra), len(grelha)- 1)
                        startx = random.randint(0, len(grelha[0])-1-len(palavra))
                        funciona = 1
                        for xd in range(len(palavra)):
                            px.append((starty-xd))
                            py.append(startx+xd)
                            if grelha[startx-xd][starty+xd] == "#":
                                pass
                            else:
                                funciona = 0
                        if funciona == 1:
                            for xd in range(len(palavra)):
                                grelha[py[xd]][px[xd]] = palavra[xd].upper()
                            feito = 1
    return grelha
        
#######################################################################################################################################################################
# adicionar_random - Função que recebe a grelha com as palavras ja colocadas, e coloca letras aleatoriamente nas posições que ainda se encontram com #.
#
# Argumentos:
#     fich - variavél associada ao ficheiro aberto.
#
# Valor de retorno:
#     grelha - matriz com as palavras ja colocada e o resto das posições já preenchidas com letras aleatórias.
#
#######################################################################################################################################################################

def adicionar_random(fich):
    grelha = adicionar_palavras(fich)
    for xd in range(len(grelha)):
        for xl in range(len(grelha[xd])):
            if grelha[xd][xl] != "#":
                pass
            else:
                grelha[xd][xl] = random.choice('abcdefghijklmnopqrstuvwxyz').upper()
    return grelha

#######################################################################################################################################################################
# display_grelha - Recebe a lista de palavras da função get_words e escreve o número de palavras e as próprias palavras num ficheiro aberto para escrita.
#                  Depois escreve no mesmo ficheiro a grelha completa e mostra no ecrã a sopa de letras criada.
#
# Argumentos:
#     fich - variavél associada ao ficheiro aberto.
#
# Valor de retorno:
#     Nenhum.
#
#######################################################################################################################################################################

def display_grelha(fich):
    grelha = adicionar_random(fich)
    file = open(input("Qual o nome do ficheiro a criar com a sopa de letras? "),'w')
    palavras = get_words(fich)
    file.write(str(len(palavras))+'\n')
    for pal in palavras:
        file.write(pal+'\n')
    for x in range(len(grelha)):
        ln = ""
        for y in range(len(grelha[x])):
            file.write(grelha[x][y])
            ln += grelha[x][y] + " "
        file.write('\n')
        print(ln)
    file.close()
    main()

main()
