package view;

import controller.GameManager;

import javax.swing.*;
import java.awt.*;

public class MyCanvas extends JPanel {
    private int width;
    private int height;

    public static boolean initialized;

    private static MyCanvas instance;

    public static MyCanvas getInstance(){
        if (instance == null){
            instance = new MyCanvas();
        }
        return instance;
    }


    public  void render(){
        width = getSize().width;
//        System.out.println(width);
        height = getSize().height;
//        setSize(new Dimension(500, 1200));
//        System.out.println(height);

        // off-screen double buffer image, for CPU to create the things to put on display
        Image doubleBufferImage = createImage(width, height);
        if (doubleBufferImage == null) {
            System.err.println("Critical Error: doubleBufferImage is null");
            initialized = false;
            System.exit(1);
        }

        // off-screen rendering
        Graphics2D g2OffScreen = (Graphics2D) doubleBufferImage.getGraphics();
        if (g2OffScreen == null) {
            System.err.println("Critical Error: g2OffScreen is null");
            initialized = false;
            System.exit(1);
        }

        // initialize the image buffer
        g2OffScreen.setBackground(Color.BLACK);
        g2OffScreen.clearRect(0,0, width, height);


        //use active rendering to put the buffer image on screen
        Graphics gOnScreen ;

        /*
        render all game data below here here!!!
         */

        for (var fig : GameManager.gameData.fixedObject){
//            g2OffScreen.translate()
            fig.render(g2OffScreen);
        }
        for (var fig : GameManager.gameData.friendObject){
            fig.render(g2OffScreen);
        }

        for (var fig : GameManager.gameData.enemyObject){
            fig.render(g2OffScreen);
        }



        /*And above here

         */
        gOnScreen = this.getGraphics();
        if (gOnScreen != null) {
            // copy offScreen image to onScreen
            initialized = true;
            gOnScreen.drawImage(doubleBufferImage, 0,0,null);
        }

        // to synchronize the display on some systems
        Toolkit.getDefaultToolkit().sync();

        if (gOnScreen != null) {

            gOnScreen.dispose();
        }


    }
}
