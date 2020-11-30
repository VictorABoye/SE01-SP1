package dk.sdu.mmmi.t3.g1;

public class WorldPlayer {

    private Inventory inventory;

    public WorldPlayer()
    {
        inventory = new Inventory();
    }

    public Item getItemByName(String name)
    {
        return inventory.getItem(name);
    }

    public Item getItemByIndex(int index)
    {
        return inventory.getItem(index);
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void addItem(Item item)
    {
        inventory.addItem(item);
    }
}
