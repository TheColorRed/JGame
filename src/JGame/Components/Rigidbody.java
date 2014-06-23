package JGame.Components;

import JGame.Behavior;
import JGame.Util.Mathf;
import JGame.Util.Time;
// See:
// http://stackoverflow.com/questions/18704999/how-to-fix-circle-and-rectangle-overlap-in-collision-response/18790389#18790389

public class Rigidbody extends Behavior{

    private double gravity = 1, mass = 1;
    private double energyLoss = .75;
    private double dy = .1, dx = 0;
    private double y = 0, x = 0;
    private double xFriction = 0.80;

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
        this.x = this.trans.position.x;
        this.y = this.trans.position.y;
    }

    public void onCollision(Collider other){
        Transform otherTrans = other.gameObject.getComponent(Transform.class);
        SpriteRenderer otherRenderer = other.gameObject.getComponent(SpriteRenderer.class);
        //Rigidbody otherRigidbody = other.gameObject.getComponent(Rigidbody.class);

        //double centerX = transform.position.x + renderer.getWidth() / 2;
        //double centerY = transform.position.y + renderer.getHeight() / 2;
        //double otherCenterX = otherTrans.position.x + otherRenderer.getWidth() / 2;
        //double otherCenterY = otherTrans.position.y + otherRenderer.getHeight() / 2;
        double centerX = transform.position.x;
        double centerY = transform.position.y;
        double otherCenterX = otherTrans.position.x;
        double otherCenterY = otherTrans.position.y;
        double ovke = 0, ohke = 0, hke = 0, vke = 0;
//        if(otherRigidbody != null){
//            double otherVForce = otherRigidbody.getVerticalForce();
//            double otherHForce = otherRigidbody.getHorizontalForce();
//            double otherMass = otherRigidbody.getMass();
//            vke = 0.5 * this.mass * Math.abs(this.dy) * Math.abs(this.dy);
//            ovke = 0.5 * otherMass * Math.abs(otherVForce) * Math.abs(otherVForce);
//            hke = 0.5 * this.mass * this.dx * this.dx;
//            ohke = 0.5 * otherMass * Math.abs(otherHForce) * Math.abs(otherHForce);
//        }

        if(centerY <= otherCenterY - (otherRenderer.getHeight() / 2) + renderer.pivotPoint.y){
            //System.out.println("Bottom");
            this.y = otherTrans.position.y - renderer.getHeight() + renderer.pivotPoint.y - 1;
//            if(otherRigidbody != null){
//                this.dy += (ovke / vke);
//            }
            this.dy *= this.energyLoss;
            this.dy = -this.dy;
        }else if(centerY >= otherCenterY + (otherRenderer.getHeight() / 2) + renderer.pivotPoint.y){
            //System.out.println("Top");
            this.y = otherTrans.position.y + otherRenderer.getHeight() + renderer.pivotPoint.y + 1;
//            if(otherRigidbody != null){
//                this.dy += -(ovke / vke);
//            }
            this.dy = -this.dy;
        }else if(centerX < otherCenterX){
            System.out.println("Right");
            this.x = otherTrans.position.x - otherRenderer.pivotPoint.x - renderer.pivotPoint.x - 1;
//            if(otherRigidbody != null){
//                this.dx += (ohke / hke);
//            }
            this.dx *= this.energyLoss;
            this.dx = -this.dx;
        }else if(centerX > otherCenterX){
            System.out.println("Left");
            this.x = otherTrans.position.x + otherRenderer.pivotPoint.x + otherRenderer.getWidth() - renderer.pivotPoint.x + 1;
//            if(otherRigidbody != null){
//                this.dx += (ohke / hke);
//            }
            this.dx *= this.energyLoss;
            this.dx = -this.dx;
        }
        if(Math.abs(this.dy) < .9){
            this.dy = 0;
        }
        // Friction
        if(transform.position.y + renderer.getHeight() >= otherTrans.position.y - 1
                && this.xFriction > 0){
            this.dx *= this.xFriction;
            if(Math.abs(this.dx) < .01){
                this.dx = 0;
            }
        }
    }

    public void update(){
        double delta = Time.deltaTime;
        x += this.dx * delta;
        if(this.gravity > 0){
            this.dy += this.gravity * delta;
            y += this.dy * delta + 0.5 * this.gravity * delta * delta;
        }else{
            y += this.dy * delta;
        }
        this.trans.setPosition(x, y);
    }

    public Rigidbody setEnergyLoss(double amount){
        amount = Mathf.clamp(amount, 0, 1);
        this.energyLoss = amount;
        return this;
    }

    public Rigidbody setFriction(double friction){
        this.xFriction = friction;
        return this;
    }

    public Rigidbody setVerticalForce(double force){
        this.dy = force;
        return this;
    }

    public Rigidbody setHorizontalForce(double force){
        this.dx = force;
        return this;
    }

    public double getEnergyLoss(){
        return this.energyLoss;
    }

    public double getFriction(){
        return this.xFriction;
    }

    public double getVerticalForce(){
        return this.dy;
    }

    public double getHorizontalForce(){
        return this.dx;
    }

    public double getGravity(){
        return gravity;
    }

    public Rigidbody setGravity(double gravity){
        this.gravity = gravity;
        return this;
    }

    public double getMass(){
        return mass;
    }

    public Rigidbody setMass(double mass){
        this.mass = mass;
        return this;
    }
}
