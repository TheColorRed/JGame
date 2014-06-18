package JGame;

import JGame.Components.Collider;
import JGame.Components.SpriteRenderer;
import JGame.Components.Transform;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameObject extends Object{

    protected HashMap<Class, Component> components = new HashMap();
    private String name = "";

    public GameObject(String spriteFileLocation){
        this();
        this.addComponent(SpriteRenderer.class).setSprite(spriteFileLocation);
    }

    public GameObject(){
        this.addComponent(Transform.class);
    }

    public <T extends Component> T addComponent(Class<T> component){
        try{
            T inst = component.newInstance();
            inst.setGameObject(this);
            this.components.put(component, inst);
            return inst;
        }catch(InstantiationException | IllegalAccessException ex){
            Logger.getLogger(GameObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HashMap<Class, Component> getComponents(){
        return this.components;
    }

    public <T extends Component> T getComponent(Class<T> component){
        HashMap<Class, Component> hm = this.getComponents();
        for(Map.Entry pairs : hm.entrySet()){
            if(pairs.getKey().equals(component)){
                return (T)pairs.getValue();
            }
        }
        return null;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
