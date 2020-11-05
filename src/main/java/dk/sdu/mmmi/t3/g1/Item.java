package dk.sdu.mmmi.t3.g1;

public abstract class Item extends Inventory implements isExpired{
    private String name;
    private String type;

    public Item(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
}
