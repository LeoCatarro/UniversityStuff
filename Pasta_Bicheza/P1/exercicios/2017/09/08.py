def vogal(s):
	return s in 'aeiouAEIOU'

def conta_vogais(s):
	c = 0
	for i in range(len(s)):
		if vogal(s[i]):
			c = c+1
	return c
	
