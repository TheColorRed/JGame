package SpaceShooters.Objects;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;
import JGame.Util.TimeRegulator;

public class Ship extends GameObject{

    protected int shipSpeed = 10;
    protected long lastFired = 0;

    public Ship(Sprite sprite, Room room){
        super(sprite, room);

        keyboard.keyPress("UP", new Runnable(){
            @Override
            public void run(){
                Ship.this.move.y(-shipSpeed);
            }
        });
        keyboard.keyPress("DOWN", new Runnable(){
            @Override
            public void run(){
                Ship.this.move.y(shipSpeed);
            }
        });
        keyboard.keyPress("LEFT", new Runnable(){
            @Override
            public void run(){
                Ship.this.move.x(-shipSpeed);
            }
        });
        keyboard.keyPress("RIGHT", new Runnable(){
            @Override
            public void run(){
                Ship.this.move.x(shipSpeed);
            }
        });

        keyboard.keyPress("SPACE", new Runnable(){
            TimeRegulator tr = new TimeRegulator(300);

            @Override
            public void run(){
                if(!tr.checkTime()){
                    return;
                }

                final GameObject laser = Ship.this.create.createAt(
                        new Sprite("/media/images/laser.png"),
                        Ship.this.room,
                        Ship.this.getX() + (Ship.this.getWidth() / 2) - 3,
                        Ship.this.getY());

                laser.move.moveToY(0 - laser.getHeight(), 15, new Runnable(){
                    @Override
                    public void run(){
                        laser.destroy.destroyGameObject();
                    }
                });
            }
        });
    }
}