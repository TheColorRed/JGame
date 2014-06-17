package JGame;

import JGame.Components.SpriteRenderer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Ryan Naddy <rnaddy@corp.acesse.com>
 */
public class Sprite extends Object{

    private BufferedImage sprite;

    public Sprite setSprite(String filename){
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
