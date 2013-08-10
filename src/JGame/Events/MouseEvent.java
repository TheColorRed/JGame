package JGame.Events;

import JGame.Room.Room;
import JGame.Util.MouseMap;
import java.awt.event.MouseListener;

public class MouseEvent{

    Room comp;
    int clickCount = 1;

    /**
     *
     * @param comp
     */
    public MouseEvent(Room comp){
        this.comp = comp;
    }

    /**
     *
     * @param button
     * @param func
     *
     * Sets up click events
     */
    public void click(String button, Runnable func){
        this.clickCount = 1;
        onClick(button, func);
    }

    public void click(String button, int clickCount, Runnable func){
        this.clickCount = clickCount;
        onClick(button, func);
    }

    protected void onClick(String button, final Runnable func){
        int btn = 1;
        switch(button){
            case "LEFT":
                btn = 1;
                break;
            case "MIDDLE":
                btn = 2;
                break;
            case "RIGHT":
                btn = 3;
                break;
        }
        final int btnInt = btn;
        final int cCount = this.clickCount;
        comp.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                int clickCount = e.getClickCount();
                if(e.getButton() == btnInt && clickCount == cCount && MouseEvent.this.clickCount >= 1){
                    MouseMap.set(btnInt, true, func);
                    System.out.println("Click");
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e){
                if(e.getButton() == btnInt && MouseEvent.this.clickCount < 1){
                    MouseMap.set(btnInt, true, func);
                    System.out.println("Press");
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e){
                if(e.getButton() == btnInt){
                    MouseEvent.this.clickCount = -1;
                    MouseMap.set(btnInt, false, func);
                    System.out.println("Release");
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
            }
        });
    }
}
