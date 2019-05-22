package java2.arquivos;

import java.util.Scanner;

public class FileDemonstrationTest {

    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        final FileDemonstration application = new FileDemonstration();

        System.out.print("Entre com o diretório ou arquivo: ");
        application.analyzePath(input.nextLine());
    }
}
