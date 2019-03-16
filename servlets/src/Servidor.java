import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Servidor {

    private String piada;

    private static int piadaIndex = 0;

    public static void main(final String[] args) {

        try {

            // Carrega as piadas
            final List<String> todasAsPiadas = Files.readAllLines(Paths.get("src/piadas.txt"), StandardCharsets.ISO_8859_1);

            final DatagramSocket datagramSocket = new DatagramSocket(40000);

            final byte[] msg = new byte[128];

            final DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length);

            datagramSocket.receive(datagramPacket);

            final String solicitacao = new String(datagramPacket.getData());

            if (solicitacao.contains("Mande uma piada")) {

                final InetAddress endereco = datagramPacket.getAddress();

                final int porta = datagramPacket.getPort();

                final String respostaMsg;
                if (piadaIndex == todasAsPiadas.size())
                    respostaMsg = "Acabaram as piadas";
                else
                    respostaMsg = "Piada " + (piadaIndex + 1) + ": " + todasAsPiadas.get(piadaIndex);

                piadaIndex++;

                final byte[] respostaMsgBytes = respostaMsg.getBytes();

                final DatagramPacket responseDatagramPacket = new DatagramPacket(respostaMsgBytes, respostaMsgBytes.length, endereco, porta);

                datagramSocket.send(responseDatagramPacket);

                datagramSocket.disconnect();
                datagramSocket.close();

                if (respostaMsg.equals("Acabaram as piadas"))
                    System.exit(0);
                else
                    main(null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
