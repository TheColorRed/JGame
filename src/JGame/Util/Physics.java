package JGame.Util;

import JGame.Room.Room;

public class Physics{

    protected double time = 0;
    protected double mass = 0;

    public void setMass(double mass){
        this.mass = mass;
    }

    public void setSeconds(double time){
        this.time = time;
    }

    public double getTime(){
        return this.time * Room.gameTime;
    }

    public double getDistance(){
        return 0.5 * Room.getGravity() * Math.pow(this.getTime(), 2);
    }

    public double getVelocity(){
        return Room.getGravity() * this.getTime();
    }

    public double getSpeed(){
        return this.getDistance() / this.getTime();
    }

    public double getVY(){
        //vy = - vbounce - 1/2 g t2
        //double vy = 0 + 0.5 * Room.getGravity() * Math.pow(this.time * Room.gameTime, 2);
        double vy = -(this.getSpeed() - (0.5 * Room.getGravity() * Math.pow(this.getTime(), 2)));
        return vy;
    }

    public double getMass(){
        return this.mass;
    }
}
