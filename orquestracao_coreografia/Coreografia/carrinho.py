class Carrinho:
    def __init__(self):
        self.lista_livros = []
        self.custo_total = 0
        self.qtde_livros = 0
    
    def showCarrinho(self):
        self.qtde_livros = len(self.lista_livros)
        if self.qtde_livros != 0:
            custo = 0
            print("ID\tNome\tPreço")
            for i, livro in enumerate(self.lista_livros):
                print(f"{i + 1}\t" + livro[0] + '\t' + f"{livro[1]}")
                custo += livro[1]
            self.custo_total = custo
            print(f"\nTotal: {self.custo_total}")
        
        else:
            print("Carrinho vazio")
    
    def addNoCarrinho(self, new_book):
        self.lista_livros.append(new_book)
        self.custo_total += new_book[1]
        self.qtde_livros += 1
    
    def removeDoCarrinho(self, id):
        id -= 1
        self.lista_livros.remove(self.lista_livros[id])
        


