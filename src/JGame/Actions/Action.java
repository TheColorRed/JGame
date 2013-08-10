package JGame.Actions;

public class Action{

    protected boolean actionComplete = false;
    protected Runnable complete;

    public void actionComplete(){
        complete.run();
    }
}
