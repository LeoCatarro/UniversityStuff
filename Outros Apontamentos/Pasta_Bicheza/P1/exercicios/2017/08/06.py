def fibonacci_ate(n):
	if n == 1:
		return str(1)
	elif n == 2:
		return str(1)+' '+str(1)
	else:
		s = '1 1'
		n1 = 1
		n2 = 1
		f = n1+n2
		while f<=n:
			s = s+' '+str(f)
			n2 = n1
			n1 = f
			f = n1+n2
		return s
