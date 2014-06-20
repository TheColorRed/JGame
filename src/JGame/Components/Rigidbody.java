package JGame.Components;

import JGame.Behavior;
import JGame.Util.Time;

public class Rigidbody extends Behavior{

    public double gravity = 1;
    public double energyLoss = .75;
    public double dy = 50, dx = 50;
    public double y = 0, x = 0;
    public double xFriction = 0.80;

    protected Transform trans;
    protected SpriteRenderer spr;
    protected BoxCollider bxCollider;

    protected double height = 0, width = 0;

    public void start(){
        this.trans = gameObject.getComponent(Transform.class);
        this.spr = gameObject.getComponent(SpriteRenderer.class);
        this.bxCollider = gameObject.getComponent(BoxCollider.class);
        this.height = spr.getHeight();
        this.width = spr.getWidth();
        this.x = this.trans.position.getX();
        this.y = this.trans.position.getY();
    }

    public void onCollision(Collider other){
        Transform otherTrans = other.gameObject.getComponent(Transform.class);
        SpriteRenderer otherRenderer = other.gameObject.getComponent(SpriteRenderer.class);
        BoxCollider bxColl = other.gameObject.getComponent(BoxCollider.class);

        int collisionX = bxColl.getRect().intersection(bxCollider.getRect()).x;
        int collisionY = bxColl.getRect().intersection(bxCollider.getRect()).y;
        // Left/Right Movement
        if(collisionX + 1 >= transform.position.getX() + renderer.getWidth() - 1){
            System.err.println("Right");
            this.x = otherTrans.position.getX() - renderer.getWidth() - 1;
            this.dx *= this.energyLoss;
            this.dx = -this.dx;
        }else if(collisionX + 1 >= transform.position.getX() - 1
                 && collisionY <= transform.position.getY()){
            System.err.println("Left");
            this.x = otherTrans.position.getX() + otherRenderer.getWidth() + 1;
            this.dx *= this.energyLoss;
            this.dx = -this.dx;
        } // Up/down Movement
        else if(collisionY + 1 >= transform.position.getY() + renderer.getHeight() - 1){
            System.err.println("Bottom");
            this.y = otherTrans.position.getY() - renderer.getHeight() - 1;
            this.dy *= this.energyLoss;
            this.dy = -this.dy;
            if(Math.abs(this.dy) < .1){
                this.dy = 0;
            }
        }else if(collisionY >= transform.position.getY() - 1
                 && collisionX <= transform.position.getX() + 1){
            System.err.println("Top");
            System.err.println(collisionX + ":" + (transform.position.getX() - 1));
            System.err.println(collisionY + ":" + (transform.position.getY() - 1));
            this.y = otherTrans.position.getY() + otherRenderer.getHeight() + 1;
            this.dy = -this.dy;
            System.err.println(this.y);
        }
        // Friction
        if(transform.position.getY() + renderer.getHeight() >= otherTrans.position.getY() - 1){
            this.dx *= this.xFriction;
            if(Math.abs(this.dx) < .01){
                this.dx = 0;
            }
        }

    }

    public void update(){
        double delta = Time.deltaTime;
        x += this.dx * delta;
        this.dy += this.gravity * delta;
        y += this.dy * delta + 0.5 * this.gravity * delta * delta;
        this.trans.setPosition(x, y);
    }
}
