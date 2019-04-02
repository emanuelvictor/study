package tcp;

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

            // Aguardar conexões
            System.out.println("Aguardando conexão");
            conexao = servidor.accept();

            entrada = new DataInputStream(conexao.getInputStream());

            final int valor = entrada.readInt();

            // Responder cliente
            final String retorno = valor > 0 ? "O valor é maior que zero" : "O valor é menor que zero";
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(retorno);

            // Fecha conexão
            conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
