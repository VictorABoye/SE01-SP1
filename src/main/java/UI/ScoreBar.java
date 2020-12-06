package UI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import worldofzuul.Game;

public class ScoreBar {

    public static Rectangle[] createScoreBar(){
        Rectangle[] scoreSet = new Rectangle[2];
        Rectangle scoreBar = new Rectangle();
        scoreBar.setId("scoreBar");
        scoreBar.setLayoutX(40);
        scoreBar.setLayoutY(680);
        scoreBar.setHeight(20);
        double score = Game.getWorldPlayer().getClimateScore();
        scoreBar.setWidth(score/100.0*1200);
        scoreBar.setFill(ScoreBar.getColor(score));
        Rectangle scoreBackGround = new Rectangle();
        scoreBackGround.setId("scoreBackGround");
        scoreBackGround.setLayoutX(40);
        scoreBackGround.setLayoutY(680);
        scoreBackGround.setHeight(20);
        scoreBackGround.setWidth(1200);
        scoreSet[0] = scoreBackGround;
        scoreSet[1] = scoreBar;
        return scoreSet;
    }

    public static Color getColor(double value){
        if (value < 10) return Color.DARKRED;
        else if (value < 20) return (Color.RED);
        else if (value < 30) return (Color.DARKORANGE);
        else if (value < 40) return (Color.ORANGE);
        else if (value < 50) return (Color.YELLOW);
        else if (value < 60) return (Color.YELLOWGREEN);
        else if (value < 70) return (Color.DARKGREEN);
        else if (value < 80) return (Color.GREEN);
        else if (value < 90) return (Color.LIGHTGREEN);
        else if (value < 100) return (Color.CYAN);
        else if (value == 100) return (Color.BLUE);
        else return Color.BLACK;
    }
}
