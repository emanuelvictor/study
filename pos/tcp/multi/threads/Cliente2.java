package multi.threads;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente2 {

    private static Socket conexao;
    private static DataOutputStream saida;

    public static void main(String[] args) {

        try {

            for (int i = 0; i < 1000; i++) {

                // Solicitar conexão
                conexao = new Socket("127.0.0.1", 4000);
                // Enviar dados
                saida = new DataOutputStream(conexao.getOutputStream());

                final String mensagem = String.valueOf(i);
                saida.writeUTF(mensagem);

                // Fechar conexão
                conexao.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
