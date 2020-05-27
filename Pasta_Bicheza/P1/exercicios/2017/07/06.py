def linhaT(n):
	i = 1
	s = ''
	while i<=n:
		s = s+' '+str(i)
		i = i+1
	return s



n = int(input('Quantas linhas? '))
l = 1
while l<=n:
	print(linhaT(l))
	l = l+1
