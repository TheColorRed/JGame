package JGame.Events;

import JGame.Util.IterationMap;
import JGame.Util.Mapping;
import java.util.Map;

public class IterationEvent{

    protected IterationMap iterationMap = new IterationMap();

    public void set(String reference, final Runnable func){
        iterationMap.set(reference, true, func);
    }

    public void remove(String reference){
        this.getMap().remove(reference);
    }

    public Map<String, Mapping> getMap(){
        return iterationMap.map;
    }

}
