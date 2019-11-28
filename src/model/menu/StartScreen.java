package model.menu;

import controller.GameManager;

import java.awt.*;
import java.io.IOException;

public class StartScreen extends HUDItems {

    public StartScreen(String fontName) {
        super(fontName);
        location.y = GameManager.win.getHeight() - 400;
    }

    @Override
    public void render(Graphics2D g) {
        g.setStroke(new BasicStroke(1.5f));
        g.setColor(GAME_RED);
        g.setColor(startLbl.color);
        g.setFont(startLbl.font);
        g.drawString(startLbl.text,
                location.x + (float)GameManager.win.canvas.getWidth() * .05f,
                location.y + (float)GameManager.win.canvas.getHeight() * .50f);
        g.setColor(HUDItems.GAME_RED);
        g.drawString(oneUp.text, location.x + (float)GameManager.win.canvas.getWidth() * .05f,
                location.y + (float)GameManager.win.canvas.getHeight() * .10f);

    }
}
