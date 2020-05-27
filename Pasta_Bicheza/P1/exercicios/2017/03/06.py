import math

epsilon = 0.0001
valor = int(input('Qual o valor? '))
raiz = math.sqrt(valor)

estimativa = 10.0
nova = (valor+estimativa/valor)/2
while abs(nova-estimativa) >= epsilon:
	estimativa=nova
	nova = (estimativa+valor/estimativa)/2
print('raiz =',nova)
print('math.sqrt =',raiz)
print('diferenca=',abs(nova-raiz))
