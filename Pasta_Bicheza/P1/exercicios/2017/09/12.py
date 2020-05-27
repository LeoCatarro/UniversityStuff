def conta_palavras(s):
	c = 0
	i = 0
	white = True
	
	while i<len(s):
		# se o caracter anterior for branco
		if white:
			# e o corrente for diferente de branco
			# encontrou uma palavra
			if (s[i]!=' ' and s[i]!=','):
				white = False
				c = c+1
		# se o caracter anterior for diferente de branco
		else:
			# e o corrente for branco
			if (s[i]==' ' or s[i]==','):
				white = True
		i = i+1
	return c
