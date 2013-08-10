package SpaceShooters;

import JGame.Game.Game;
import SpaceShooters.Rooms.Room1;
import javax.swing.JFrame;

public class Main extends JFrame{

    public static void main(String[] args){
        Game game = new Game("Space Shooters");
        game.startWindowed(800, 600);
        //game.startFullScreen();

        game.setRoom(new Room1());
    }
}
