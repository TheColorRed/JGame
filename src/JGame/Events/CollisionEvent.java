package JGame.Events;

import JGame.Util.CollisionMap;
import JGame.Util.Mapping;
import java.util.Map;

public class CollisionEvent{

    protected CollisionMap collisionMap = new CollisionMap();

    public void collidesWith(String reference, final Runnable func){
        collisionMap.set(reference, true, func);
    }

    public Map<String, Mapping> getMap(){
        return collisionMap.map;
    }

}
