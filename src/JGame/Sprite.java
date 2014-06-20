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

    public boolean isScaled = false;
    private BufferedImage sprite, origSprite;
    private int width = 0, height = 0;

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public Sprite setSprite(String filename){
        try{
            this.sprite = ImageIO.read(getClass().getResource(filename));
            this.origSprite = this.sprite;
            this.isScaled = false;
            this.setSize();
        }catch(IOException ex){
            Logger.getLogger(SpriteRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public Sprite setSprite(BufferedImage image){
        this.sprite = image;
        this.origSprite = this.sprite;
        this.isScaled = false;
        this.setSize();
        return this;
    }

    public Sprite setResized(BufferedImage image){
        this.sprite = image;
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.isScaled = true;
        return this;
    }

    public BufferedImage getSprite(){
        return this.sprite;
    }

    public void setOrigSprite(){
        this.sprite = this.origSprite;
        this.isScaled = false;
    }

    private void setSize(){
        this.width = this.sprite.getWidth();
        this.height = this.sprite.getHeight();
    }
}
