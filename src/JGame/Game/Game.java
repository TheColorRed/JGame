package JGame.Game;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Game extends JFrame{

    protected Room[] rooms = new Room[10];
    public static int CENTERX = 0, CENTERY = 0;
    public static int width = 0, height = 0;

    public Game(String gameTitle){
        this.setTitle(gameTitle);
    }

    public void setKeyEvents(KeyListener key){
        this.addKeyListener(key);
    }

    public void startFullScreen(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice myDevice = ge.getDefaultScreenDevice();
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            myDevice.setFullScreenWindow(this);
        }finally{
            //myDevice.setFullScreenWindow(null);
        }

        Game.width = this.getWidth();
        Game.height = this.getHeight();
        Game.CENTERX = (int)(width / 2.0);
        Game.CENTERY = (int)(height / 2.0);
    }

    public void startWindowed(int width, int height){
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game.width = width - (this.getInsets().left + this.getInsets().right);
        Game.height = height - (this.getInsets().top + this.getInsets().bottom);
        Game.CENTERX = (int)(width / 2.0);
        Game.CENTERY = (int)(height / 2.0);
    }

    public void setRoom(Room room){
        room.setSize(Game.width, Game.height);
        room.setPreferredSize(new Dimension(Game.width, Game.height));
        rooms[0] = room;
        //this.add(rooms[0]);
        this.repaint();
    }

    public void start(){
        rooms[0].start();
    }
}
