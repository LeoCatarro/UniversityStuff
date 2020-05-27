a = int(input('valor de a: '))
b = int(input('valor de b: '))
num = 1
while num <= 50:
	if num%a == 0 and num%b == 0:
		print(num, ', multiplo de', a, ', multiplo de', b)
	elif num%a == 0:
		print(num, ', multiplo de', a)
	elif num%b == 0:
		print(num, ', multiplo de', b)
	num = num+1

