def letras_inv1(s):
	for i in range(len(s)-1,-1,-1):
		print(s[i])

def letras_inv2(s):
	r = s[::-1]
	for i in range(len(r)):
		print(r[i])
