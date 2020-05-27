ano = int(input("Qual é o ano?"))

def bissexto(ano):
    if ano%4 == 0 and ano%100 != 0:
        print ("O ano", ano," é bissexto")
    elif ano%400 == 0:
        print ("0 ano,", ano," é bissexto")
    else:
        print ("O ano,", ano," não é bissexto")

bissexto(ano)
