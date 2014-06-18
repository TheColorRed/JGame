package JGame.Components;

import JGame.Behavior;
import JGame.Util.Mathf;
import JGame.Util.Vector2;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Transform extends Behavior{

    public Vector2 position = new Vector2(0, 0);

    public void translate(double x, double y){
        position = new Vector2(position.getX() + x, position.getY() + y);
    }

    public void scale(double x, double y){
        x = Mathf.clamp(x, 0, 1);
        y = Mathf.clamp(y, 0, 1);
        SpriteRenderer spr = gameObject.getComponent(SpriteRenderer.class);
        if(x == 1 && y == 1){
            spr.sprite.setOrigSprite();
            return;
        }
        BufferedImage before = spr.sprite.getSprite();
        BufferedImage after = new BufferedImage(
                before.getWidth(),
                before.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        AffineTransform at = new AffineTransform();
        at.scale(x, y);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);
        spr.sprite.setResized(after);
    }

    public void rotate(double rotation){

    }

}
