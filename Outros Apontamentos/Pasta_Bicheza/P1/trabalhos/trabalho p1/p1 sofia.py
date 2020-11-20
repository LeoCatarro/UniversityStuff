def menu():
    print('1 - Encontrar Palavras \n 2 - Grelha aleatoria \n 3 - Sair')
    a = int(input('Insira o numero da opção que deseja '))
    if a == 1:
        mostra()
        
def mostra () :
    r = 0
    #y = input('qual o nome do ficheiro? ')
    file = open('p.txt','r')
    m = []
    l = []
    l1 = []
    c = ' '
    for i in range(10):
        y = file.readline()
        d = y.upper()
        m.append(d.split())
        
    for i in range (10,19):
        x = file.readline()
        for j in x:
            c = c + ' ' + j
            f = (c.split())
        c = ' '
        l.append(f)

    del m[0]
    for k in m:
        for h in k:
            l1.append(h)
            
    acha(l,l1)
   
def acha (matriz,palavra):
    for p in palavra:
        v = 0
        j = len(p)-1
        for z in range(9):
            if z == 0:
                h = matriz[0]
                for i in range(len(h)):
                    if i!=0 and i!=8:
                        if h[i] == p[0]:
                                esquerda(i,v,j,h,p,z,matriz,palavra)
                                cima(i,v,j,h,p,z,matriz,palavra)
                                baixo(i,v,j,h,p,z,matriz,palavra)
                                sudeste(i,v,j,h,p,z,matriz,palavra)
                                sudoeste(i,v,j,h,p,z,matriz,palavra)
                    if i==0:
                        if h[i] == p[0]:
                                direita(i,v,j,h,p,z,matriz,palavra)
                                baixo(i,v,j,h,p,z,matriz,palavra)
                                sudeste(i,v,j,h,p,z,matriz,palavra)
                                         
                    if i==8:
                        if h[i] == p[0]:
                                esquerda(i,v,j,h,p,z,matriz,palavra)
                                baixo(i,v,j,h,p,z,matriz,palavra)
                                sudoeste(i,v,j,h,p,z,matriz,palavra)
                                
            if z == 8:
                h = matriz[8]
                for i in range(len(h)):
                    if i!=0 and 1!=8:
                        if h[i] == p[0]:
                            esquerda(i,v,j,h,p,z,matriz,palavra)
                            cima(i,v,j,h,p,z,matriz,palavra)
                            direita(i,v,j,h,p,z,matriz,palavra)
                            nordeste(i,v,j,h,p,z,matriz,palavra)
                            noroeste(i,v,j,h,p,z,matriz,palavra)
                    if i==0:
                        if h[i] == p[0]:
                            cima(i,v,j,h,p,z,matriz,palavra)
                            direita(i,v,j,h,p,z,matriz,palavra)
                            nordeste(i,v,j,h,p,z,matriz,palavra)
                            
                            
                    if i == 8:
                        if h[i] == p[0]:
                            esquerda(i,v,j,h,p,z,matriz,palavra)
                            cima(i,v,j,h,p,z,matriz,palavra)
                            noroeste(i,v,j,h,p,z,matriz,palavra)
                           
            if z!=0 and z!= 8:
                h = matriz[z]
                for i in range(len(h)):
                    if i!=0 and i!=8:
                        if h[i] == p[0]:
                            esquerda(i,v,j,h,p,z,matriz,palavra)
                            cima(i,v,j,h,p,z,matriz,palavra)
                            baixo(i,v,j,h,p,z,matriz,palavra)
                            direita(i,v,j,h,p,z,matriz,palavra)
                            noroeste(i,v,j,h,p,z,matriz,palavra)
                            sudoeste(i,v,j,h,p,z,matriz,palavra)
                            nordeste(i,v,j,h,p,z,matriz,palavra)
                            sudeste(i,v,j,h,p,z,matriz,palavra)
                    if i==0:
                        if h[i] == p[0]:
                            cima(i,v,j,h,p,z,matriz,palavra)
                            direita(i,v,j,h,p,z,matriz,palavra)
                            baixo(i,v,j,h,p,z,matriz,palavra)
                            nordeste(i,v,j,h,p,z,matriz,palavra)
                            sudeste(i,v,j,h,p,z,matriz,palavra)
                                
                    if i==8:
                        if h[i] == p[0]:
                            esquerda(i,v,j,h,p,z,matriz,palavra)
                            cima(i,v,j,h,p,z,matriz,palavra)
                            baixo(i,v,j,h,p,z,matriz,palavra)
                            noroeste(i,v,j,h,p,z,matriz,palavra)
                            sudoeste(i,v,j,h,p,z,matriz,palavra)
        
def esquerda(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    i = i - 1
    v = v + 1
    l = len(p)-v
    if (0<=(i-l)< 9) or (0<=(l-i)< 9):
        if h[i] == p[1]:
            o = i + 2
            s = o - j
            while v < j:
                c = c + h[i]
                i = i - 1
                v = v + 1
                if v == j:
                    c = c + p[j]
                    if c == p:
                        z = z + 1
                        resultado(p,s,o,z,z,'oeste')
                        palavra.remove(p)
                        acha(matriz,palavra)             
def direita(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    i = i + 1
    v = v + 1
    l = len(p)-v
    if (0<=(i+l)< 9):
        if h[i] == p[1]:
            o = i
            s = o + j
            while v < j:
                c = c + h[i]
                i = i + 1
                v = v + 1
                if v == j:
                    c = c + p[j]
                    if c == p:
                        z = z + 1
                        resultado(p,s,o,z,z,'este')
                        palavra.remove(p)
                        acha(matriz,palavra)         
def cima(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    v = v + 1
    z = z - 1
    h = matriz[z]
    l = len(p)-v
    if (0<=(z-l)< 9) or  (0<=(l-z)< 9):
        if h[i] == p[1]:
            c = c + h[i]
            o = z + 2
            s = o - j
            while v < j:
                z = z - 1
                h = matriz[z]
                c = c + h[i]
                v = v + 1
                if v == j:
                    if c == p:
                        i = i + 1
                        resultado(p,i,i,o,s,'norte')
                        palavra.remove(p)
                        acha(matriz,palavra)
def baixo(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    v = v + 1
    z = z + 1
    h = matriz[z]
    l = len(p)-v
    if (0<=(z+l)< 9):
        if h[i] == p[1]:
            c = c + h[i]
            o  = z 
            s = o + j
            r = i + 1
            while v < j:
                z = z + 1
                h = matriz[z]
                v = v + 1
                c = c + h[i]
                if v == j:
                    if c == p:
                        i = i + 1
                        resultado(p,r,r,o,s,'sul')
                        palavra.remove(p)
                        acha(matriz,palavra)
def noroeste(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    v = v + 1
    z = z - 1
    i = i - 1
    h = matriz[z]
    l = len(p)-v
    if ((0<=(l-z)< 9) or (0<=(z-l)< 9)) and ((0<=(i-l)<9) or (0<=(l-i)<9)):
        if h[i] == p[1]:
            c = c + h[i]
            o = z + 2
            s = o - j
            r = i + 2
            t = o - v
            while v < j:
                z = z - 1
                h = matriz[z]
                i = i - 1
                c = c + h[i]
                v = v + 1
                if v == j:
                    if c == p:
                        resultado(p,t,r,o,s,'noroeste')
                        palavra.remove(p)
                        acha(matriz,palavra)            
def nordeste(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    v = v + 1
    z = z - 1
    i = i + 1
    h = matriz[z]
    l = len(p)-v
    if ((0<=(z-l)< 9) or (0<=(l-z)< 9)) and (0<=(i+l)<9):
        if h[i] == p[1]:
            c = c + h[i]
            o = z + 2
            s = o - j
            while v < j:
                z = z - 1
                h = matriz[z]
                i = i + 1
                c = c + h[i]
                v = v + 1
                if v == j:
                    if c == p:
                        resultado(p,1,1,o,s,'nordeste')
                        palavra.remove(p)
                        acha(matriz,palavra)
    
def sudoeste(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    v = v + 1
    i = i - 1
    z = z + 1
    h = matriz[z]
    l = len(p)-v
    if (0<=(l+z)< 9) and ((0<=(l-i)<9) or (0<=(l-i)<9)):
        if h[i] == p[1]:
            o  = z 
            s = o + j
            r = i + 2
            t = o - j
            c = c + h[i]
            while v < j:
                z = z + 1
                h = matriz[z]
                i = i - 1
                c = c + h[i]
                v = v + 1
                if v == j:
                    if c == p:
                        resultado(p,t,r,o,s,'sudoeste')
                        palavra.remove(p)
                        acha(matriz,palavra)
                        
def sudeste(i,v,j,h,p,z,matriz,palavra):
    c = h[i]
    v = v + 1
    i = i + 1
    z = z + 1
    h = matriz[z]
    l = len(p)-v
    if (0<=(z+l)< 9) and (0<=(i+l)<9):
        if h[i] == p[1]:
            c = c + h[i]
            o  = z 
            s = o + j
            r = i
            t = o + j
            while v < j :
                z = z + 1
                h = matriz[z]
                i = i + 1
                c = c + h[i]
                v = v + 1
                if v == j:
                    if c == p:
                        resultado(p,t,r,o,s,'sudeste')
                        palavra.remove(p)
                        acha(matriz,palavra)
                         
def resultado (p,c,r,a,s,t):
    d ={1:'a',2:'b',3:'c',4:'d',5:'e',6:'f',7:'g',8:'h',9:'f'}
    re = (p,d[r],a,d[c],s,t)
    print(re)

menu() 

