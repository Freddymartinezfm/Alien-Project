package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.type = InputEvent.KEY_PRESSED;
        inputEvent.event = e;
        InputManager.queue.add(inputEvent);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.type = InputEvent.KEY_RELEASED;
        inputEvent.event = e;
        InputManager.queue.add(inputEvent);
    }
}
