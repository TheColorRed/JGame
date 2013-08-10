package JGame.GameObject;

import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel{

    public Label(Font font){
        this.setFont(font);
    }

    public void setPosition(int x, int y){
        this.setLocation(x, y);
    }
}
