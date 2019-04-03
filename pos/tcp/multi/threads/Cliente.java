package multi.threads;

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

            boolean continuidade = true;

            while (continuidade) {
// Solicitar conexão
                conexao = new Socket("127.0.0.1", 4000);
                // Enviar dados
                saida = new DataOutputStream(conexao.getOutputStream());

                entrada = null;
                final String mensagem = JOptionPane.showInputDialog("Digite a mensagem", entrada);

                if (mensagem == null || mensagem.trim().toLowerCase().equals("sair"))
                    continuidade = false;
                else {
                    if (mensagem.length() > 0)
                        saida.writeUTF(mensagem);
                }

                // Fechar conexão
                conexao.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
