def maximo( n1, n2, n3 ):
	"""n1, n2, n3: números inteiros
	resultado: maximo de n1, n2, n3"""
	if n1>=n2 and n1>=n3:
		return n1
	elif n2>=n1 and n2>=n3:
		return n2
	else:
		return n3
		

def minimo( n1, n2, n3 ):
	"""n1, n2, n3: números inteiros
	resultado: minimo de n1, n2, n3"""
	if n1<=n2 and n1<=n3:
		return n1
	elif n2<=n1 and n2<=n3:
		return n2
	else:
		return n3


def triangulo( a, b, c ):
	"""a, b, c: tamanho dos lado
	resultado: string com indicação se é triângulo e qual o seu tipo segundo os seus angulos"""
	lmax = maximo(a, b, c)
	if lmax == a:
		l1 = b
		l2 = c
	elif lmax == b:
		l1 = a
		l2 = c
	else:
		l1 = a
		l2 = b
	if lmax>=l1+l2:
		return 'Não é triangulo.'
	lmax = lmax**2
	l1 = l1**2
	l2 = l2**2
	if lmax == l1+l2:		
		return 'É um triangulo rectangulo'
	if lmax > l1+2:
		return 'É um triangulo obtuso'
	return 'É um triangulo agudo'
	

	
def raiz1( a, b, c ):
	"""a, b, c: coeficientes do polinomio de 2o grau
	resultado: primeira raiz real do poloinomio"""
	r2 = b**2-4*a*c
	if r2<0:		# nao tem raiz real
		return
	return (-b+r2)/(2*a)

def raiz2( a, b, c ):
	"""a, b, c: coeficientes do polinomio de 2o grau
	resultado: segunda raiz real do poloinomio"""
	r2 = b**2-4*a*c
	if r2<0:		# nao tem raiz real
		return
	return (-b-r2)/(2*a)


def calcula( n1, n2, op ):
	"""n1, n2: operandos (real)
	op: operador (string: '+', '-', '*', '/')
	resultado: n1 op n2"""
	if op == '+':
		return n1+n2
	if op == '-':
		return n1-n2
	if op == '*':
		return n1*n2
	if op == '/':
		return n1/n2


def mdc( n1, n2 ):
	"""n1, n2: numeros inteiros
	resultado: maximo divisor comum"""
	if n1>=n2:
		M = n1
		m = n2
	else:
		M = n2
		m = n1
	i = 1
	r = m
	while M%r != 0:
		i = i+1
		r = r//i
	return r
	

def mmc( n1, n2 ):
	"""n1, n2: numeros inteiros
	resultado: minimo multiplo comum"""
	if n1>=n2:
		M = n1
		m = n2
	else:
		M = n2
		m = n1
	i = 1
	r = M
	while r%m != 0:
		i = i+1
		r = M*i
	return r


def somacubos( n ):
	"""n: inteiro de 3 algarismos
	resultado: True se n igual à soma do cubo dos algarismos que os constituem; False caso contrario"""
	a1 = n//100
	aux = n%100
	a2 = aux//10
	a3 = aux %10
	return n == a1**3+a2**3+a3**3


def serie( x ):
	"""x: valor real
	resultado: valor dos 10 primeiros termos"""
	i = 1
	r = 0
	while i<=10:
		v = 2*i-1
		r = r+(-1)**(i-1)*1/v*x**v
		i = i+1
	return r
	

def fatorial( n ):
	"""n: valor inteiro
	resultado: fatorial de n"""
	r = 1
	while n>0:
		r = r*n
		n = n-1
	return r

	
