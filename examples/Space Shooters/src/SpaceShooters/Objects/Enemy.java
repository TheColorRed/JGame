package SpaceShooters.Objects;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;

public class Enemy extends GameObject{

    public Enemy(Sprite sprite, Room room){
        super("enemy", sprite, room);

        collide.collidesWith("laser", new Runnable(){
            @Override
            public void run(){
                Enemy.this.destroy.destroyGameObject();
            }
        });

    }
}
