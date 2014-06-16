package JGame;

import JGame.Components.SpriteRenderer;
import JGame.Components.Transform;

public class Component extends Object{

    public SpriteRenderer renderer;
    public Transform transform;
    public GameObject gameObject;

    public void setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
        this.transform = gameObject.getComponent(Transform.class);
        this.renderer = gameObject.getComponent(SpriteRenderer.class);
    }
}
