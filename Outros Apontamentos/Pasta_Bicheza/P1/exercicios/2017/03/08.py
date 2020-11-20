valor=int(input('Qual o numero? '))
if valor < 0:
	print('Valor negativo')
else:
	fatorial = 1
	n = 2
	while n <= valor:
		fatorial = fatorial*n
		n = n+1
	print('O fatorial de', valor, 'é', fatorial)
