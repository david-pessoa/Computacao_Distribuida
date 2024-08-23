#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//Integrantes:
//David Pessoa  10402647
//João Pedro de Souza Costa Ferreira  10400720

typedef struct objeto //cria struct objeto com nome, idade e altura
{
    char nome[50];
    int idade;
    float altura;
}objeto;

void add_pessoa(FILE* arquivo, objeto ob[], int i) //lê os dados no arquivo e add a pessoa no vetor objetos
{
    fscanf(arquivo, "%s", ob[i].nome);
    fscanf(arquivo, "%d", &ob[i].idade);
    fscanf(arquivo, "%f", &ob[i].altura);
}

int* compara(char a[], char b[]) //Compara duas strings com strcmp()
{
  int num = strcmp(a, b);
    return (int*) num;
}

void sort_objetos(objeto ob[], int n) //Ordena os objetos no vetor na ordem alfabética dos nomes
{
    qsort(ob, n, sizeof(objeto), compara);
}

void show_data(objeto ob[], int n) //Imprime os dados do vetor
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
    objeto objetos[10]; //Vetor de objetos
    FILE* arquivo; //arquivo lido
    int i = 0; // variável contadora de pessoas no arquivo

    arquivo = fopen("dados.txt", "r");
    if(arquivo == NULL)
    {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    while(!feof(arquivo))
    {
        add_pessoa(arquivo, objetos, i); //Add as pessoas e seus dados no vetor
        i++;
    }
    fclose(arquivo);

    sort_objetos(objetos, i); //Ordena e exibe o vetor
    show_data(objetos, i);
    return 0;

}