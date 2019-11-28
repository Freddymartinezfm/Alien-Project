package view;

import controller.KeyInputListener;
import controller.GameManager;
import controller.MouseEventListener;

import javax.swing.*;
import java.awt.*;

import static controller.GameManager.win;

public class MainWindow extends JFrame {
    public MyCanvas canvas;
    //private JButton startButton;
    private static MainWindow instance;

    public  void init() {
        setLocation(300, 200);
        setTitle("Game Engine");
        canvas = new MyCanvas(); // creates a new screen

        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);
        canvas.setFocusable(true);
        canvas.addKeyListener(new KeyInputListener());

        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseMotionListener(listener);
        canvas.addMouseListener(listener);


        //startButton = new JButton("Start");
        //startButton.setFocusable(false);

        //JPanel buttonPanel = new JPanel();
        //buttonPanel.add(startButton);
        //cp.add(BorderLayout.SOUTH, buttonPanel);

//        startButton.addActionListener((e)-> {
//            if (!GameManager.running) {
//                GameManager.running = true;
//                startButton.setText("Quit");
//            } else {
//                System.exit(0);
//            }
//
//            System.out.println("Button pressed using Lambda");
//            GameManager.running = true;
//        });
    }


    public static  MainWindow getInstance(){
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }
}
