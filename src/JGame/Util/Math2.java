package JGame.Util;

public class Math2{

    public static int random(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
