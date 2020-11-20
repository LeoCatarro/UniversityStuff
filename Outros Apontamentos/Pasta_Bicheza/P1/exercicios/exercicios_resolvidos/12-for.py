np=int(input("Quantos são os produtos"))
pro=[]
qua=[]
pre=[]
n=0

for n in range(np):
    p = input ("Qual é o produto?")
    pro.append(p)
    
for n in range(np):
    q = int(input("Quantos deste produto?"))
    qua.append(q)
    
for n in range(np):
    l=float(input ("Qual é o preço?"))
    pre.append(l)

for n in range(np):
    print( pro[n],"por unidade", pre[n],"eur")
    m=qua[n]*pre[n]
    
print("TOTAL:",round(m,2),"eur")
