package JGame.Events;

import JGame.Game.Room;
import JGame.Util.CustomEventMap;

public class CustomEvent{

    Room comp;

    public CustomEvent(Room comp){
        this.comp = comp;
    }

    public void triggerEvent(String eventName, final Runnable event){
        CustomEventMap.set(eventName, event);
    }
}
