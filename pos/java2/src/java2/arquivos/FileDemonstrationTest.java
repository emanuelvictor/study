package java2.arquivos;

import java.util.Scanner;

public class FileDemonstrationTest {

    public static void main(final String args[]) {
        final Scanner input = new Scanner(System.in);
        final FileDemonstration application = new FileDemonstration();

        System.out.print("Enter file or directory name here: ");
        application.analyzePath(input.nextLine());
    }
}
