/*package JGame.Events;

import JGame.Room.Room;
import JGame.Util.KeyboardMap;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class KeyboardEvent{

    Room comp;

    public KeyboardEvent(Room comp){
        this.comp = comp;
    }

    public void keyPress(final String key, final Runnable func){
        comp.getInputMap(Room.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), "do" + key + "Action");

        comp.getActionMap().put("do" + key + "Action", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent evt){
                KeyboardMap.set(key, true, func);
            }
        });


        comp.getInputMap(Room.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released " + key), "do" + key + "ActionReleased");
        comp.getActionMap().put("do" + key + "ActionReleased", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent evt){
                KeyboardMap.set(key, false, func);
            }
        });
    }
}
*/
