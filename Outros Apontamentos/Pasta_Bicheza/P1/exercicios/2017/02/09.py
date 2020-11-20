num=int(input('Insira um numero de 3 algarismos: '))
centena=num//100
unidade=num%10
if centena==unidade:
	print('O numero', num, 'é capicua.')
else:
	print('O numero', num, 'não é capicua.')
