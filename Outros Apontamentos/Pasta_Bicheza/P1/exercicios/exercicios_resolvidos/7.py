def combustivel(c,q):
    d=int(input("qual o dia do abastecimento?"))
    if c == "gasoleo":
        if (d < 15):
            return round(q*1.149, 2)# tem que ser em centimos             
        if (d >= 15):
            return round(q*(1.149-0.023),2)             
    elif c == "gasolina95":
        if( d < 15):
            return round(q*1.364, 2)            
        if( d >= 15):
            return round(q*(1.364-0.021), 2)
    elif c == "gasolina98":
        return round(q*1.414, 2)
    else:
        print("diga qual é o combustivél que quer usar")
         
q=float(input("Quantos litros? "))            
c=str(input("qual o combustivél(gasoleo, gasolina95, gasolina98? "))
print (combustivel(c,q))

