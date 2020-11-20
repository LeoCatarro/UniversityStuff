def rot13(s):
	s = s.lower()
	r = ''
	for i in range(len(s)):
		c = ord(s[i])+13
		if c>ord('z'):
			# se e posterior a 'z' e preciso retirar 26 (letras do alfabeto)
			c = c-26
		r = r+chr(c)
	return r
