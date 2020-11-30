package dk.sdu.mmmi.t3.g1;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class NonFoodItem extends Item {

    public NonFoodItem(ImageView image, String type, String name, String url) {
        super(image, type, name, url);
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
