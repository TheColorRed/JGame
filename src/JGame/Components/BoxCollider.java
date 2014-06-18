package JGame.Components;

import JGame.Util.Vector2;
import java.awt.Rectangle;

public class BoxCollider extends Collider{

    private double width = 0, height = 0, x = 0, y = 0;
    private Rectangle rectangle = new Rectangle();

    public void start(){
        Transform trans = gameObject.getComponent(Transform.class);
        SpriteRenderer rend = gameObject.getComponent(SpriteRenderer.class);
        this.width = rend.getWidth();
        this.height = rend.getHeight();
        this.x = trans.position.getX();
        this.y = trans.position.getY();
        this.resizeRect();
    }

    public Rectangle getRect(){
        return this.rectangle;
    }

    public double getHeight(){
        return height;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public double getWidth(){
        return width;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public double getX(){
        return x;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getY(){
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    public BoxCollider setSize(double width, double height){
        this.setWidth(width);
        this.setHeight(height);
        this.resizeRect();
        return this;
    }

    public BoxCollider setOffset(double x, double y){
        Vector2 position = this.gameObject.getComponent(Transform.class).position;
        this.x = position.getX() + x;
        this.y = position.getY() + y;
        this.resizeRect();
        return this;
    }

    private void resizeRect(){
        this.rectangle.width = (int)this.width;
        this.rectangle.height = (int)this.height;
        this.rectangle.x = (int)this.x;
        this.rectangle.y = (int)this.y;
    }
}
