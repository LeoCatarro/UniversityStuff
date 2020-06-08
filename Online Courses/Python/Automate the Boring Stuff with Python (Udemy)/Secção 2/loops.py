#Programa para testar While and For loops

#
#   -->while
#

size = input()
tamanho = int(size)
i=1

while i <= tamanho :
    print(i,end="\n")
    i+=1


#
#   ->for
#         

size = input()
tamanho = int(size)
i=1

for i in range(tamanho+1):
    print(i,end=" ")
    i+=1
    
##############################################################################
#                               Secção 2.6 / 2.7:
#
#   RESUMO:
#      --> while
#           while "start" operador "até onde vai":
#               ...
#      **********************************************************************      
#      --> for
#           for "start" in "até onde vai(ex.:range(10)-até 10" :
#               ...
#      **********************************************************************
#      --> NOTAs: 
#           range(start, limite , incrementação)
#           print(x,end="") - forma para apos print passar para next line
#               -end="\n"
#               -end=" "
#               ...               
##############################################################################