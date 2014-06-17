package JGame.Util;

/**
 *
 * @author Ryan Naddy <rnaddy@corp.acesse.com>
 */
public class Random{

    public static int range(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static Object choose(Object... args){
        return args[Random.range(0, args.length - 1)];
    }
}
