import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorImpl implements Validadora {

    public ServidorImpl() {
    }

    @Override
    public boolean validarCPF(final String CPF) throws RemoteException {
        return CPF != null && CPF.length() == 11;
    }

    @Override
    public String maiorMenorZero(final int valor) throws RemoteException {
        return valor > 0 ? "Maior que zero!" : "Menor que zero";
    }

    public static void main(String[] args) {
        try {

            Validadora servidor = new ServidorImpl();

            // Criando canal
            Validadora stub = (Validadora) UnicastRemoteObject.exportObject(servidor, 0);

            // Instancia o servidor RMI do Java (Servidor que vem imbutido no JAVA)
            Registry registro = LocateRegistry.createRegistry(1099);

            registro.bind("metodosValidadores", stub);

            System.out.println("Servidor RMI está pronto!");

        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
