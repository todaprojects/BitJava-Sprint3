package lt.toda.app;

import lt.toda.app.filter.FilterApp;
import lt.toda.app.filter.FilterName;
import lt.toda.app.filter.FilterPrice;
import lt.toda.app.filter.FilterQuantity;
import lt.toda.app.util.DataFile;
import lt.toda.app.util.Reader;
import lt.toda.app.util.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static boolean exitApp;
    static Scanner scanner = new Scanner(System.in);

    static String path = "./data/";

    public static void main(String[] args) {
        while (!exitApp) {
            String[] pathNames;
            File f = new File(path);
            pathNames = f.list();
            if (pathNames != null) {
                System.out.println("\nFiles stored in a directory:");
                for (String pathname : pathNames) {
                    System.out.printf("\t%s\n", pathname);
                }
            }
            runMenu();
        }
    }

    public static void runMenu() {
        File file = new DataFile(path).get();
        ArrayList<Item> items = new ArrayList<>(new Reader(file).getData());
        System.out.printf("\tuploaded file: \"%s\"\n", file.getName());
        while (true) {
            try {
                System.out.print("\nWrite command (print / add / edit / delete / filter / sort / file / exit): ");
                String command = scanner.nextLine();
                switch (command.toLowerCase()) {
                    case "print": {
                        print(items);
                        break;
                    }
                    case "add": {
                        Item item = new Item();
                        String string;

                        while (true) {
                            System.out.print("\tenter name: ");
                            string = scanner.nextLine();
                            if (string.length() > 0) {
                                item.setName(string);
                                break;
                            }
                        }

                        while (true) {
                            System.out.print("\tenter price: ");
                            string = scanner.nextLine();
                            if (string.length() > 0) {
                                item.setPrice(Double.parseDouble(string.replace(",", ".")));
                                break;
                            }
                        }

                        while (true) {
                            System.out.print("\tenter quantity: ");
                            string = scanner.nextLine();
                            if (!string.isEmpty()) {
                                item.setQuantity(Integer.parseInt(string));
                                break;
                            }
                        }

                        items.add(item);
                        System.out.println("\tnew item added!");
                        break;
                    }
                    case "edit": {
                        if (!isEmpty(items)) {
                            String string;
                            System.out.printf("\twrite item \"No.\" to edit (1 - %d): ", (items.size()));
                            int index = Integer.parseInt(scanner.nextLine());
                            if (index >= 1 && index <= (items.size())) {
                                Item item = items.get(index - 1);

                                System.out.printf("\t -> enter new 'name' or press 'Enter' to leave \"%s\": ", item.getName());
                                string = scanner.nextLine();
                                if (!string.isEmpty())
                                    item.setName(string);

                                System.out.printf("\t -> enter new 'price' or press 'Enter' to leave \"%.2f\": ", item.getPrice());
                                string = scanner.nextLine();
                                if (!string.isEmpty())
                                    item.setPrice(Double.parseDouble(string.replace(",", ".")));

                                System.out.printf("\t -> enter new 'quantity' or press 'Enter' to leave \"%d\": ", item.getQuantity());
                                string = scanner.nextLine();
                                if (!string.isEmpty())
                                    item.setQuantity(Integer.parseInt(string));

                                System.out.println("\titem stored!");
                            } else {
                                System.out.print("\tincorrect number\n");
                            }
                        } else {
                            throw new NoSuchElementException();
                        }
                        break;
                    }
                    case "delete": {
                        if (!isEmpty(items)) {
                            System.out.printf("\twrite item \"No.\" to delete (1 - %d): ", (items.size()));
                            int index = Integer.parseInt(scanner.nextLine());
                            if (index >= 1 && index <= (items.size())) {
                                System.out.printf("\titem \"No. %d\" deleted!\n", index);
                                items.remove(index - 1);
                            } else {
                                System.out.print("\tincorrect number\n");
                            }
                        } else {
                            throw new NoSuchElementException();
                        }
                        break;
                    }
                    case "filter": {
                        if (!isEmpty(items)) {
                            System.out.print("\twrite filter attribute (name / price / quantity): ");
                            String attribute = scanner.nextLine();
                            switch (attribute.toLowerCase()) {
                                case "name": {
                                    System.out.print("\twrite filter condition (contains / equals / excludes): ");
                                    String condition = scanner.nextLine();
                                    switch (condition.toLowerCase()) {
                                        case "contains":
                                        case "equals":
                                        case "excludes": {
                                            System.out.print("\twrite filter value: ");
                                            String value = scanner.nextLine();

                                            FilterApp filterApp = new FilterName(items);
                                            print(filterApp.filter(condition, value));
                                            break;
                                        }
                                        default: {
                                            throw new IllegalArgumentException();
                                        }
                                    }
                                    break;
                                }
                                case "price": {
                                    System.out.print("\twrite filter condition (smaller / equals / larger): ");
                                    String condition = scanner.nextLine();
                                    switch (condition.toLowerCase()) {
                                        case "smaller":
                                        case "equals":
                                        case "larger": {
                                            System.out.print("\twrite filter value: ");
                                            String value = scanner.nextLine();

                                            FilterApp filterApp = new FilterPrice(items);
                                            print(filterApp.filter(condition, value));
                                            break;
                                        }
                                        default: {
                                            throw new IllegalArgumentException();
                                        }
                                    }
                                    break;
                                }
                                case "quantity": {
                                    System.out.print("\twrite filter condition (smaller / equals / larger): ");
                                    String condition = scanner.nextLine();
                                    switch (condition.toLowerCase()) {
                                        case "smaller":
                                        case "equals":
                                        case "larger": {
                                            System.out.print("\twrite filter value: ");
                                            String value = scanner.nextLine();

                                            FilterApp filterApp = new FilterQuantity(items);
                                            print(filterApp.filter(condition, value));
                                            break;
                                        }
                                        default: {
                                            throw new IllegalArgumentException();
                                        }
                                    }
                                    break;
                                }
                                default: {
                                    throw new IllegalArgumentException();
                                }
                            }
                            break;
                        }
                    }
                    case "sort": {
                        if (!isEmpty(items)) {
                            System.out.print("\twrite sorting attribute (name / price / quantity): ");
                            String attribute = scanner.nextLine();
                            items.sort(new Comparator<>() {
                                @Override
                                public int compare(Item i1, Item i2) {
                                    if ("name".equalsIgnoreCase(attribute)) {
                                        int compare = i1.getName().compareTo(i2.getName());
                                        if (compare == 0) compare = Double.compare(i1.getPrice(), i2.getPrice());
                                        if (compare == 0) compare = Integer.compare(i1.getQuantity(), i2.getQuantity());
                                        return compare;
                                    } else if ("price".equalsIgnoreCase(attribute)) {
                                        int compare = Double.compare(i1.getPrice(), i2.getPrice());
                                        if (compare == 0) i1.getName().compareTo(i2.getName());
                                        if (compare == 0) compare = Integer.compare(i1.getQuantity(), i2.getQuantity());
                                        return compare;
                                    } else if ("quantity".equalsIgnoreCase(attribute)) {
                                        int compare = Integer.compare(i1.getQuantity(), i2.getQuantity());
                                        if (compare == 0) i1.getName().compareTo(i2.getName());
                                        if (compare == 0) compare = Double.compare(i1.getPrice(), i2.getPrice());
                                        return compare;
                                    } else {
                                        throw new IllegalArgumentException();
                                    }
                                }
                            });
                            print(items);
                        } else {
                            throw new NoSuchElementException();
                        }
                        break;
                    }
                    case "file": {
                        if (!isEmpty(items)) new Writer(file).write(items);
                        return;
                    }
                    case "exit": {
                        Main.exitApp = true;
                        System.out.printf("All data saved to \"%s\"!\n", file.getName());
                        if (!isEmpty(items)) new Writer(file).write(items);
                        return;
                    }
                    default: {
                        throw new IllegalArgumentException();
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("unknown command");
            } catch (NoSuchElementException n) {
                System.out.println("\tdata not found");
            }
        }

    }

    static boolean isEmpty(ArrayList<Item> items) {
        return items.size() == 0;
    }

    static void print(ArrayList<Item> items) {
        if (!isEmpty(items)) {
            int number = 0;
            System.out.printf("\n %-7s | %-21s | %-21s | %-20s \n", "\"No.\"", "\"Item\"", "\"Price\"", "\"Quantity\"");
            for (Item item : items) {
                System.out.printf("  %-6d |", ++number);
                System.out.println(item);
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
