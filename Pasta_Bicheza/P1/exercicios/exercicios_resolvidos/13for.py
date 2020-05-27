def fibonacci(n):
    k=[1,1]
    t=2
    for t in range(n-2):
        k.append(k[t] + k[t+1])
    print(k)
