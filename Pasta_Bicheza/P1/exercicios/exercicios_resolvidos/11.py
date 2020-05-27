import math

x1 = float(input('Valor de x1'))
x2 = float(input('Valor de x2'))
y1 = float(input('Valor de y1'))
y2 = float(input('Valor de y2'))

Y = y1==y2
X = x1==x2

def distance(x1, y1, x2, y2):
    if X and (y1 > y2):
        R = y1 - y2
    elif X and (y2 > y1):
        R = y2 - y1
    elif Y and (x1 > x2):
        R = x1 -  x2
    elif Y and (x2 > x1):
        R = x2 - x1
    elif (x1 > x2) and (y1 > y2):
        R = math.sqrt(( x1 - x2 )**2 + ( y1 - y2 )**2)
    elif (x2 > x1) and (y2 > y1):
        R = math.sqrt(( x2 - x1 )**2 + ( y2 - y1 )**2)
    elif (x1 > x2) and (y2 > y1):
        R = math.sqrt(( x1 - x2 )**2 + ( y2 - y1 )**2)
    elif (x2 > x1) and (y1 > y2):
        R = math.sqrt(( x2 - x1 )**2 + ( y1 - y2 )**2)
    return R   
 
print ("A distancia entre os pontos (",x1,",",y1,") e (",x2,",",y2,") é", distance(x1, y1, x2, y2))

