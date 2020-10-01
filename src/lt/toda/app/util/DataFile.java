package lt.toda.app.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DataFile {
    Scanner scanner = new Scanner(System.in);

    private final String path;

    public DataFile(String path) {
        this.path = path;
    }

    public File get() {
        String pathToFile;
        while (true) {
            System.out.print("\nEnter file name to upload: ");
            String fileName = scanner.nextLine();
            if (!fileName.isEmpty()) {
                pathToFile = path + fileName;
                try {
                    if (Files.exists(Path.of(pathToFile))) {
                        break;
                    } else {
                        throw new FileNotFoundException();
                    }
                } catch (FileNotFoundException e) {
                    System.out.printf("\tfile not found; create new file \"%s\"? (yes / no): ", fileName);
                    while (true) {
                        String command = scanner.nextLine();
                        if ("yes".toLowerCase().equals(command)) {
                            try {
                                new File(pathToFile).createNewFile();
                            } catch (IOException i) {
                                System.out.println("\terror: file has not been created");
                            }
                            return new File(pathToFile);
                        } else if ("no".toLowerCase().equals(command)) {
                            break;
                        } else {
                            System.out.println("\tunknown command");
                            break;
                        }
                    }
                }
            }
        }
        return new File(pathToFile);
    }
}
