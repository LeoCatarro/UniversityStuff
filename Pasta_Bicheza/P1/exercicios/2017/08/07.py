def e_primo(n):
	if n==1 or n==2:
		return True
	i = 2
	# verifica se e divisivel por cada um dos numeros
	while n%i!=0 and i<n//2:
		i = i+1
	# e primo se o ciclo terminar em n//2
	return n%i!=0 and i==n//2
