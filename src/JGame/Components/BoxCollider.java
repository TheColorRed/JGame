package JGame.Components;

import JGame.Component;
import JGame.Game.Stage;
import JGame.GameObject;
import JGame.Util.Vector2;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

public class BoxCollider extends Collider{

    private double width = 0, height = 0, x = 0, y = 0;
    private Rectangle rectangle = new Rectangle();
    private Transform trans;
    private SpriteRenderer spr;
    public boolean showCollider = true;

    public void start(){
        this.trans = this.gameObject.getComponent(Transform.class);
        this.spr = this.gameObject.getComponent(SpriteRenderer.class);
        this.width = this.spr.getWidth();
        this.height = this.spr.getHeight();
        this.x = this.trans.position.x - spr.pivotPoint.x;
        this.y = this.trans.position.y - spr.pivotPoint.y;
        this.resizeRect();
    }

    public void update(){
        for(GameObject go : Stage.gameObjects){
            if(go == gameObject){
                continue;
            }
            HashMap<Class, Component> hm = go.getComponents();
            for(Map.Entry pairs : hm.entrySet()){
                Component comp = (Component)pairs.getValue();
                if(comp instanceof Collider){
                    BoxCollider bx = (BoxCollider)comp;
                    if(bx.rectangle.intersects(this.rectangle)){
                        this.sendCollision(gameObject, bx);
                    }
                }
            }
        }
    }

    private void sendCollision(GameObject go, Collider collider){
        HashMap<Class, Component> hm = go.getComponents();
        for(Map.Entry pairs : hm.entrySet()){
            Component comp = (Component)pairs.getValue();
            comp.onCollision(collider);
        }
    }

    public Rectangle getRect(){
        return this.rectangle;
    }

    public double getHeight(){
        return height;
    }

    public void setHeight(double height){
        this.height = height;
        this.resizeRect();
    }

    public double getWidth(){
        return width;
    }

    public void setWidth(double width){
        this.width = width;
        this.resizeRect();
    }

    public double getX(){
        return x;
    }

    public void setX(double x){
        this.x = x;
        this.resizeRect();
    }

    public double getY(){
        return y;
    }

    public void setY(double y){
        this.y = y;
        this.resizeRect();
    }

    public BoxCollider setSize(double width, double height){
        this.setWidth(width);
        this.setHeight(height);
        this.resizeRect();
        return this;
    }

    public BoxCollider setOffset(double x, double y){
        Vector2 position = this.gameObject.getComponent(Transform.class).position;
        this.x = position.x + x;
        this.y = position.x + y;
        this.resizeRect();
        return this;
    }

    private void resizeRect(){
        SpriteRenderer spr = this.gameObject.getComponent(SpriteRenderer.class);
        this.rectangle.width = (int)this.width;
        this.rectangle.height = (int)this.height;
        this.rectangle.x = (int)this.x;// - (int)spr.pivotPoint.x;
        //System.out.println(this.y + ":" + pivotPoint.y + ":" + this.rectangle.y);
        this.rectangle.y = (int)this.y;
    }
}
