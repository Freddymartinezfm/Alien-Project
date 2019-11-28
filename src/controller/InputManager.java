package controller;

import model.bullet.Bullet;
import model.spaceship.BaseShip;
import model.level.Target;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import static controller.GameManager.gameData;

public class InputManager {
    public static LinkedList<InputEvent> queue = new LinkedList<>();

    public void processInputEvents(){

        while (!queue.isEmpty()){
            InputEvent inputEvent = queue.removeFirst();
            MouseEvent me;
            switch (inputEvent.type){
                case InputEvent.KEY_PRESSED:
                    BaseShip baseShipPlayer = (BaseShip) gameData.fixedObject.get(GameManager.SPACESHIP_INDEX);
                    /*
                    * Testing enemy object
                    *
                    * */


                    //Bee bee = (Bee) gameData.enemyObject.get(GameManager.ENEMY_INDEX);

                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()){
                        case KeyEvent.VK_UP:
                            //spaceShipPlayer.getBoundingBox().x += 5;
                            baseShipPlayer.setVelocityY(-BaseShip.UNIT_MOVE);
                            break;
                        case KeyEvent.VK_W:
                            //bee.location.y-= 5;
                            break;
                        case KeyEvent.VK_DOWN:
                            baseShipPlayer.setVelocityY(BaseShip.UNIT_MOVE);
                            break;
                        case KeyEvent.VK_S:
                            //bee.location.y += 5;
                            break;
                        case KeyEvent.VK_LEFT:
                            baseShipPlayer.setVelocityX(-BaseShip.UNIT_MOVE);
                            break;
                        case KeyEvent.VK_A:
                            //bee.location.x -= 5;
                        case KeyEvent.VK_RIGHT:
                            baseShipPlayer.setVelocityX(BaseShip.UNIT_MOVE);
                            break;
                        case KeyEvent.VK_D:
                            //bee.location.x += 5;
                            break;
                        case KeyEvent.VK_SPACE:
                            Bullet bullet = new Bullet(gameData.fixedObject.get(GameManager.TARGET_INDEX).location.x,
                                    gameData.fixedObject.get(GameManager.TARGET_INDEX).location.y);
                            gameData.friendObject.add(bullet);
                            break;
                        case KeyEvent.VK_ESCAPE:
                            System.exit(1);
                            break;
                        case KeyEvent.VK_I:

                            break;
                    }
                    break;
                case InputEvent.KEY_RELEASED:
                    baseShipPlayer = (BaseShip) gameData.fixedObject.get(GameManager.SPACESHIP_INDEX);


                    ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()){
                        case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                            baseShipPlayer.setVelocityX(0);
                            break;
                        case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                            baseShipPlayer.setVelocityY(0);
                            break;
                        case KeyEvent.VK_A:

                            break;
                        case KeyEvent.VK_LEFT:
                            baseShipPlayer.setVelocityX(0);
                            break;
                        case KeyEvent.VK_UP: case KeyEvent.VK_W:
                            baseShipPlayer.setVelocityY(0);
                            break;
                }
                    break;
                case InputEvent.MOUSE_PRESSED:
                    me = (MouseEvent) inputEvent.event;
                    Bullet bullet = new Bullet(me.getX(), me.getY());
                    gameData.friendObject.add(bullet);
                    break;
                case InputEvent.MOUSE_MOVED:
                    var target = (Target) gameData.fixedObject.get(GameManager.TARGET_INDEX);
                    me = (MouseEvent) inputEvent.event;
                    target.setLocationX(Math.abs(me.getX()));
                    target.setLocationY(Math.abs(me.getY()));
                    break;

            }
        }
    }

    public void processStartMenuEvents(){
        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();
            switch (inputEvent.type) {
                case InputEvent.KEY_PRESSED:
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_SPACE: case KeyEvent.VK_ENTER:
                            GameManager.running = true;
                    }
            }
        }
    }
}
