package model.level;

public class AdvanceLevel implements Observer {

    @Override
    public void eventRecieved() {
        System.out.println("Observer called");
    }
}
