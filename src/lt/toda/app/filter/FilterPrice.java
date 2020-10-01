package lt.toda.app.filter;

import lt.toda.app.Item;

import java.util.ArrayList;

public class FilterPrice extends FilterApp {

    public FilterPrice(ArrayList<Item> items) {
        super(items);
    }

    @Override
    public ArrayList<Item> filter(String condition, String value) {
        ArrayList<Item> itemsFiltered = new ArrayList<>();
        double valueInt = Double.parseDouble(value.replace(',', '.'));
        if ("smaller".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getPrice() < valueInt) {
                itemsFiltered.add(item);
            }
        }
        else if ("equals".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getPrice() == valueInt) {
                itemsFiltered.add(item);
            }
        }
        else if ("larger".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getPrice() > valueInt) {
                itemsFiltered.add(item);
            }
        }
        return itemsFiltered;
    }
}
