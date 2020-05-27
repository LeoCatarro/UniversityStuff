mensagem="\nIndique a sua opcao:\
\nadicionar produto(1), consultar itens no carrinho (2), retirar os primeiros n (3), sair (0)"

tabela_de_preços='bananas=0.99\ntangerinas=1.29\nmaçã=1\nkiwi=1.80'

k={'bananas':0.99,'tangerinas':1.29,'maçãs':1,'kiwis':1.80}
t={}
lista_produtos=['bananas','tangerinas','maçãs','kiwi']
total = 0

while True:
    print(mensagem)
    print(tabela_de_preços)
    op= int(input("opcao -> "))
    if op == 0:
        break
        print('Bye!')
    if op == 1:
        produto = str(input('Qual o produto a ser adicionado?'))
        quantidade = int(input('Quantos itens?'))        
        total = total + k[produto] * quantidade
        t[produto]=quantidade        
    if op == 2:
        print(t)
    if op == 3:
        a_eliminar = str(input(('Qual o produto que dejesa retirar do carro?')))
        total_absuluto = total - k[a_eliminar] * t[a_eliminar]
        del(t[a_eliminar])
print(total_absuluto)
