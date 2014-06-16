/*package JGame.GameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ryan
 *//*
public class Sprite{

    protected BufferedImage sprite;

    public Sprite(){

    }

    public Sprite(String filename){
        URL loc = this.getClass().getResource(filename);
        ImageIcon imgIcon = new ImageIcon(loc);
        int width = imgIcon.getIconWidth();
        int height = imgIcon.getIconHeight();
        BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics bg = bimg.getGraphics();
        bg.drawImage(imgIcon.getImage(), 0, 0, null);
        bg.dispose();
        this.sprite = bimg;
    }

    public Sprite(String filename, int width){
        try{
            URL loc = this.getClass().getResource(filename);
            BufferedImage img = ImageIO.read(loc);
            float w = img.getWidth();
            float h = img.getHeight();
            int height = (int)(h * (width / w));
            Image imgScaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics bg = bimg.getGraphics();
            bg.drawImage(imgScaled, 0, 0, null);
            bg.dispose();
            this.sprite = bimg;
        }catch(Exception e){
        }
    }

    public Sprite(Font font, String text, Color color){
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D bounds = font.getStringBounds(text, frc);
        int w = (int)bounds.getWidth();
        int h = (int)bounds.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.BLACK);
        g.setFont(font);
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setRenderingHints(rh);

        g.drawString(text, (float)bounds.getX(), (float)-bounds.getY());
        g.dispose();
        this.sprite = image;
    }

    public int getWidth(){
        return this.sprite.getWidth(null);
    }

    public int getHeight(){
        return this.sprite.getHeight(null);
    }

    public void drawRect(int w, int h, Color color){
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, h, h);
        g.dispose();
        this.sprite = image;
    }

}
*/
