package JGame.Components;

import JGame.Behavior;
import JGame.Util.Time;

public class Rigidbody extends Behavior{

    public double gravity = 0.5;
    public double energyLoss = .75;
    public double dy = 10, dx = 20;
    public double y = 0, x = 0;
    public double xFriction = 0.99;

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

    public void update(){
        double delta = Time.deltaTime;
        //x = this.trans.position.getX();
        //y = this.trans.position.getY();
        // Movement Left/Right
        if(x + this.dx > 600){
            //this.trans.position = new Vector2(600, y);
            x = 600;
            this.dx = -this.dx;
        }else if(x + this.dx < 0){
            x = 0;
            this.dx = -this.dx;
        }else{
            x += this.dx * delta;
        }
        // Friction
        if(y > 400 - 1){
            this.dx *= this.xFriction;
            if(Math.abs(this.dx) < .0001){
                this.dx = 0;
            }
        }
        // Movement Up/Down
        if(y > 400){
            //this.trans.position = new Vector2(x, 400 - 1);
            y = 400;
            this.dy *= this.energyLoss;
            this.dy = -this.dy;
            if(Math.abs(this.dy) < .1){
                this.dy = 0;
            }
        }else{
            this.dy += this.gravity * delta;
            y += this.dy * delta + 0.5 * this.gravity * delta * delta;
        }
        //x = dx;
        //y = dy;
        //x += delta * this.dx;
        this.trans.setPosition(x, y);
        //this.trans.position = new Vector2(x * delta, y);
        //this.trans.translate(x * delta, y * delta);
    }
}
