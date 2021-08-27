import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        openFile("texto", new File("piadas.txt"));
    }

    public static final boolean openFile(final String texto, final File file) throws IOException {
        final FileWriter fr;
        final BufferedWriter br;
        if (!file.exists()) {
            file.createNewFile();
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
        } else {
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.newLine();
        }

        br.write(texto);
        br.close();
        fr.close();

        return true;
    }
}
