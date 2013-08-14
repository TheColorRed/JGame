package physics.Rooms;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;
import java.awt.Color;

public class Room1 extends Room{

    public Room1(){
        Sprite rect = new Sprite();
        rect.drawRect(50, 50, Color.red);

        GameObject item = new GameObject("rect", rect, this);

        this.addGameObjectAt(item, 100, 10);

        item.physics.setTime(TOP_ALIGNMENT);

    }
}
