package JGame.Components;

import JGame.Behavior;
import JGame.Util.Time;

public class Rigidbody extends Behavior{

    public double gravity = 1;
    public double energyLoss = .75;
    public double dy = 10, dx = 20;
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
        if(transform.position.getX() + renderer.getWidth() >= otherTrans.position.getX() - 1){
            this.x = otherTrans.position.getX() - renderer.getWidth() - 1;
            this.dx = -this.dx;
        }else if(x + this.dx < 0){
            x = 0;
            this.dx = -this.dx;
        }
        // Friction
        if(transform.position.getY() + renderer.getHeight() >= otherTrans.position.getY() - 1){
            this.dx *= this.xFriction;
            if(Math.abs(this.dx) < .01){
                this.dx = 0;
            }
        }
        // Up/down Movement
        if(transform.position.getY() + renderer.getHeight() >= otherTrans.position.getY() - 1){
            this.y = otherTrans.position.getY() - renderer.getHeight() - 1;
            this.dy *= this.energyLoss;
            this.dy = -this.dy;
            if(Math.abs(this.dy) < .1){
                this.dy = 0;
            }
        }
    }

    public void update(){
        double delta = Time.deltaTime;
        // Movement Left/Right
//        if(x + this.dx > 600){
//            //this.trans.position = new Vector2(600, y);
//            x = 600;
//            this.dx = -this.dx;
//        }else if(x + this.dx < 0){
//            x = 0;
//            this.dx = -this.dx;
//        }else{
        x += this.dx * delta;
//        }
        // Friction
//        if(y > 450 - 1){
//            this.dx *= this.xFriction;
//            if(Math.abs(this.dx) < .0001){
//                this.dx = 0;
//            }
//        }
        // Movement Up/Down
//        if(y > 450){
//            //this.trans.position = new Vector2(x, 400 - 1);
//            y = 450;
//            this.dy *= this.energyLoss;
//            this.dy = -this.dy;
//            if(Math.abs(this.dy) < .1){
//                this.dy = 0;
//            }
//        }else{
        this.dy += this.gravity * delta;
        y += this.dy * delta + 0.5 * this.gravity * delta * delta;
//        }
        this.trans.setPosition(x, y);
    }
}
