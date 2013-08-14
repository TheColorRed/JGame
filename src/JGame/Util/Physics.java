package JGame.Util;

public class Physics{

    protected float time = 0f;

    public void setTime(float time){
        this.time = time;
    }

    public double getDistance(){
        double dist = 0;
        dist = 0.5 * 9.8 * Math.pow(this.time, 2);
        return dist;
        //1/2 × 9.8 × 22
    }
}
