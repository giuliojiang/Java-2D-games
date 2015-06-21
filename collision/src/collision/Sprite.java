package collision;

import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.swing.ImageIcon;


/**
 * @author Giulio Jiang
 * 
 * tutorial from http://zetcode.com/tutorials/javagamestutorial/collision/
 *
 */
public class Sprite
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
    
    public Sprite(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.vis = true;
    }
    
    protected void getImageDimensions()
    {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    protected void loadImage(URL imageName)
    {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public boolean isVisible()
    {
        return vis;
    }
    
    public void setVisible(boolean visible)
    {
        vis = visible;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }

}
