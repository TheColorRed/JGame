package JGame.Util;

import java.util.HashMap;
import java.util.Map;

public class CollisionMap{

    public static Map<String, Mapping> map = new HashMap<>();

    public static void set(String key, Boolean value, Runnable run){
        Mapping mp = new Mapping();
        mp.doing = value;
        mp.run = run;
        CollisionMap.map.put(key, mp);
    }

    public static Mapping get(String key){
        return CollisionMap.map.get(key);
    }
}