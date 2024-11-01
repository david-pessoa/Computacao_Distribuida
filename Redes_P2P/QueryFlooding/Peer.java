import java.io.*;
import java.net.*;
import java.util.*;

public class Peer {
    private int port;
    private Set<String> arquivos;
    private List<InetSocketAddress> conhecidos;

    public Peer(int port) {
        this.port = port;
        this.arquivos = new HashSet<>();
        this.conhecidos = new ArrayList<>();
    }

    // Adiciona arquivo à lista do peer
    public void adicionarArquivo(String arquivo) {
        arquivos.add(arquivo);
        System.out.println("Arquivo '" + arquivo + "' adicionado ao peer.");
    }

    // Adiciona conhecido à lista de peers conhecidos
    public void adicionarConhecido(String ip, int porta) {
        conhecidos.add(new InetSocketAddress(ip, porta));
    }

    // Inicia o servidor para receber pedidos de busca de outros peers
    public void iniciarServidor() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Peer rodando na porta " + port);

                while (true) {
                    try (Socket socket = serverSocket.accept()) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                        String comando = in.readLine();
                        String nomeArquivo = in.readLine();

                        if ("BUSCAR".equals(comando)) {
                            if (arquivos.contains(nomeArquivo)) {
                                out.println("ENCONTRADO");
                                out.println(socket.getLocalAddress().getHostAddress());
                                out.println(port);
                            } else {
                                buscarArquivoNosConhecidos(nomeArquivo, out);
                            }
                        } else if ("DOWNLOAD".equals(comando)) {
                            enviarArquivo(nomeArquivo, socket.getOutputStream());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Busca o arquivo recursivamente entre os conhecidos
    private void buscarArquivoNosConhecidos(String nomeArquivo, PrintWriter out) {
        for (InetSocketAddress conhecido : conhecidos) {
            try (Socket socket = new Socket(conhecido.getAddress(), conhecido.getPort())) {
                PrintWriter outConhecido = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader inConhecido = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                outConhecido.println("BUSCAR");
                outConhecido.println(nomeArquivo);
                
                String resposta = inConhecido.readLine();

                if ("ENCONTRADO".equals(resposta)) {
                    String ipPeer = inConhecido.readLine();
                    int portaPeer = Integer.parseInt(inConhecido.readLine());
                    out.println("ENCONTRADO");
                    out.println(ipPeer);
                    out.println(portaPeer);
                    return;
                }
            } catch (IOException e) {
                System.out.println("Falha ao conectar com " + conhecido);
            }
        }
        out.println("NÃO ENCONTRADO");
    }

    // Método para enviar arquivo
    private void enviarArquivo(String nomeArquivo, OutputStream outputStream) {
        File file = new File(nomeArquivo);
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado para envio: " + nomeArquivo);
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(outputStream)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            System.out.println("Arquivo '" + nomeArquivo + "' enviado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para realizar busca e download do arquivo
    public void pesquisarEObterArquivo(String nomeArquivo) {
        System.out.println("Iniciando busca pelo arquivo: " + nomeArquivo);

        for (InetSocketAddress conhecido : conhecidos) {
            try (Socket socket = new Socket(conhecido.getAddress(), conhecido.getPort())) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("BUSCAR");
                out.println(nomeArquivo);

                String resposta = in.readLine();

                if ("ENCONTRADO".equals(resposta)) {
                    String ipPeer = in.readLine();
                    int portaPeer = Integer.parseInt(in.readLine());
                    obterArquivoDePeer(ipPeer, portaPeer, nomeArquivo);
                    return;
                }
            } catch (IOException e) {
                System.out.println("Falha ao conectar com " + conhecido);
            }
        }
        System.out.println("Arquivo não encontrado na rede.");
    }

    // Método para obter arquivo de outro peer
    private void obterArquivoDePeer(String ip, int porta, String nomeArquivo) {
        try (Socket socket = new Socket(ip, porta)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("DOWNLOAD");
            out.println(nomeArquivo);

            try (InputStream inputStream = socket.getInputStream();
                 FileOutputStream fos = new FileOutputStream("baixado_" + nomeArquivo);
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                System.out.println("Arquivo '" + nomeArquivo + "' baixado e salvo como 'baixado_" + nomeArquivo + "'.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao obter arquivo de peer: " + ip + ":" + porta);
            e.printStackTrace();
        }
    }

    // Método principal para inicializar o peer
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configuração da porta do peer
        System.out.print("Digite a porta do peer: ");
        int porta = scanner.nextInt();
        scanner.nextLine();

        Peer peer = new Peer(porta);

        // Configuração dos peers conhecidos
        System.out.println("Digite o IP e a porta dos peers conhecidos (Formato: IP porta, ou 'fim' para parar): ");
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("fim")) {
                break;
            }
            String[] partes = entrada.split(" ");
            if (partes.length == 2) {
                String ip = partes[0];
                int portaConhecido = Integer.parseInt(partes[1]);
                peer.adicionarConhecido(ip, portaConhecido);
            }
        }

        // Inicia o servidor para requisições
        peer.iniciarServidor();

        // Menu de ações
        while (true) {
            System.out.println("\nEscolha uma ação:");
            System.out.println("1 - Adicionar arquivo");
            System.out.println("2 - Pesquisar e obter arquivo");
            System.out.println("3 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do arquivo para adicionar: ");
                    String arquivo = scanner.nextLine();
                    peer.adicionarArquivo(arquivo);
                    break;
                case 2:
                    System.out.print("Digite o nome do arquivo para pesquisar: ");
                    String nomeArquivo = scanner.nextLine();
                    peer.pesquisarEObterArquivo(nomeArquivo);
                    break;
                case 3:
                    System.out.println("Encerrando peer...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
