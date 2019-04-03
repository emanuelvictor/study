import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteRMI {

    public ClienteRMI() {
    }


    public static void main(String[] args) {
        try {

            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            Validadora stub = (Validadora) registro.lookup("metodosValidadores");

            final String cpf = JOptionPane.showInputDialog("Digite o CPF");
            System.out.println("O CPF " + cpf + " é " + (stub.validarCPF(cpf) ? "válido" : "inválido"));

//            System.out.println("O número 10 é: " + stub.maiorMenorZero(10));

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
