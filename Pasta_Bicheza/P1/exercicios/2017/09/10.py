def ocorrencias(letra,string,indice):
	c = 0
	for i in range(indice,len(string)):
		if letra == string[i]:
			c = c+1
	return c
