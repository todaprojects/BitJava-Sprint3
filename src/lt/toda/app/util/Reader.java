package lt.toda.app.util;

import lt.toda.app.Item;

import java.io.*;
import java.util.ArrayList;

public class Reader {
    private final File file;

    public Reader(File file) {
        this.file = file;
    }

    public ArrayList<Item> getData() {
        ArrayList<Item> items = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String fileLine = bufferedReader.readLine();
            while (fileLine != null) {
                String[] line = fileLine.split("\\|");
                items.add(new Item(line[0],
                        Double.parseDouble(line[1].replace(',','.')),
                        Integer.parseInt(line[2])));
                fileLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.printf("\terror: file \"%s\" not found\n", file.getName());
        }
        return items;
    }
}
