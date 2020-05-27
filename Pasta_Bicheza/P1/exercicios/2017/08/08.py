def e_primo(n):
	if n==1 or n==2:
		return True
	i = 2
	while n%i!=0 and i<n//2:	# basta apenas verificar o resto da divisao ate n//2
		i = i+1
	return n%i!=0 and i==n//2


def mostra_primos(n):
	for i in range(2,n+1):
		if e_primo(i):
			print(i)
