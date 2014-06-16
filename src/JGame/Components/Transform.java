package JGame.Components;

import JGame.Behavior;
import JGame.Util.Vector2;

public class Transform extends Behavior{

    protected double x = 0, y = 0;

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 getPosition(){
        return new Vector2(this.x, this.y);
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }
}
