package JGame.GameObject;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class GameFont extends Font{

    protected Font font;

    public GameFont(String filename, float fontSize){
        super("serif", Font.PLAIN, 24);
        try{
            InputStream is = this.getClass().getResourceAsStream(filename);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(fontSize);
        }catch(FontFormatException | IOException ex){
            font = new Font("serif", Font.PLAIN, 24);
        }
    }

    public Font getFont(){
        return this.font;
    }

    public Label createText(String text){
        Label lbl = new Label(font);
        lbl.setText(text);
        return lbl;
    }
}
