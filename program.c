#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct objeto
{
    char nome[50];
    int idade;
    float altura;
}objeto;

int main()
{
    objeto objetos[10];
    FILE* arquivo;
    char linha[256];
    char *conteudo = NULL;
    size_t tamanho = 0;
    int i = 0;

    arquivo = fopen("dados.txt", "r");
    if(arquivo == NULL)
    {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    while(fscanf(arquivo, "%s %d %f", objetos[i].nome, &objetos[i].idade, &objetos[i].altura) == 3)
    {
        if (fgets(objetos[i].nome, sizeof(objetos[i].nome), arquivo) == NULL)
            break;
        objetos[i].nome[strcspn(objetos[i].nome, "\n")] = '\0'; // Remove o newline

        // Leitura da idade (2ª linha)
        if (fscanf(arquivo, "%d", &objetos[i].idade) != 1)
            break;

        // Leitura da altura (3ª linha)
        if (fscanf(arquivo, "%f", &objetos[i].altura) != 1)
            break;

        // Ignora o newline após a altura
        fgetc(arquivo);
        printf("%s\n", objetos[i].nome);
        i++;
    }
    fclose(arquivo);

    for(int j = 0; j < i; j++)
    {
        printf("\nNome: %s\n", objetos[i].nome);
        printf("Idade: %d\n", objetos[i].idade);
        printf("Altura: %f\n", objetos[i].altura);
    }
    free(conteudo);
    return 0;

}
