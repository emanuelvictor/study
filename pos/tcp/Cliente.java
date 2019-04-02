package tcp;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {

        try {
            // Solicitar conexão
            conexao = new Socket("127.0.0.1", 4000);

            // Enviar dados
            saida = new DataOutputStream(conexao.getOutputStream());

            entrada = null;
            int dados = Integer.parseInt(JOptionPane.showInputDialog("Digite um valor", entrada));
            saida.writeInt(dados);

            // Receber resposta
            entrada = new DataInputStream(conexao.getInputStream());

            final String resultado = entrada.readUTF();
            System.out.println("Servidor enviou: " + resultado);

            // Fechar conexão
            conexao.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
