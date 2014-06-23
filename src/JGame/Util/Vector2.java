package JGame.Util;

import JGame.GameObject;

public class Vector2{

    public final double x;
    public final double y;

    public Vector2(final double x, final double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 multiply(double amount){
        return new Vector2(this.x * amount, y * amount);
    }

    public Vector2 divide(double amount){
        return new Vector2(this.x / amount, y / amount);
    }

    public Vector2 add(double amount){
        return new Vector2(this.x + amount, y + amount);
    }

    public Vector2 add(Vector2 vector2){
        return new Vector2(this.x + vector2.x, this.y + vector2.y);
    }

    public Vector2 subtract(double amount){
        return new Vector2(this.x - amount, y - amount);
    }

    public Vector2 subtract(Vector2 vector2){
        return new Vector2(this.x - vector2.x, this.y - vector2.y);
    }
}
