package model;

import controller.GameManager;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        GameManager gameManager = GameManager.getInstance();
        gameManager.run();
    }
}
