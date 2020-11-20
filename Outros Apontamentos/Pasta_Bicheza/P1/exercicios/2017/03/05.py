soma = 0
n = 0
num = int(input('Qual o valor? '))
while num != 0:
	soma = soma+num
	n = n+1
	num = int(input('Qual o valor? '))
print('Foram introduzidos', n, 'valores e a média é', soma/n)

