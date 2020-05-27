def ocorrencias(letra,string):
	c = 0
	for i in range(len(string)):
		if letra == string[i]:
			c = c+1
	return c
