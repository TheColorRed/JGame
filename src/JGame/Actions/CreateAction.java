package JGame.Actions;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;

public class CreateAction extends Action{

    public GameObject create(Sprite sprite, Room room){
        GameObject obj = new GameObject(sprite, room);
        return obj;
    }

    public GameObject createAt(Sprite sprite, Room room, int x, int y){
        GameObject obj = new GameObject(sprite, room);
        room.addGameObjectAt(obj, x, y);
        return obj;
    }
}
