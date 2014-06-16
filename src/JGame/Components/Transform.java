package JGame.Components;

import JGame.Behavior;
import JGame.Util.Vector2;

public class Transform extends Behavior{

    public Vector2 position = new Vector2(0, 0);

    public void translate(double x, double y){
        position = new Vector2(position.getX() + x, position.getY() + x);
    }

    public void rotate(double rotation){

    }

}
