package physics;

import JGame.Game.Game;
import physics.Rooms.Room1;

public class Physics{

    public static void main(String[] args){
        Game game = new Game("Ball Drop");
        game.startWindowed(800, 600);
        //game.startFullScreen();

        game.setRoom(new Room1());
    }
}
