package JGame.Util;

import java.util.HashMap;
import java.util.Map;

public class MouseMap{

    public static Map<Integer, Mapping> map = new HashMap<>();

    public static void set(int key, Boolean value, Runnable run){
        Mapping mp = new Mapping();
        mp.doing = value;
        mp.run = run;
        MouseMap.map.put(key, mp);
    }

    public static Mapping get(Integer key){
        return MouseMap.map.get(key);
    }
}
