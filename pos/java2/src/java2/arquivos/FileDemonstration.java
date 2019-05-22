package java2.arquivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

class FileDemonstration {

    class MyFileVisitor extends SimpleFileVisitor<Path> {

        private long length = 0L;

        private int folderCount = 0;

        private int fileCount = 0;

        public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {

            length = length + path.toFile().length();

            fileCount++;

            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {

            length = length + path.toFile().length();

            folderCount++;

            return FileVisitResult.CONTINUE;
        }

        long getLength() {
            return length;
        }

        int getFolderCount() {
            return folderCount;
        }

        int getFileCount() {
            return fileCount;
        }
    }

    void analyzePath(final String path) {
        final Path source = Paths.get(path);
        try {
            final MyFileVisitor visitor = new MyFileVisitor();
            Files.walkFileTree(source, visitor);
            System.out.printf("%s%s\n", "Tamanho total: ", (visitor.getLength() / 1024L / 1024L) + " MB");
            System.out.printf("%s%s\n", "Quantidade de pastas contidas: ", (visitor.getFolderCount()));
            System.out.printf("%s%s\n", "Quantidade de arquivos contidos: ", (visitor.getFileCount()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
