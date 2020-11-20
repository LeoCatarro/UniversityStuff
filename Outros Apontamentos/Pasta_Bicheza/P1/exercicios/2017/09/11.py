def ocorrencias_case(letra,string,indice):
	c = 0
	for i in range(indice,len(string)):
		if letra.lower() == string[i].lower():
			c = c+1
	return c
