package Physics;

/**
 *
 * @author Ryan Naddy <rnaddy@corp.acesse.com>
 */
public class Force{

    public String name;
    private float force, vector;

    public Force(String name){
        this.name = name;
        this.force = 0;
        this.vector = 0;
    }

    public Force(String name, float newtons, float vector){
        this(name);
        this.force = newtons;
        this.force = vector;
    }

    public float getForce(){
        return force;
    }

    public void setForce(float force){
        this.force = force;
    }

    public float getVector(){
        return vector;
    }

    public void setVector(float vector){
        this.vector = vector;
    }
}
