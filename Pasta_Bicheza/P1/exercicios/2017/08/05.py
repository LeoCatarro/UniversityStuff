def fibonacci_n(n):
	if n == 1:
		return str(1)
	elif n == 2:
		return str(1)+' '+str(1)
	else:
		s = '1 1'
		n1 = 1
		n2 = 1
		for i in range(3,n+1):
			f = n1+n2
			s = s+' '+str(f)
			n2 = n1
			n1 = f
		return s
