package JGame;

import JGame.Components.SpriteRenderer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameObject extends Object{

    protected HashMap<Class, Component> components = new HashMap();

    public SpriteRenderer renderer;

    public <T extends Component> T addComponent(Class<T> component){
        try{
            T inst = component.newInstance();
            this.components.put(component, inst);
            if(component.equals(SpriteRenderer.class)){
                this.renderer = (SpriteRenderer)inst;
            }
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

    /*public <T extends Component> Component getComponent(Class<T> component){
     for(Class c : this.components){
     if(c.equals(component)){
     //return this.getClass().cast();
     System.out.println("Here");
     }
     }
     return null;
     }*/
}
