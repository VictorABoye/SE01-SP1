package dk.sdu.mmmi.t3.g1;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class NonFoodItem extends Item {

    private String material;

    public NonFoodItem(ImageView image, String name, String material) {
        super(image, name);
        this.material = material;
    }

    public String getMaterial(){
        return material;
    }

    /*
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

     */
}
