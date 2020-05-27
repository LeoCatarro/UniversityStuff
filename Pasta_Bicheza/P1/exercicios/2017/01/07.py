#7
num = int(input('Quantos segundos? '))
segundos = num % 60
aux = num // 60
minutos = aux % 60
aux = aux // 60
horas = aux % 24
dias = aux // 24
print( num, "segundos correspondem a", dias, "dias,", horas, "horas,", minutos, "minutos e", segundos, "segundos." )
