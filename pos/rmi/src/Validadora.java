import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Validadora extends Remote {
    boolean validarCPF(final String CPF) throws RemoteException;
    String maiorMenorZero(final int valor) throws RemoteException;
}
