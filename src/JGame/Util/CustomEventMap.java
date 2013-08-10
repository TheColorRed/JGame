package JGame.Util;

import java.util.HashMap;
import java.util.Map;

public class CustomEventMap{

    public static Map<String, Mapping> map = new HashMap<>();

    public static void set(String key, Runnable run){
        Mapping mp = new Mapping();
        mp.doing = true;
        mp.run = run;
        CustomEventMap.map.put(key, mp);
    }

    public static Mapping get(String key){
        return CustomEventMap.map.get(key);
    }
}