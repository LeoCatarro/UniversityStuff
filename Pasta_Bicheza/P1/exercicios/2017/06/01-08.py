def fatorial(n):
	i = 2
	f = 1
	while i<=n:
		f =f*i
		i = i+1
	return f

	
def fatorialR(n):
	if n==1:
		return 1
	else:
		return n*fatorialR(n-1)
		

def soma(n):
	if n==1:
		return 1
	else:
		return n+soma(n-1)


def multiplo(n,i):
	if i == 1:
		return n
	else:
		return n+multiplo(n,i-1)
		
		
def mdc(m,n):
	if m==n:
		return m
	elif m>n:
		return mdc(m-n,n)
	else:
		return mdc(m,n-m)
		

def ackermann(m,n):
	if m==0:
		return n+1
	elif n==0:
		return ackermann(m-1,1)
	else:
		return ackermann(m-1,ackermann(m,n-1))
		

def pascal(l,c):
	if l==c or c==0:
		return 1
	else:
		return pascal(l-1,c-1)+pascal(l-1,c)
	
		
def trianguloPascal(n):
	l = 1
	while l<=n:
		c = 1
		s='1 '
		while c<=l:
			s = s+str(pascal(l,c))+' '
			c = c+1
		print(s)
		l = l+1
		
def trianguloPascalR(n):
	if n==1:
		print(n)
	else:
		
	
		
#print(fatorial(5))
#print(fatorialR(5))
#print(soma(5))
#print(multiplo(5,3))
#print(mdc(28,8))
#print(ackermann(3,4))
#print(pascal(6,3))
trianguloPascal(5)


