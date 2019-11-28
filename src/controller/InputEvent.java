package controller;

import java.util.EventObject;

public class InputEvent {
    public static final int KEY_PRESSED = 0;
    public static final int KEY_RELEASED = 1;
    public static final int MOUSE_PRESSED = 2;
    public static final int MOUSE_MOVED = 3;
    public static final int MOUSE_DRAGGED = 4;

    public static final int QUIT_GAME = 2;



    public int  type;
    public EventObject event;




}
