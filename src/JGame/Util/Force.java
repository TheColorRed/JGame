package JGame.Util;

/**
 *
 * @author Ryan Naddy <rnaddy@corp.acesse.com>
 */
public class Force{

    public String name;
    private double force, vector;

    public Force(String name){
        this.name = name;
        this.force = 0;
        this.vector = 0;
    }

    public Force(String name, float newtons, float vector){
        this(name);
        this.force = newtons;
        this.vector = vector;
    }

    public double getForce(){
        return force;
    }

    public void setForce(double force){
        this.force = force;
    }

    public double getVector(){
        return vector;
    }

    public void setVector(double vector){
        this.vector = vector;
    }
}
