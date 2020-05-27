def sucessor( n ):
	"""n: número inteiro
	resultado: sucessor de n"""
	return n+1


def quadradoDoSucessor( n ):
	"""n: número inteiro
	resultado: quadrado do sucessor de n"""
	return sucessor(n)**2

	
def velocidade( v0, a, t ):
	"""v0: velocidade inicial
	a: aceleração
	t: tempo
	resultado: velocidade final"""
	return v0+a*t

	
def posicao( p0, v0, a, t ):
	"""p0: posicial inicial
	v0: velocidade inicial
	a: aceleração
	t: tempo
	resultado: posição final"""
	return p0+v0*t+1/2*a*t**2

	
def rightJustify( s ):
	"""s: cadeia de caracteres
	resultado: s justificado à direita"""
	n = 70-len(s)
	i = 0
	s = ''
	while i<n:
		s = s+' '
		i = i+1
	return s

	
def custoEnvio( n, custo1, custon ):
	"""n: numero de livros
	custo1: custo da primeira cópia
	custon: custo das restantes cópias
	resultado: custo do envio da encomenda"""
	return custo1+(n-1)*custon


def custoEncomenda( n, preco, custo1, custon ):
	"""n: numero de livros
	preco: preco de uma cópia
	custo1: custo da primeira cópia
	custon: custo das restantes cópias
	resultado: custo total da encomenda"""
	return n*preco+custoEnvio( n, custo1, custon )

	
def tempoDecorrido( d, v ):
	"""d: distancia percorrida
	v: velocidade
	resultado: tempo necessário para percorrer a distancia d à velocidade v"""
	return d/v		# em horas

	
def horaChegada( hp, mp ):	# assume-se que não há mudanca de dia
	"""hp: hora de partida
	mp: minuto de partida
	resultado: string com a hora e minuto de chegada"""
	t1 = tempoDecorrido( 2, 7.5 )
	t2 = tempoDecorrido( 6, 10.9 )
	t3 = tempoDecorrido( 2, 7.5 )
	total = t1+t2+t3
	toth = int(total)
	totm = total-toth
	hchegada = int(hp+t1+t2+t3)
	mchegada = int((mp+totm)*60)
	if mchegada >=60:
		hchegada = hchegada+1
		mchegada = mchegada-60
	return 'A chegada é às ', hchegada, 'hora e', mchegada, 'minutos.'

	
def triangulo( a, b, c ):
	"""a: tamanho lado a
	b: tamanho lado b
	c: tamanho lado c
	resultado: string com indicação se é triângulo e qual o seu tipo"""
	if a+b>c and a+c>b and b+c>a:
		if a==b and a==c:
			return 'É um triangulo equilatero'
		elif a!=b and a!=c and b!=a:
			return 'É um triangulo escaleno'
		else:
			return 'É um triangulo isósceles'
	else:
		return 'Não é triangulo.'
