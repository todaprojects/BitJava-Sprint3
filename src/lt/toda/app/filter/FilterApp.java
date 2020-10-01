package lt.toda.app.filter;

import lt.toda.app.Item;

import java.util.ArrayList;

public abstract class FilterApp {
    protected ArrayList<Item> items;

    public FilterApp(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> filter(String condition, String value) {
        return new ArrayList<>();
    }
}
