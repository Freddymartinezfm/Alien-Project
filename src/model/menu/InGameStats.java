package model.menu;

import controller.GameManager;

import java.awt.*;

public class InGameStats extends HUDItems {

    public InGameStats(String fontName)  {
        super(fontName);
    }

    @Override
    public void render(Graphics2D g) {
        g.setStroke(new BasicStroke(1.0f));
        //g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(GAME_RED);
        g.setFont(scoreText.font);
        g.drawString(scoreText.text, (float) location.x + (float) GameManager.win.getWidth() * .05f, location.y + (float)GameManager.win.getHeight() * .05f);

        g.setColor(GAME_RED);
        g.setFont(hiScoreText.font);
        g.drawString(hiScoreText.text, (float) location.x + (float) GameManager.win.getWidth() * .60f, location.y + (float)GameManager.win.getHeight() * .05f);
        g.drawString(timeLbl.text, location.x + (float)GameManager.win.getWidth() * .30f, location.y + (float)GameManager.win.getHeight() * .05f);

        g.setColor(GAME_WHITE);
        g.setFont(hiScoreLbl.font);
        g.setFont(timeText.font);
        g.drawString(scoreLbl.text, (float) location.x + (float)GameManager.win.getWidth() * .05f, location.y + (float)GameManager.win.getHeight() * .10f);
        g.drawString(timeText.text, location.x + (float)GameManager.win.getWidth() * .30f, location.y + (float)GameManager.win.getHeight() * .10f);
        g.drawString(hiScoreLbl.text, location.x + (float)GameManager.win.getWidth() * .60f, location.y + (float)GameManager.win.getHeight() * .10f);


    }

    public void setScore(int s){
        scoreLbl.setText(s);

    }

}
