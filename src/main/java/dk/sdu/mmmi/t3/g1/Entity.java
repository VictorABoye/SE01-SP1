package dk.sdu.mmmi.t3.g1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {
    protected String type;
    protected ImageView image;

    protected double x,y,h,w;

    public Entity(ImageView image){
        this.image = image;
        type = "entity";
        x = image.getLayoutX();
        y = image.getLayoutY();
        h = image.getFitHeight();
        w = image.getFitWidth();
    }

    public double getH() {
        return h;
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Image getImage() {
        return image.getImage();
    }

    public ImageView getImageView(){
        return image;
    }

    public String getType() {
        return type;
    }
}
