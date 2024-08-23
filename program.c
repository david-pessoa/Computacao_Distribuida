#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct objeto
{
    char nome[50];
    int idade;
    float altura;
}objeto;

void add_pessoa(FILE* arquivo, objeto ob[], int i)
{
    fscanf(arquivo, "%s", ob[i].nome);
    fscanf(arquivo, "%d", &ob[i].idade);
    fscanf(arquivo, "%f", &ob[i].altura);
}

char* compara(char a[], char b[])
{
  int num = strcmp(a, b);
    return (int*) num;
}

void sort_objetos(objeto ob[], int n)
{
    qsort(ob, n, sizeof(objeto), compara);
}

void show_data(objeto ob[], int n)
{
    for(int i = 0; i < n; i++)
    {
        printf("\nNome: %s\n", ob[i].nome);
        printf("Idade: %d\n", ob[i].idade);
        printf("Altura: %.1f\n", ob[i].altura);
    }
}

int main()
{
    objeto objetos[10];
    FILE* arquivo;
    int i = 0;

    arquivo = fopen("dados.txt", "r");
    if(arquivo == NULL)
    {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    while(!feof(arquivo))
    {
        add_pessoa(arquivo, objetos, i);
        i++;
    }
    fclose(arquivo);

    sort_objetos(objetos, i);
    show_data(objetos, i);
    return 0;

}