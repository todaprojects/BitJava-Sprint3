package lt.toda.app.util;

import lt.toda.app.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Writer {
    private final File file;

    public Writer(File file) {
        this.file = file;
    }

    public void write(ArrayList<Item> items) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            if (items.size() > 0) {
                for (Item item : items) {
                    bufferedWriter.write(item.toFile());
                }
            }
        } catch (Exception e) {
            System.out.printf("\terror: data file \"%s\" not found\n", file.getName());
        }
    }
}
