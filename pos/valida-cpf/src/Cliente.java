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
            final String cpf = JOptionPane.showInputDialog("Digite o CPF", entrada);
            saida.writeUTF(cpf);

            // Receber resposta
            entrada = new DataInputStream(conexao.getInputStream());

            final boolean resultado = entrada.readBoolean();
            System.out.println(resultado ? "CPF válido" : "CPF inválido");

            // Fechar conexão
            conexao.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
