package JGame.Actions;

import JGame.GameObject.GameObject;
import JGame.Room.Room;

public class MoveAction extends Action{

    protected GameObject obj;
    protected int endX = 0, endY = 0;
    protected int moveAmount = 0;
    protected Thread thread;
    protected Room room;
    protected String moveDirection = "";

    public MoveAction(Room room, GameObject obj){
        this.room = room;
        this.obj = obj;
    }

    public void x(int amount){
        obj.setX(obj.getX() + amount);
    }

    public void y(int amount){
        obj.setY(obj.getY() + amount);
    }

    public void moveToY(int y, int speed){
        this.complete = new Runnable(){
            @Override
            public void run(){
            }
        };
        this._moveToY(y, speed, complete);
    }

    public void moveToY(int y, double time){

    }

    public void moveToY(int y, int speed, Runnable complete){
        this._moveToY(y, speed, complete);
    }

    public void moveToX(int x, int speed){
        this.complete = new Runnable(){
            @Override
            public void run(){
            }
        };
        this._moveToX(x, speed, complete);
    }

    public void moveToX(int x, int speed, Runnable complete){
        this._moveToX(x, speed, complete);
    }

    protected void _moveToX(int x, int speed, Runnable complete){
        this.obj.moveEndX = x;
        this.obj.moveAmountX = speed;
        this.obj.needsToMoveX = true;
        if(this.obj.getX() > x){
            this.moveDirection = "LEFT";
        }
        if(this.obj.getX() < x){
            this.moveDirection = "RIGHT";
        }
    }

    protected void _moveToY(int y, int speed, Runnable complete){
        this.obj.moveEndY = y;
        this.obj.moveAmountY = speed;
        this.obj.needsToMoveY = true;
        this.complete = complete;
        if(this.obj.getY() > y){
            this.moveDirection = "UP";
        }
        if(this.obj.getY() < y){
            this.moveDirection = "DOWN";
        }
    }

    public void jumpTo(int x, int y, Runnable complete){
    }

    public void jumpTo(int x, int y){
        this.obj.setX(x);
        this.obj.setY(y);
    }

    public String getMoveDirection(){
        return this.moveDirection;
    }
}
