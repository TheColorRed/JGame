package SpaceShooters.Rooms;

import JGame.Game.Game;
import JGame.GameObject.GameFont;
import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;
import JGame.Util.Math2;
import SpaceShooters.Objects.Enemy;
import SpaceShooters.Objects.Ship;
import java.awt.Color;

public final class Room1 extends Room{

    public final int shipSpeed = 10;

    public Room1(){
        this.setupBackground();
        this.setupGameObjects();
        this.setupFonts();
    }

    public void setupBackground(){
        this.setBackground("/media/images/landscape1.jpg");
    }

    public void setupFonts(){
        GameFont font = new GameFont("/media/fonts/future.ttf", 40);
        GameObject go = new GameObject(
                new Sprite(font.getFont(), "LEVEL   1", Color.black),
                this);
        this.addGameObjectAt(go, 10, 10);
    }

    public void setupGameObjects(){
        this.setupShip();
        this.setupEnemies();
    }

    protected void setupShip(){
        Ship ship = new Ship(
                new Sprite("/media/images/ship.png", 80),
                this);
        ship.setLeaveScreen(false);
        this.addGameObjectAt(
                ship,
                Game.CENTERX - (ship.getWidth() / 2),
                this.getHeight() - ship.getHeight() - 50);
    }

    protected void setupEnemies(){
        Enemy enemy = new Enemy(
                new Sprite("/media/images/enemy.png", 40),
                this);
        this.addGameObject(enemy);
        this.setEnemyLocation(enemy);
        this.moveEnemy(enemy);
    }

    protected void setEnemyLocation(final Enemy enemy){
        enemy.move.jumpTo(Math2.random(20, Game.width - enemy.getWidth() - 20), 0);
    }

    protected void moveEnemy(final Enemy enemy){
        enemy.move.moveToY(Game.height, Math2.random(3, 8), new Runnable(){
            @Override
            public void run(){
                Room1.this.setEnemyLocation(enemy);
                Room1.this.moveEnemy(enemy);
            }
        });
    }
}
