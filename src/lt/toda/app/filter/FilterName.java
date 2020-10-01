package lt.toda.app.filter;

import lt.toda.app.Item;

import java.util.ArrayList;

public class FilterName extends FilterApp {

    public FilterName(ArrayList<Item> items) {
        super(items);
    }

    @Override
    public ArrayList<Item> filter(String condition, String value) {
        ArrayList<Item> itemsFiltered = new ArrayList<>();
        if ("contains".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getName().toLowerCase().contains(value.toLowerCase())) {
                itemsFiltered.add(item);
            }
        }
        else if ("equals".equalsIgnoreCase(condition)) for (Item item : items) {
            if (item.getName().equalsIgnoreCase(value)) {
                itemsFiltered.add(item);
            }
        }
        else if ("excludes".equalsIgnoreCase(condition)) for (Item item : items) {
            if (!item.getName().toLowerCase().contains(value.toLowerCase())) {
                itemsFiltered.add(item);
            }
        }
        return itemsFiltered;
    }
}
