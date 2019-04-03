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

            System.out.println("O CPF 07074762911 é: " + stub.validarCPF("07074762911"));

            System.out.println("O número 10 é: " + stub.maiorMenorZero(10));

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
