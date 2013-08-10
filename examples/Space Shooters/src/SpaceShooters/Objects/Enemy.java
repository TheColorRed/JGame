package SpaceShooters.Objects;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;

public class Enemy extends GameObject{

    public Enemy(Sprite sprite, Room room){
        super(sprite, room);

        //collide.collide("Enemy", null);

    }
}
