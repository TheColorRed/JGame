package JGame.Events;

import JGame.Util.CollisionMap;

public class CollisionEvent{

    public void collidesWith(String reference, final Runnable func){
        CollisionMap.set(reference, true, func);
    }
}
