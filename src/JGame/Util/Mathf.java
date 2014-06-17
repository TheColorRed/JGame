package JGame.Util;

public class Mathf{

    private static double pp = 1;

    public static double pingPong(double time, double length){
        if(time >= length){
            pp = -1;
        }else if(time <= 0){
            pp = 1;
        }
        double val = pp;
        //System.out.println(val);
        return val;
    }

    public static int clamp(int number, int min, int max){
        return Math.max(min, Math.min(max, number));
    }

    public static double toRad(double deg){
        return deg * Math.PI / 180;
    }

    public static double toDeg(double rad){
        return rad * 180 / Math.PI;
    }
}
