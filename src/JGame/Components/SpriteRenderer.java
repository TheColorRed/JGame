package JGame.Components;

import JGame.Behavior;
import JGame.Sprite;
import JGame.Util.PivotPoints;
import JGame.Util.Vector2;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Behavior{

    protected Sprite sprite = new Sprite();
    protected Vector2 pivotPoint;
    public String filename;

    public void start(){
        if(this.pivotPoint == null){
            this.setPivotPoint(PivotPoints.Center);
        }
    }

    public Vector2 getPivotPoint(){
        return this.pivotPoint;
    }

    public double getWidth(){
        return this.sprite.getWidth();
    }

    public double getHeight(){
        return this.sprite.getHeight();
    }

    public BufferedImage getSprite(){
        return this.sprite.getSprite();
    }

    public SpriteRenderer setSprite(String filename){
        this.sprite.setSprite(filename);
        return this;
    }

    public SpriteRenderer setPivotPoint(double x, double y){
        this.pivotPoint = new Vector2(x, y);
        return this;
    }

    public SpriteRenderer setPivotPoint(int pivotPoint){
        Vector2 vector2 = new Vector2(0, 0);
        Transform trans = gameObject.getComponent(Transform.class);
        double x = trans.position.x;
        double y = trans.position.y;
        double width = this.getWidth();
        double height = this.getHeight();
        switch(pivotPoint){
            case PivotPoints.Center:
                vector2 = new Vector2(x + width / 2, y + height / 2);
                break;
            case PivotPoints.TopLeft:
                vector2 = new Vector2(x, y);
                break;
            case PivotPoints.Top:
                vector2 = new Vector2(x + width / 2, y);
                break;
            case PivotPoints.TopRight:
                vector2 = new Vector2(x + width, y);
                break;
            case PivotPoints.Left:
                vector2 = new Vector2(x, y + height / 2);
                break;
            case PivotPoints.Right:
                vector2 = new Vector2(x + width, y + height / 2);
                break;
            case PivotPoints.BottomLeft:
                vector2 = new Vector2(x, y + height);
                break;
            case PivotPoints.Bottom:
                vector2 = new Vector2(x + width / 2, y + height);
                break;
            case PivotPoints.BottomRight:
                vector2 = new Vector2(x + width, y + height);
                break;
        }
        this.pivotPoint = vector2;
        return this;
    }
}
