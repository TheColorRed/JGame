package JGame.Game;

import JGame.Component;
import JGame.Components.BoxCollider;
import JGame.Components.Collider;
import JGame.Components.SpriteRenderer;
import JGame.Components.Transform;
import JGame.GameObject;
import JGame.Util.KeyboardMap;
import JGame.Util.Mapping;
import JGame.Util.MouseMap;
import JGame.Util.Time;
import JGame.Util.TimeRegulator;
import JGame.Util.Vector2;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Stage extends JFrame implements Runnable{

    protected Image bg;
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    protected int width = Game.width, height = Game.height;
    public static int CENTERX = 0, CENTERY = 0;
    private BufferedImage dbImage;
    private Graphics2D dbg;
    private static float gravity = 9.8f;
    public static long gameTime = 0;
    protected long startTime = System.nanoTime();
    protected final TimeRegulator secondsReg = new TimeRegulator(1000);
    protected long newTime = 0;
    public boolean running = true;
    public double delta = 0;
    private BufferStrategy bufferStrategy;
    public double fixedTimeStep = 0.02;
    protected boolean started = false;
    protected static boolean debug = false;

    public Stage(){
        super("Game");
        this.setSize(1024, 800);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public synchronized void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    /*
     * This runs the main thread for the current room.
     *
     * run() tests for key presses and Mouse Events, it then runs its set method
     * if a key press or mouse click is activated.
     */
    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60f;
        double ns = 1000000000 / amountOfTicks;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        long startTime = System.currentTimeMillis();
        while(running){
            long now = System.nanoTime();
            long milliNow = System.currentTimeMillis();
            delta += (now - lastTime) / ns;
            lastTime = now;
            Time.deltaTime = (delta / amountOfTicks) * Time.timeStep;
            Time.time = (milliNow - startTime) / 1000;
            while(delta >= 1){
                fixedTick();
                updates++;
                delta--;
            }
            tick();
            render();
            frames++;

            if(milliNow - timer > 1000){
                timer = milliNow;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    // Starts
    protected static void startComponents(GameObject gameObject){
        HashMap<Class, Component> hm = gameObject.getComponents();
        for(Map.Entry pairs : hm.entrySet()){
            Component comp = (Component)pairs.getValue();
            if(comp.gameObject == null){
                comp.setGameObject(gameObject);
            }
            comp.start();
        }
    }

    // Updates
    protected void tick(){
        for(GameObject go : this.gameObjects){
            HashMap<Class, Component> hm = go.getComponents();
            for(Map.Entry pairs : hm.entrySet()){
                Component comp = (Component)pairs.getValue();
                if(comp.gameObject == null){
                    comp.setGameObject(go);
                }
                comp.update();
            }
        }
    }

    // Fixed Update
    protected void fixedTick(){
        for(GameObject go : this.gameObjects){
            HashMap<Class, Component> hm = go.getComponents();
            for(Map.Entry pairs : hm.entrySet()){
                Component comp = (Component)pairs.getValue();
                if(comp.gameObject == null){
                    comp.setGameObject(go);
                }
                //System.out.println(comp.getClass().getName());
                if(comp instanceof Collider){
                    //((BoxCollider)comp).isCollision = false;
                    //this.testCollisions(go, (Collider)comp);
                }
                comp.fixedUpdate();
            }
        }
    }

    // Render the images
    protected void render(){
        bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            bufferStrategy = this.getBufferStrategy();
        }
        Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
        super.paint(g);
        for(GameObject go : gameObjects){
            Image sprite = go.getComponent(SpriteRenderer.class).getSprite();
            if(sprite == null){
                continue;
            }
            Vector2 pos = go.getComponent(Transform.class).getPosition();
            g.drawImage(sprite, (int)pos.x, (int)pos.y, this);
            Rectangle rect = go.getComponent(BoxCollider.class).getRect();
            if(rect != null && Stage.debug){
                SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
                g.setColor(Color.green);
                g.setStroke(new BasicStroke(1));
                g.drawRect(rect.x, rect.y, rect.width, rect.height);
                g.setColor(Color.blue);
                g.setStroke(new BasicStroke(3));
                g.drawOval(rect.x - 5 + (int)spr.getPivotPoint().x, rect.y + (int)spr.getPivotPoint().y - 5, 10, 10);
            }
        }
        g.dispose();
        bufferStrategy.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public static void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
        startComponents(gameObject);
    }

    public static void setDebug(boolean debug){
        Stage.debug = debug;
    }

    /*
     protected void updateGame(double delta){
     // Check for key press events
     this.keyboardEventTests();
     // Check for mouse events
     this.mouseEventTests();
     // Check for collision events
     this.collisionEventTests();
     // Check for custom events
     this.customEventTests();
     this.iterationEventTests();
     this.secondsEventTests();
     // Redraw all game objects
     for(GameObject go : gameObjects){
     // Move objects along X/Y axis
     this.moveXY(go, delta);
     // Test object for screen leaveable
     this.screenLeaveCheck(go);
     }
     // Remove all game objects that have been marked to be destroyed
     // this.removeMarked();
     }

     protected void secondsEventTests(){
     if(!this.secondsReg.checkTime()){
     return;
     }
     for(GameObject go : this.gameObjects){
     for(Map.Entry ap : go.siteration.getMap().entrySet()){
     Mapping mp = (Mapping)ap.getValue();
     mp.run();
     }
     }
     }

     protected void iterationEventTests(){
     for(GameObject go : this.gameObjects){
     for(Map.Entry ap : go.iteration.getMap().entrySet()){
     Mapping mp = (Mapping)ap.getValue();
     mp.run();
     }
     }
     }

     /**
     * Tests for keyboard events and performs action if one is found
     */
    protected void keyboardEventTests(){
        for(Map.Entry ap : KeyboardMap.map.entrySet()){
            Mapping mp = (Mapping)ap.getValue();
            if(mp.doing){
                mp.run();
            }
        }
    }

    /**
     * Tests for mouse events and preforms action if one is found
     */
    protected void mouseEventTests(){
        for(Map.Entry ap : MouseMap.map.entrySet()){
            Mapping mp = (Mapping)ap.getValue();
            if(mp.doing){
                mp.run();
            }
        }
    }

    /**
     * Tests for collision events and performs action if one is found
     *//*
     public void collisionEventTests(){
     for(GameObject go : this.gameObjects){
     Rectangle rect1 = new Rectangle(
     go.getX(),
     go.getY(),
     go.getWidth(),
     go.getHeight());
     for(GameObject go2 : this.gameObjects){
     Rectangle rect2 = new Rectangle(
     go2.getX(),
     go2.getY(),
     go2.getWidth(),
     go2.getHeight());
     String ref = go2.getReference();
     if(rect1.intersects(rect2)){
     for(Map.Entry ap : go.collide.getMap().entrySet()){
     String key = ap.getKey().toString();
     if(key.equals(ref)){
     Mapping mp = (Mapping)ap.getValue();
     go.setCollidedWith(go2);
     mp.run();
     }
     }
     }
     }
     }
     }
     */

    /**
     * Tests for custom events and performs action if one is found
     */
    /*protected void customEventTests(){
     for(Map.Entry ap : CustomEventMap.map.entrySet()){
     Mapping mp = (Mapping)ap.getValue();
     if(mp.doing){
     mp.run();
     CustomEventMap.map.remove(ap.getKey().toString());
     }
     }
     }*/

    /*protected void moveXY(GameObject go, double delta){
     // Move an object along the Y axis
     if(go.needsToMoveY){
     int objY = go.getY();
     if(objY > go.moveEndY && go.move.getMoveDirection().equals("UP")){
     go.setY((int)(objY - go.moveAmountY * delta));
     }else if(objY < go.moveEndY && go.move.getMoveDirection().equals("DOWN")){
     go.setY((int)(objY + go.moveAmountY * delta));
     }else{
     go.needsToMoveY = false;
     go.move.actionComplete();
     }
     }
     // Move an object along the X axis
     if(go.needsToMoveX){
     int objX = go.getX();
     if(objX > go.moveEndX && go.move.getMoveDirection().equals("LEFT")){
     go.setX((int)(objX - go.moveAmountX * delta));
     }else if(objX < go.moveEndX && go.move.getMoveDirection().equals("RIGHT")){
     go.setX((int)(objX + go.moveAmountX * delta));
     }else{
     go.needsToMoveX = false;
     go.move.actionComplete();
     }
     }
     }

     protected void screenLeaveCheck(GameObject go){
     if(!go.getLeaveScreen()){
     int goWidth = go.getWidth();
     int goHeight = go.getHeight();
     int goX = go.getX();
     int goY = go.getY();
     int gameWidth = Game.width;
     int gameHeight = Game.height;
     if(goX + goWidth >= gameWidth){
     go.setX(gameWidth - goWidth);
     }
     if(goX <= 0){
     go.setX(0);
     }
     if(goY + goHeight >= gameHeight){
     go.setY(gameHeight - goHeight);
     }
     if(goY <= 0){
     go.setY(0);
     }
     }
     }

     public void removeMarked(){
     Iterator objs = gameObjects.iterator();
     while(objs.hasNext()){
     GameObject go = (GameObject)objs.next();
     if(go.markedForDeletion){
     objs.remove();
     }
     }
     }
     */
    /**
     *
     * @param width
     * @param height
     *
     * Sets the size of a room.
     */
    public void setRoomSize(int width, int height){
        this.width = width;
        this.height = height;

        Stage.CENTERX = (int)(this.width / 2.0);
        Stage.CENTERY = (int)(this.height / 2.0);
    }

    /**
     *
     * @param color
     *
     * Sets a color to be the background a room
     */
    @Override
    public void setBackground(Color color){
        super.setBackground(color);
    }

    /**
     *
     * @param filename
     *
     * Sets an image to be the background of a room
     */
    public void setBackground(String filename){
        URL loc = this.getClass().getResource(filename);
        ImageIcon imgIcon = new ImageIcon(loc);
        bg = imgIcon.getImage();
    }

    /**
     *
     * @param gameObject
     * @param x
     * @param y
     *
     * Adds a GameObject to the room at a particular location
     */
    /*public void addGameObjectAt(GameObject gameObject, int x, int y){
     gameObject.setX(x);
     gameObject.setY(y);
     gameObjects.add(gameObject);
     }*/
    /**
     *
     * @param gameObject
     */
    /**
     *
     * @param go
     *
     * Removes a known GameObject from the room
     */
    public void removeGameObject(GameObject go){
        gameObjects.remove(go);
        //System.gc();
    }

    /*@Override
     public void paint(Graphics g){
     System.out.println("Painting 1");
     dbg = this.dbImage.createGraphics();
     //clear offscreen buffer image.
     dbg.fillRect(0, 0, this.getWidth(), this.getHeight());
     //render a new frame to offscreen buffer
     this.paintComponent(dbg);
     //render offscreen buffer to component
     g.drawImage(this.dbImage, 0, 0, this);
     dbg.dispose();
     }*/
    /**
     *
     * @param g
     *
     * Draws out all of the GameObjects in the room
     */
    /*@Override
     public void paintComponent(Graphics g){
     System.out.println("Painting 1");
     try{
     g.drawImage(bg, 0, 0, this);
     for(GameObject go : this.gameObjects){
     //GameObject go = gameObjects.get(i);
     BufferedImage sprite = go.getComponent(SpriteRenderer.class).getSprite();
     g.drawImage(sprite, 10, 10, this);
     }
     //this.repaint();
     }catch(Exception e){
     }
     }*/
    /**
     *
     * @return
     *
     * Gets the rooms width
     */
    public int getWidth(){
        return this.width;
    }

    /**
     *
     * @return
     *
     * Gets the rooms height
     */
    @Override
    public int getHeight(){
        return this.height;
    }

    public void setGravity(float gravity){
        Stage.gravity = gravity;
    }

    public static float getGravity(){
        return Stage.gravity;
    }

    public long getTickCount(){
        long now = System.nanoTime();
        long diffNanos = now - startTime;
        return TimeUnit.NANOSECONDS.toMillis(diffNanos);
    }
}
