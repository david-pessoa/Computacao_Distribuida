class Catalogo:
    def __init__(self):
        with open("livros.csv", "r") as file:
            self.num_livros = 0
            self.catalogo = []
            while True:
                nextLine = file.readline()
                if nextLine == '':
                    break

                new_list = nextLine.split(',')
                float(new_list[1])
                self.catalogo.append(new_list)
                self.num_livros += 1
    
    def showCatalogo(self):
        print("Título\tPreço\tAutor\tCategoria")
        for i, livro in enumerate(self.catalogo):
            print(f"{i}) " + livro[0] + '\t' + f"{livro[1]}" + '\t' + livro[2] + '\t' + livro[3])
    
    def getLivro(self, id):
        id -= 1
        return self.catalogo[id]
