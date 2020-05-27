def palindromo(s):
	i = 0
	# compara a 1a letra com a ultima, a 2a com a penultima, etc
	# ate ao meio da palavra
	while s[i]==s[-i-1] and i<len(s)//2:
		i = i+1
 	# e palindromo se o ciclo terminar a meio da palavra
 	return i==len(s)//2
		
