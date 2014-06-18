package JGame.Components;

import JGame.Component;
import JGame.Sprite;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component{

    protected Sprite sprite = new Sprite();
    public String filename;

    public double getWidth(){
        return this.sprite.getWidth();
    }

    public double getHeight(){
        return this.sprite.getHeight();
    }

    public BufferedImage getSprite(){
        return this.sprite.getSprite();
    }

    public void setSprite(String filename){
        this.sprite.setSprite(filename);
    }
}
