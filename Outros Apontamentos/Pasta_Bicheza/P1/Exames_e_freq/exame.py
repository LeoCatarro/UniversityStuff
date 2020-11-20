#### 1
va={(1,1):[4,5,4], (1,2):[], (2,3):[4,5,4], (2,2):[3,3]}
vb={2:(1,1), 3:(1,2), 5:(2,3), 4:(2,2)}
print((1,1) in vb)
print(len(va[(1,2)]))
print(va[vb[4]])
print(vb[3][1])
z=list(va.values())
print([] in z)
print()

#### 2
def primeira(string):
	d = {}
	for i in range(len(string)):
		if string[i] in d:
			return d[string[i]]
		else:
			d[string[i]]=i
	return -1
print('primeira')
print(primeira('asdrrfsgd'))
print(primeira('12345'))


#### 3
def soma_alg(n):
	if n<10:
		return n
	else:
		return n%10+soma_alg(n//10)
print('\nsoma_alg')
print(soma_alg(1234))


#### 4
def processa(vetor):
    size = len(vetor)
    for i in range(1,size):
        el = vetor[i]
        ia = i-1
        while ia>=0 and vetor[ia]>el:
            vetor[ia+1] = vetor[ia]
            ia = ia-1
            vetor[ia+1] = el
    return vetor
print('\nprocessa')
print(processa([6,3,7,1]))


#### 5
def medias(notas):
	for i in range(len(notas)):
		aluno = notas[i]
		print(aluno[0],' - ',round((aluno[1]+aluno[2]+aluno[3])/3,1))
print('\nmedias')
medias([(1,10,10,10),(2,12,10,11),(3,9,7,6),(4,15,12,10)])


def estatisticas(notas):
	lista=[]
	media = []
	for j in range(3):
		media = media + [notas[0][j+1]]
	minima = media[:]
	maxima = media[:]
	for i in range(1,len(notas)):
		for j in range(3):
			if minima[j] > notas[i][j+1]:
				minima[j] = notas[i][j+1]
			if maxima[j] < notas[i][j+1]:
				maxima[j] = notas[i][j+1]
			media[j] = media[j]+notas[i][j+1]
	for j in range(3):
		lista = lista+[(minima[j],maxima[j],media[j]/len(notas))]
	return lista
	

print('\nestatisticas')
print(estatisticas([('a',10,10,10),('b',12,10,11),('c',9,7,6),('d',15,12,10)]))
