package dk.sdu.mmmi.t3.g1;


import javafx.scene.image.ImageView;

public class Player extends Entity implements Movement{

    private final double speed = 25;

    public Player(ImageView image){
        super(image);
        type = "player";
    }

    @Override
    public void moveUp(){
        image.setLayoutY(y-speed);
    }

    @Override
    public void moveDown(){
        image.setLayoutY(y+speed);
    }

    @Override
    public void moveRight(){
        image.setLayoutX(x-speed);
    }

    @Override
    public void moveLeft(){
        image.setLayoutX(x+speed);
    }

}
