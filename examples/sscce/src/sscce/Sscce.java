/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sscce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ryan
 */
public class Sscce{

    public Sscce(){
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel room = new Room();
        frame.add(room);

        frame.setVisible(true);

    }

    public static void main(String[] args){
        new Sscce();
    }
}

class Room extends JPanel implements Runnable{

    public Room(){
        /*BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        g.setColor(Color.red);
        g.fillRect(0, 0, 100, 100);
        g.dispose();*/
    }

    @Override
    public void run(){
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long lastFpsTime = 0;
        while(true){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            if(updateLength < OPTIMAL_TIME){
                continue;
            }
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            if(lastFpsTime >= 1000000000){
                lastFpsTime = 0;
            }

            this.updateGame(delta);

            try{
                long gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(gameTime);
            }catch(Exception e){
            }
            this.paintImmediately(0, 0, 800, 600);
        }
    }

    public void updateGame(double delta){
    }


    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(100, 100, 100, 100);
        g.dispose();
    }
}