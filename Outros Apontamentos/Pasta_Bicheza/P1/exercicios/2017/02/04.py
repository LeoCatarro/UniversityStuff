# 4
num1 = int(input('1º num: '))
num2 = int(input('2º num: '))
num3 = int(input('3º num: '))
if num1<=num2 and num2<=num3 or num3<=num2 and num2<=num1:
	print('O numero do meio inserido foi', num2)
elif num1<=num3 and num3<=num2 or num2<=num3 and num3<=num1:
	print('O numero do meio inserido foi', num3)
else:
	print('O numero do meio inserido foi', num1)
