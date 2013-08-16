package JGame.Util;

import java.util.HashMap;
import java.util.Map;

public class SecondsIteratorMap{

    public Map<String, Mapping> map = new HashMap<>();

    public void set(String key, Boolean value, Runnable run){
        Mapping mp = new Mapping();
        mp.doing = value;
        mp.run = run;
        this.map.put(key, mp);
    }

    public Mapping get(String key){
        return this.map.get(key);
    }
}
