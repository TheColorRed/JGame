package JGame.Events;

import JGame.Game.Stage;
import JGame.Util.CustomEventMap;

public class CustomEvent{

    Stage comp;

    public CustomEvent(Stage comp){
        this.comp = comp;
    }

    public void triggerEvent(String eventName, final Runnable event){
        CustomEventMap.set(eventName, event);
    }
}
