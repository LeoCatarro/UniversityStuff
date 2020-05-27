import math
epsilon = 0.0001
def raiz2(a, x, epsilon):
        while True:
            y = (x + a/x)/2
            if abs(y-x) < epsilon:
                break
            x = y
        return y
def test_sqrt(a,x):
    print ("raiz2=", raiz2(a, x, epsilon))
    print ("math.sqrt=", math.sqrt(a))
    print ("diferenca=", raiz2(a,x,epsilon) - math.sqrt(a))
