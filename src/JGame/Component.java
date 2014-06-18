package JGame;

import JGame.Components.BoxCollider;
import JGame.Components.CircleCollider;
import JGame.Components.Collider;
import JGame.Components.Rigidbody;
import JGame.Components.SpriteRenderer;
import JGame.Components.Transform;

public class Component extends Object{

    public SpriteRenderer renderer;
    public Transform transform;
    public GameObject gameObject;
    public Rigidbody rigidbody;
    public Collider boxCollider, circleCollider;

    public void setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
        this.transform = gameObject.getComponent(Transform.class);
        this.renderer = gameObject.getComponent(SpriteRenderer.class);
        this.rigidbody = gameObject.getComponent(Rigidbody.class);
        this.boxCollider = gameObject.getComponent(BoxCollider.class);
        this.circleCollider = gameObject.getComponent(CircleCollider.class);
    }

    public boolean hasCollider(){
        return boxCollider != null || circleCollider != null;
    }
}
