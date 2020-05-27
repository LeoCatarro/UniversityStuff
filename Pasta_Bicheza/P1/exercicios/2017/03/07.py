gasolina95 = 1.364
gasoleo = 1.149
gasolina98 = 1.414
litros = float(input('Quantos litros? '))
combustivel = input('Qual o combustivel (gasoleo, gasolina95, gasolina98)? ')
if combustivel == 'gasolina98':
	valor = litros*gasolina98
else:
	dia = int(input('Qual o dia do abastecimento? '))
	if combustivel == 'gasolina95':
		if dia <15:
			valor = litros*gasolina95
		else:
			valor = litros*(gasolina95-0.021)
	else:
		if dia < 15:
			valor = litros*gasoleo
		else:
			valor = litros*(gasoleo-0.023)
print('O custo de', litros, 'de', combustivel, 'é de', round(valor,2), 'EUR')
			
