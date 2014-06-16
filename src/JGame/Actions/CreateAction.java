/*package JGame.Actions;

import JGame.GameObject.GameObject;
import JGame.GameObject.Sprite;
import JGame.Room.Room;
import JGame.Util.Math2;

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

    public GameObject createAtRandom(String reference, Sprite sprite, Room room){
        GameObject obj = new GameObject(reference, sprite, room);
        int maxX = room.getWidth() - obj.getWidth();
        int maxY = room.getHeight() - obj.getHeight();
        int x = Math2.random(0, maxX);
        int y = Math2.random(0, maxY);
        room.addGameObjectAt(obj, x, y);
        return obj;
    }
}
*/
