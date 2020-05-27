def mdc(m,n):
	if m==n:
		return m
	elif m>n:
		return mdc(m-n,n)
	else:
		return mdc(m,n-m)


def simplifica(n,d):
	divisor = mdc(n,d)
	s = 'n='+ str(n//divisor)+' d='+str(d//divisor)
	print(s)


def soma(n1,d1,n2,d2):
	if d1==d2:
		nr = n1+n2
		dr = d1
	else:
		nr = n1*d2+n2*d1
		dr = d1*d2
	simplifica(nr,dr)


def revFatorial(n):
	i = 1
	while n%(i+1)==0:
		 i = i+1
		 n = n//i
	if n==1:
		return i
	else:
		return 0

print(revFatorial(24))


def revAux(n, r):
	if n==1:
		return r
	elif n%(r+1)!=0:
		return 0
	else:
		return revAux(n//(r+1),r+1)

def revFatorialR(n):
	return revAux(n,1)
