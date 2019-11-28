package model.menu;

import java.awt.*;

public class Text  {
    String text;
    Color color;
    public Font font;


    public Text(String text,Color color, Font font){
        this.color = color;
        this.font = font;
        this.text = text;
    }

    public void setText(int score){
        text = Integer.toString(score);
    }
}
