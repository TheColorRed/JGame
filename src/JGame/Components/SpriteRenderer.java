package JGame.Components;

import JGame.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpriteRenderer extends Component{

    protected BufferedImage sprite;
    public String filename;

    public SpriteRenderer setSprite(String filename){
        this.filename = filename;
        try{
            this.sprite = ImageIO.read(getClass().getResource(filename));
        }catch(IOException ex){
            Logger.getLogger(SpriteRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public BufferedImage getSprite(){
        return this.sprite;
    }
}
