package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    protected ArrayList<Item> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public int getSize() {
        return items.size();
    }
}

