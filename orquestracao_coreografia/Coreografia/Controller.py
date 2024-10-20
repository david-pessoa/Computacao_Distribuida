import os
from time import sleep
from carrinho import Carrinho
from catalogo import Catalogo
from coreografia import event_bus

catalogo = Catalogo()
carrinho = Carrinho()

while True:
    print(
'''
Menu:
1) Ver catálogo de produtos
2) Ver itens no carrinho
3) Adicionar item ao carrinho
4) Remover item do carrinho
5) Realizar pedido
6) Sair
''')
    choice = int(input("Escolha uma opção: "))

    if choice == 1:
        catalogo.showCatalogo()

    elif choice == 2:
        carrinho.showCarrinho()

    elif choice == 3:
        new_book_index = int(input("Escolha um item do catálogo pelo índice: "))
        new_book = catalogo.getLivro(new_book_index)
        carrinho.addNoCarrinho(new_book)
    
    elif choice == 4:
        book_index = int(input("Escolha um item do carrinho pelo índice: "))
        carrinho.removeDoCarrinho(book_index)
        
    elif choice == 5:
        book_index = int(input("Escolha um item do carrinho pelo índice: "))
        carrinho.removeDoCarrinho(book_index)
        pedido = catalogo.getLivro(book_index)
        event_bus.publish("order_created", pedido)
        
    elif choice == 6:
        exit()

    else:
        print("Opção inválida!")
    
    sleep(3)
    os.system('clear') #Mudar para 'cls' caso esteja no windows
    