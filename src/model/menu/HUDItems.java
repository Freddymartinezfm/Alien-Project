package model.menu;

import controller.GameManager;
import model.GameFigure;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.sql.Time;

public class HUDItems extends GameFigure {
    protected static final int FONT_SIZE = 18;
    protected static final int BEGIN = 0;
    protected static final int IN_FORMATION = 1;


    protected int state;
    protected Text scoreLbl;
    protected Text scoreText;
    protected Text oneUp;
    protected Text timeLbl;
    protected Text timeText;
    protected Text startLbl;

    protected Text hiScoreLbl;
    protected Text hiScoreText;
    protected String fontName;
    protected int fontSize;
    protected int scoreValue ;
    protected int timeValue ;
    BufferedImage levels[];

    protected Point2D.Float endPosition = new Point2D.Float(0.0f, 0.0f);
    protected Font font;

    public static final Color GAME_RED = new Color((int)82.7, (int)32.9, (int)25.5);
    public static final Color GAME_WHITE = new Color((int)96.5, (int)96.5, (int)96.5);
    public static final Color GAME_BLUE = new Color((int)31.5, (int)31.0, (int)91);
    public static final Color GAME_CYAN = new Color((int)24.7, (int)91.8, (int)95.7);

    public HUDItems(String fontName) {
        super((float) 0.0, GameManager.win.getHeight());
        this.fontName = fontName;
        font = new Font(fontName, Font.BOLD,20);
        fontSize = FONT_SIZE;
        hiScoreLbl = new Text("0000", GAME_WHITE, new Font(fontName, Font.BOLD,16));
        scoreLbl = new Text("000", GAME_WHITE, new Font(fontName, Font.BOLD,16));
        startLbl = new Text("PRESS SPACE KEY", GAME_CYAN, new Font(fontName, Font.BOLD,32));
        scoreText = new Text("SCORE", GAME_RED, new Font(fontName, Font.BOLD,fontSize));
        hiScoreText = new Text("HI-SCORE", GAME_RED, new Font(fontName, Font.BOLD,fontSize));
        oneUp = new Text("1-UP", GAME_RED, new Font(fontName, Font.BOLD,fontSize));
        timeLbl = new Text("TIME", GAME_RED, new Font(fontName, Font.BOLD,fontSize));
        timeText = new Text("000", GAME_WHITE, new Font(fontName, Font.BOLD,16));
        velocity.y = -20f;
        state = BEGIN;
        timeValue = 0;



    }

    public void begin(){
        location.y += velocity.y ;
        scoreValue += 1;
        scoreLbl.setText(0);

        double dist = endPosition.distance(location);
        System.out.println(dist);
        if (dist <= 5.0f){
            setVelocityY(0.0f);
            state = IN_FORMATION;
        } else {
            state = BEGIN;
        }




    }


    public void setState(int state){
        this.state = state;
    }

    public void updateStates(){
        switch (state){
            case BEGIN:
                begin();
                break;
            case IN_FORMATION:
                formationState();
                break;
            default:
                throw new IllegalStateException("Unexpected state: " + state);
        }
    }

    public void formationState() {
        timeText.setText((int) timeValue++/20);
        setVelocityY(0f);
        setVelocityX(0f);
    }


    @Override
    public void update() {
        updateStates();
    }


    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public double getCollisionRadius() {
        // not needed for startScreen
        return 0;
    }

}
