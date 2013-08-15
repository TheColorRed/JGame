package physics.Rooms;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;
import JGame.Util.TimeRegulator;
import java.awt.Color;

public class Room1 extends Room{

    protected final GameObject item;

    public Room1(){
        Sprite rect = new Sprite();
        rect.drawRect(50, 50, Color.red);

        item = new GameObject("rect", rect, this);

        this.addGameObjectAt(item, 100, 10);
        item.physics.setTime(0);
        item.setMass(50);
        this.drop();
    }

    public void drop(){
        final TimeRegulator tr = new TimeRegulator(0);
        item.iteration.set("drop", new Runnable(){
            @Override
            public void run(){
                item.physics.setTime(tr.secondsPassed());
                double dist = item.physics.getDistance();
                if(dist <= 500){
                    item.move.jumpTo(100, (int)dist);
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
                //mgh=1/2 m v^2
                float mgh = (float)(0.5 * item.getMass() * Math.pow(item.physics.getVelocity(), 2));
                System.out.println(mgh);
            }
        });
    }
}
