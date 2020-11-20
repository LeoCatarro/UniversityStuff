def novoEstado(eAnterior, eAtual, comando):
	if eAtual == 'fechado':
		if comando == 'clic':
			return 'aabrir'
		else:
			return 'fechado'
	elif eAtual == 'aberto':
		if comando == 'clic':
			return 'afechar'
		else:
			return 'aberto'
	elif eAtual == 'afechar':
		if comando == 'clic':
			return 'parado'
		else:
			return 'fechado'
	elif eAtual == 'aabrir':
		if comando == 'clic':
			return 'parado'
		else:
			return 'aberto'
	else:	#parado
		if eAnterior == 'afechar':
			return 'aabrir'
		elif eAnterior == 'aabrir':
			return 'afechar'
		else:
			return eAtual
	
eAtual = 'fechado'
eAnterior = 'fechado'
acao = input('Qual a accao (clic,completo,fim)? ')
while acao !='fim':
	temp = novoEstado(eAnterior,eAtual,acao)
	eAnterior = eAtual
	eAtual = temp
	print(eAnterior, '->', acao, '->', eAtual)
	acao = input('Qual a accao (clic,completo,fim)? ')
