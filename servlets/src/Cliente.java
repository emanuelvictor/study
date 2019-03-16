
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public static void main(String[] args) {
        final DatagramSocket datagramSocket;
        try {
            datagramSocket = new DatagramSocket();

            final String solicitacao = "Mande uma piada";
            final byte[] msg = solicitacao.getBytes();

            InetAddress endereco = InetAddress.getByName("localhost");

            DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length, endereco, 40000);

            datagramSocket.send(datagramPacket);

            final byte[] responseMsg = new byte[128];

            datagramPacket = new DatagramPacket(responseMsg, responseMsg.length);

            datagramSocket.receive(datagramPacket);

            final String piada = new String(datagramPacket.getData());

            System.out.println(piada);

            if (piada.contains("Acabaram as piadas")) {
                System.exit(0);
            }

            Thread.sleep(60);

            main(null);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
