package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventListener extends MouseAdapter {


    @Override
    public void mousePressed(MouseEvent e) {

        InputEvent inputEvent = new InputEvent();
        inputEvent.type = InputEvent.MOUSE_PRESSED;
        inputEvent.event = e;
        //System.out.println("Mouse Pressed at \nX " + e.getX() + " Y  " + e.getY());
        InputManager.queue.add(inputEvent);


    }

    @Override
    public void mouseMoved(MouseEvent e) {

        InputEvent inputEvent = new InputEvent();
        inputEvent.type = InputEvent.MOUSE_MOVED;
        inputEvent.event = e;
        //System.out.println("Mouse Pressed at \nX " + e.getX() + " Y  " + e.getY());
        InputManager.queue.add(inputEvent);


    }

    @Override
    public void mouseDragged(MouseEvent e) {

        InputEvent inputEvent = new InputEvent();
        inputEvent.type = InputEvent.MOUSE_DRAGGED;
        inputEvent.event = e;
        //System.out.println("Mouse Pressed at \nX " + e.getX() + " Y  " + e.getY());
        InputManager.queue.add(inputEvent);


    }
}
