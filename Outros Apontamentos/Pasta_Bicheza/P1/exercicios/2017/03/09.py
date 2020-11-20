a = int(input('valor de a: '))
b = int(input('valor de b: '))
num = 1
while num <= 50:
	if num%a == 0 or num%b == 0:
		print(num)
	num = num+1
