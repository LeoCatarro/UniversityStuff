import math
x1=float(input('Indique a coordenada x do ponto 1: '))
y1=float(input('Indique a coordenada y do ponto 1: '))
x2=float(input('Indique a coordenada x do ponto 2: '))
y2=float(input('Indique a coordenada y do ponto 2: '))
distancia=math.sqrt((x1-x2)**2+(y1-y2)**2)
print( 'A distancia entre os dois pontos é', distancia, '.')
