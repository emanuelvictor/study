package tcp.objetos;

import tcp.objetos.entity.Usuario;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Cliente {

    private static Socket conexao;
    private static DataInputStream entrada;
    private static ObjectOutput saida;

    public static void main(String[] args) {

        try {
            // Solicitar conexão
            conexao = new Socket("127.0.0.1", 4000);

            starRunnable().run();

            // Fechar conexão
            conexao.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Runnable starRunnable() {
        return () -> {

            try {

                // Enviar dados
                saida = new ObjectOutputStream(conexao.getOutputStream());

                saida.writeObject(new Usuario("nome", 40));

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
