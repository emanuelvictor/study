package multi.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static Socket conexao;
    private static ServerSocket servidor;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {
        try {
            // Reservar uma porta
            servidor = new ServerSocket(4000);

            // Toda vez ele starta uma nova trhead
            while (true) {
                // Aguardar conexões
                System.out.println("Aguardando conexão");
                conexao = servidor.accept();
                new Thread(server(conexao)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Runnable server(final Socket conexao) {
        return () -> {
            // Receber dados
            try {

                // Recebe dados
                entrada = new DataInputStream(conexao.getInputStream());
                final String valor = entrada.readUTF();
                System.out.println(valor);

                // Fecha conexão
                conexao.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
