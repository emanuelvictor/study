package tcp.objetos;

import tcp.objetos.entity.Usuario;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static Socket conexao;
    private static ServerSocket servidor;
    private static ObjectInput entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {
        try {
            // Reservar uma porta
            servidor = new ServerSocket(4000);

            // Aguardar conexões
            System.out.println("Aguardando conexão");
            conexao = servidor.accept();

            startTrhead().run();

            // Fecha conexão
            conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Runnable startTrhead() {
        return () -> {
            // Receber dados
            try {

                entrada = new ObjectInputStream(conexao.getInputStream());

                final Usuario usuario = (Usuario) entrada.readObject();

                System.out.println("Usuario recebido");
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("Idade: " + usuario.getIdade());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        };
    }


}
