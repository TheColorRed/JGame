package JGame.Util;

import JGame.Room.Room;

public class Physics{

    protected double time = 0;
    protected double mass = 0;

    public void setMass(double mass){
        this.mass = mass;
    }

    public void setTime(double time){
        this.time = time;
    }

    public double getDistance(){
        return 0.5 * Room.getGravity() * Math.pow(this.time * Room.gameTime, 2);
    }

    public double getVelocity(){
        return Room.getGravity() * (this.time * Room.gameTime);
    }

    public double getMass(){
        return this.mass;
    }
}
