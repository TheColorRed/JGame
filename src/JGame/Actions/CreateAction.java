package JGame.Actions;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;

public class CreateAction extends Action{

    public GameObject create(String reference, Sprite sprite, Room room){
        GameObject obj = new GameObject(reference, sprite, room);
        return obj;
    }

    public GameObject createAt(String reference, Sprite sprite, Room room, int x, int y){
        GameObject obj = new GameObject(reference, sprite, room);
        room.addGameObjectAt(obj, x, y);
        return obj;
    }
}
