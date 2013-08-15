package physics.Rooms;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;
import JGame.Util.TimeRegulator;
import java.awt.Color;

public class Room1 extends Room{

    protected final GameObject item;
    final TimeRegulator tr = new TimeRegulator(0);

    public Room1(){
        Sprite rect = new Sprite();
        rect.drawRect(50, 50, Color.red);

        item = new GameObject("rect", rect, this);

        this.addGameObjectAt(item, 100, 10);
        item.physics.setSeconds(0);
        item.setMass(50);
        item.physics.setForce(1000);
        this.drop();
    }

    public void drop(){
        item.iteration.set("drop", new Runnable(){
            @Override
            public void run(){
                item.physics.setSeconds(tr.secondsPassed());
                double dist = item.physics.getDistance();
                double force = item.physics.getHorizontalDistance();
                if(item.getBottomY() < 500){
                    item.move.jumpTo((int)force, (int)dist);
                }else{
                    item.iteration.remove("drop");
                    bounce();
                }
            }
        });
    }

    public void bounce(){
        item.iteration.set("bounce", new Runnable(){
            @Override
            public void run(){
                item.physics.setSeconds(tr.secondsPassed());
                //mgh=1/2 m v^2
                //vy = 0 + 1/2 g t2
                //float mgh = (float)(0.5 * item.getMass() * Math.pow(item.physics.getVelocity(), 2));
                double vy = item.physics.getVY();
                System.out.println(vy);
                item.move.moveToY((int)vy, (int)(item.physics.getSpeed() * 0.5));
                item.iteration.remove("bounce");
            }
        });
    }
}
