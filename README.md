# Computacao Distribuida

Repositório com exercícios, exemplos e trabalhos desenvolvidos para a disciplina de Computação Distribuída. O conteúdo reúne implementações em Java, Python, C e C++ envolvendo comunicação por sockets, Java RMI, gRPC, redes P2P, concorrência e padrões de interação entre serviços.

## Conteúdo do repositório

| Caminho | Descrição |
| --- | --- |
| `Treino P1/` | Exercícios de Java RMI para treino da P1, incluindo verificação de primos gêmeos, calculadora de frações e calculadora de números complexos. |
| `exemplo_sockets/` | Exemplo de servidor TCP em Java com clientes em Python e C. |
| `calc/` | Serviço gRPC em Python para multiplicação de frações. |
| `NumerosComplexos/` | Calculadora gRPC em Python para operações básicas com números complexos. |
| `Redes_P2P/` | Exemplos de transferência de arquivos entre peers e busca por query flooding. |
| `orquestracao_coreografia/` | Simulações em Python comparando orquestração e coreografia em um fluxo simples de pedidos. |
| `ContaCorrente.cpp` | Exemplo de concorrência com threads e mutex em C++. |
| `program.c` e `dados.txt` | Exemplo em C para leitura, ordenação e exibição de dados de pessoas a partir de arquivo. |
| `refatorar/` | Código Python separado para refatoração/experimentos. |

## Pré-requisitos

Instale as ferramentas conforme o módulo que será executado:

- Java JDK 8 ou superior, para os exemplos em Java e RMI.
- Python 3.10 ou superior, para os exemplos em Python.
- `grpcio` e `grpcio-tools`, para os serviços gRPC em Python.
- Compilador C, como `gcc`, para `program.c`.
- Compilador C++, como `g++`, para `ContaCorrente.cpp`.

Para instalar as dependências Python usadas pelos exemplos gRPC:

```bash
python3 -m pip install grpcio grpcio-tools
```

## Como executar

Os comandos abaixo assumem que o terminal está aberto na raiz do repositório.

### gRPC: calculadora de frações

O diretório `calc/` contém um servidor gRPC que multiplica duas frações.

```bash
cd calc
python3 calc_server.py
```

Em outro terminal:

```bash
cd calc
python3 calc_client.py
```

Caso seja necessário regenerar os arquivos Python a partir do `.proto`:

```bash
cd calc
python3 -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. calc.proto
```

### gRPC: números complexos

O diretório `NumerosComplexos/` implementa soma, subtração, multiplicação e divisão de números complexos via gRPC. Os números são enviados como strings no formato `a + bi` ou `a - bi`, com espaços entre os números e os sinais.

```bash
cd NumerosComplexos
python3 server.py
```

Em outro terminal:

```bash
cd NumerosComplexos
python3 client.py
```

Para regenerar os arquivos derivados do `.proto`:

```bash
cd NumerosComplexos
python3 -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. calculator.proto
```

### Java RMI: Treino P1

Os exercícios em `Treino P1/` usam pacotes Java. Compile a partir da pasta `Treino P1`:

```bash
cd "Treino P1"
javac Treino1/*.java Treino2/*.java Treno3/*.java
```

Execute primeiro o servidor do exercício desejado e depois o cliente correspondente em outro terminal.

Exemplo para `Treino1`:

```bash
cd "Treino P1"
java Treino1.Server
```

Em outro terminal:

```bash
cd "Treino P1"
java Treino1.Client
```

Exemplo para `Treino2`:

```bash
cd "Treino P1"
java Treino2.Servidor
```

Em outro terminal:

```bash
cd "Treino P1"
java Treino2.Clientela
```

Exemplo para `Treno3`:

```bash
cd "Treino P1"
java Treno3.Servidor
```

Em outro terminal:

```bash
cd "Treino P1"
java Treno3.Cliente
```

### Sockets TCP

O diretório `exemplo_sockets/` contém um servidor Java e clientes em Python/C. Antes de executar os clientes, confira os valores de `HOST` e `PORT`, pois alguns arquivos usam IPs locais fixos.

```bash
cd exemplo_sockets
javac Server.java
java Server
```

Cliente Python:

```bash
cd exemplo_sockets
python3 ClientPython.py
```

Cliente C:

```bash
cd exemplo_sockets
gcc ClientC.c -o client
./client
```

### Redes P2P

Transferência simples de arquivo:

```bash
cd Redes_P2P
javac Sender.java Receiver.java
java Sender
```

Em outro terminal:

```bash
cd Redes_P2P
java Receiver
```

O arquivo `Receiver.java` possui uma lista de IPs em `HOSTS`; ajuste esses endereços conforme a rede usada no teste.

Busca por query flooding:

```bash
cd Redes_P2P/QueryFlooding
javac P2PNetwork.java
java P2PNetwork
```

Peer interativo:

```bash
cd Redes_P2P/QueryFlooding
javac Peer.java
java Peer
```

### Orquestração e coreografia

Os exemplos simulam um fluxo de catálogo, carrinho e pedido.

Orquestração:

```bash
cd orquestracao_coreografia/Orquestração
python3 Controller.py
```

Coreografia:

```bash
cd orquestracao_coreografia/Coreografia
python3 Controller.py
```

### Concorrência em C++

```bash
g++ ContaCorrente.cpp -o conta_corrente -pthread
./conta_corrente
```

### Leitura e ordenação em C

```bash
gcc program.c -o program
./program
```

O programa lê os dados do arquivo `dados.txt`.

## Observações

- Arquivos como `*_pb2.py` e `*_pb2_grpc.py` são gerados a partir dos arquivos `.proto`.
- Pastas `__pycache__/` são geradas automaticamente pelo Python e não fazem parte da lógica principal do projeto.
- Alguns exemplos usam portas e IPs fixos. Caso a conexão falhe, verifique se servidor e cliente usam a mesma porta e se o endereço IP corresponde à máquina correta.
- Em sistemas Windows, comandos como `clear` nos scripts Python podem precisar ser trocados por `cls`.
