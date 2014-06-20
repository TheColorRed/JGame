package JGame.Components;

import JGame.Behavior;
import JGame.Util.Time;

public class Rigidbody extends Behavior{

    public double gravity = 1;
    public double energyLoss = .75;
    public double dy = 50, dx = 30;
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

        double centerX = transform.position.getX() + renderer.getWidth() / 2;
        double centerY = transform.position.getY() + renderer.getHeight() / 2;
        double otherCenterX = otherTrans.position.getX() + otherRenderer.getWidth() / 2;
        double otherCenterY = otherTrans.position.getY() + otherRenderer.getHeight() / 2;

        double wy = (renderer.getWidth() + otherRenderer.getWidth()) * (centerY - otherCenterY);
        double hx = (renderer.getHeight() + otherRenderer.getHeight()) * (centerX - otherCenterX);

        if(centerY <= otherCenterY - (otherRenderer.getHeight() / 2)){
            //System.out.println("Bottom");
            this.y = otherTrans.position.getY() - renderer.getHeight() - 1;
            this.dy *= this.energyLoss;
            this.dy = -this.dy;
            if(Math.abs(this.dy) < .1){
                this.dy = 0;
            }
        }else if(centerY >= otherCenterY + (otherRenderer.getHeight() / 2)){
            //System.out.println("Top");
            this.y = otherTrans.position.getY() + otherRenderer.getHeight() + 1;
            this.dy = -this.dy;
        }else if(centerX < otherCenterX){
            //System.out.println("Right");
            this.x = otherTrans.position.getX() - renderer.getWidth() - 1;
            this.dx *= this.energyLoss;
            this.dx = -this.dx;
        }else if(centerX > otherCenterX){
            //System.out.println("Left");
            this.x = otherTrans.position.getX() + otherRenderer.getWidth() + 1;
            this.dx *= this.energyLoss;
            this.dx = -this.dx;
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
