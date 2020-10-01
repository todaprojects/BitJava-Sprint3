package lt.toda.app.filter;

import lt.toda.app.Item;

import java.util.ArrayList;

public class FilterQuantity extends FilterApp {

    public FilterQuantity(ArrayList<Item> items) {
        super(items);
    }

    @Override
    public ArrayList<Item> filter(String condition, String value) {
        ArrayList<Item> itemsFiltered = new ArrayList<>();
        int valueInt = Integer.parseInt(value);
        if ("smaller".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getQuantity() < valueInt) {
                itemsFiltered.add(item);
            }
        }
        else if ("equals".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getQuantity() == valueInt) {
                itemsFiltered.add(item);
            }
        }
        else if ("larger".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getQuantity() > valueInt) {
                itemsFiltered.add(item);
            }
        }
        return itemsFiltered;
    }
}
