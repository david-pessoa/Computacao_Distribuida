class Carrinho:
    def __init__(self):
        self.lista_livros = []
        self.custo_total = 0
        self.qtde_livros = 0
    
    def showCarrinho(self):
        custo = 0
        for livro in range(self.lista_livros):
            print("Nome\tPreço")
            print(livro[0] + '\t' + f"{livro[1]}")
            custo += livro[1]
        self.custo_total = custo
        print(f"\nTotal: {self.custo_total}")
    
    def addNoCarrinho(self, new_book):
        self.lista_livros.append(new_book)
        self.custo_total += new_book[1]
        self.qtde_livros += 1
        


