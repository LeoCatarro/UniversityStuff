import random

def Import(file):
    try:
        f=open(file)
    except IOError:
        print('Ficheiro',file,'não encontrado')
        return list(),list()
    
    lst=list()
    f.seek(0)
    size=f.readline()
    size=size[:len(size)-1]
    s= list(size)
    s.pop(len(s)//2)
    for l in f:
        if l !='' or l!='\n':
            if l[len(l)-1].isalpha():
                l=l[:len(l)]
            else:
                l=l[:len(l)-1]    
            lst.append(l.lower())
    f.close()
    return s,lst

def Export(wl,nxn,file):
    f=open(file,'w+')
    f.write(str(len(wl))+'\n')
    for w in wl:
        f.write(w+'\n')
    for y in nxn:
        f.write(''.join(y)+'\n')
    f.close()

def Reverse (st):
    ls=list()
    for c in st:
        ls.append(c)      
    ls.reverse()  
    return ''.join(ls)


def gerar(n,m):
    lsty=list()
    for y in range(m):
        lstx=list()
        for x in range(n):
            lstx.append(' ')
        lsty.append(lstx)
    return lsty

def Seth(x,y,w,l):
    lst=list(l[y])
    rl=list(l)
    for xx in range(len(w)):
        if lst[x+xx]==' ' or lst[x+xx]==w[xx]:
            lst[x+xx]=w[xx]
        else:
            return rl
    l[y]=lst
    return l
            

def Setv(x,y,w,l):
    rl=list(l)
    for yy in range(len(w)):
        lst=list(l[y+yy])
        if lst[x]==' ' or lst[x]==w[yy]:
            lst[x]=w[yy]
            l[y+yy]=lst
        else:
            return rl
    return l


def Setse(x,y,w,l):
    rl=list(l)
    for p in range(len(w)):
        lst=list(l[y+p])
        if lst[x+p]==' ' or lst[x+p]==w[p]:
            lst[x+p]=w[p]
            l[y+p]=lst
        else:
            return rl
    return l

def Setsw(x,y,w,l):
    rl=list(l)
    for p in range(len(w)):
        lst=list(l[y+p])
        if lst[x-p]==' ' or lst[x-p]==w[p]:
            lst[x-p]=w[p]
            l[y+p]=lst
        else:
            return rl
    return l
            
def Fill(nxn):
    for y in range(len(nxn)):
        lst=nxn[y]
        for x in range(len(lst)) :
            if lst[x]==' ':
                lst[x]=chr(random.randint(ord('A'),ord('Z')))
    return nxn
            
def Position(size,lst):
    n=int(size[0])
    m=int(size[1])
    
    nxn=gerar(n,m)
    
    for palavra in lst:
        while True:
            x=random.randint(0,n-1)
            y=random.randint(0,m-1)
            d=random.randint(1,8)
            if (d==1 or d==2) and x+len(palavra)-1<n:
                ver=list(nxn)
                if d==2:
                    palavra=Reverse(palavra)
                nxn=Seth(x,y,palavra.upper(),nxn)
                if ver!=nxn:
                    break

            if (d==3 or d==4) and y+len(palavra)-1<m:
                ver=list(nxn)
                if d==4:
                    palavra=Reverse(palavra)
                nxn=Setv(x,y,palavra.upper(),nxn)
                if ver!=nxn:
                    break

            if (d==5 or d==6) and y+len(palavra)-1<m and x+len(palavra)-1<n:
                ver=list(nxn)
                if d==6:
                    palavra=Reverse(palavra)
                nxn=Setse(x,y,palavra.upper(),nxn)
                if ver!=nxn:
                    break
                
            if (d==7 or d==8) and  y+len(palavra)-1<m and x-len(palavra)+1>0:
                ver=list(nxn)
                if d==8:
                    palavra=Reverse(palavra)
                nxn=Setsw(x,y,palavra.upper(),nxn)
                if ver!=nxn:
                    break
    return nxn


def mainGerar():
    while True:
        fileI=str(input('Nome do ficheiro a importar:'))+'.txt'
        chk=Import(fileI)
        if chk!=(list(),list()):
            size,lst=chk
            break
    fileO=str(input('Nome do ficheiro para exportar:'))+'.txt'
    while True:
        nxn=Position(size,lst)
        for l in nxn:
            print(l)
        resposta=input('Gerar novamente?(S \ N)').lower()
        while resposta!='s' and resposta!='n':
            resposta=input('Invalida:').lower()
        if resposta=='n':
            break
    Fill(nxn)
    Export(lst,nxn,fileO)
    print('Feito')
mainGerar()
