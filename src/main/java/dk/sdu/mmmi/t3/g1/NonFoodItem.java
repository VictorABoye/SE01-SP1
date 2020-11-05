package dk.sdu.mmmi.t3.g1;

public class NonFoodItem extends Item {

    public NonFoodItem(String name, String type) {
        super(name, type);
    }
    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void removeExpired() {

    }

    @Override
    public void checkExpired(){

    }
}
