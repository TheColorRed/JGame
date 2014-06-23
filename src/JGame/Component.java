package JGame;

import JGame.Components.Audio;
import JGame.Components.BoxCollider;
import JGame.Components.CircleCollider;
import JGame.Components.Collider;
import JGame.Components.Rigidbody;
import JGame.Components.SpriteRenderer;
import JGame.Components.Transform;
import JGame.Util.Vector2;

public class Component extends Object{

    public Audio audio;
    public Collider boxCollider, circleCollider;
    public GameObject gameObject;
    public Rigidbody rigidbody;
    public SpriteRenderer renderer;
    public Transform transform;

    public void setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
        this.transform = gameObject.getComponent(Transform.class);
        this.renderer = gameObject.getComponent(SpriteRenderer.class);
        this.rigidbody = gameObject.getComponent(Rigidbody.class);
        this.boxCollider = (BoxCollider)gameObject.getComponent(BoxCollider.class);
        this.circleCollider = (CircleCollider)gameObject.getComponent(CircleCollider.class);
        this.audio = (Audio)gameObject.getComponent(Audio.class);

    }

    public boolean hasCollider(){
        return boxCollider != null || circleCollider != null;
    }
}
