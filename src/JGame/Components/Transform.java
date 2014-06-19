package JGame.Components;

import JGame.Behavior;
import JGame.Util.Vector2;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Transform extends Behavior{

    public Vector2 position = new Vector2(0, 0);

    public void translate(double x, double y){
        position = new Vector2(position.getX() + x, position.getY() + y);
        BoxCollider boxCollider = this.gameObject.getComponent(BoxCollider.class);
        if(boxCollider != null){
            Transform trans = this.gameObject.getComponent(Transform.class);
            boxCollider.setX(trans.position.getX());
            boxCollider.setY(trans.position.getY());
        }
    }

    public void setPosition(double x, double y){
        position = new Vector2(x, y);
        BoxCollider boxCollider = this.gameObject.getComponent(BoxCollider.class);
        if(boxCollider != null){
            Transform trans = this.gameObject.getComponent(Transform.class);
            boxCollider.setX(trans.position.getX());
            boxCollider.setY(trans.position.getY());
        }
    }

    public void scale(double x, double y){
        //x = Mathf.clamp(x, 0, 1);
        //y = Mathf.clamp(y, 0, 1);
        SpriteRenderer spr = gameObject.getComponent(SpriteRenderer.class);
        if(x == 1 && y == 1){
            spr.sprite.setOrigSprite();
            return;
        }
        BufferedImage before = spr.sprite.getSprite();
        Image tempImg = before.getScaledInstance((int)x, (int)y, BufferedImage.SCALE_SMOOTH);
        BufferedImage after = new BufferedImage((int)x, (int)y, BufferedImage.TYPE_INT_ARGB);

        Graphics2D bgr = after.createGraphics();
        bgr.drawImage(tempImg, 0, 0, null);
        bgr.dispose();

        spr.sprite.setResized(after);
    }

    public void rotate(double rotation){

    }

}
