x=float(input('Indique a coordenada x: '))
y=float(input('Indique a coordenada y: '))
if x>=0 and y>=0:
	print('O ponto encontra-se no 1º quadrante.')
elif x<0 and y>=0:
	print('O ponto encontra-se no 2º quadrante.')
elif x<0 and y<0:
	print('O ponto encontra-se no 3º quadrante.')
else:
	print('O ponto encontra-se no 4º quadrante.')
