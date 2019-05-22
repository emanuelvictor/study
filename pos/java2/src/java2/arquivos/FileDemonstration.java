package java2.arquivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

class FileDemonstration {

    class MyFileVisitor extends SimpleFileVisitor<Path> {

        private long length = 0L;

        private int folderCount = 0;

        private int fileCount = 0;

        public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {

            if (path.toFile().isFile())
                length = length + path.toFile().length();

            fileCount++;

            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {

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

    // display information about file user specifies
    void analyzePath(final String path) {
        // create File object based on user input
        File name = new File(path);

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

        if (name.exists()) // if name exists, output information about it
        {
            // display file (or directory) information
            System.out.printf(
                    "%s%s\n%s\n%s\n%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s",
                    name.getName(), " exists",
                    (name.isFile() ? "is a file" : "is not a file"),
                    (name.isDirectory() ? "is a directory" :
                            "is not a directory"),
                    (name.isAbsolute() ? "is absolute path" :
                            "is not absolute path"), "Last modified: ",
                    name.lastModified(), "Length: ", name.length(),
                    "Path: ", name.getPath(), "Absolute path: ",
                    name.getAbsolutePath(), "Parent: ", name.getParent());

            if (name.isDirectory()) // output directory listing
            {
                final String[] directory = name.list();
                System.out.println("\n\nDirectory contents:\n");

                for (String directoryName : Objects.requireNonNull(directory))
                    System.out.printf("%s\n", directoryName);
            } // end else
        } // end outer if
        else // not file or directory, output error message
        {
            System.out.printf("%s %s", path, "does not exist.");
        } // end else
    } // end method analyzePath
} // end class arquivos.FileDemonstration
