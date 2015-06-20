package sprite;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Craft
{
    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;

    public Craft()
    {
        initCraft();
    }

    private void initCraft()
    {
        ImageIcon ii = new ImageIcon(getClass().getResource(
                "/resource/craft.png"));
        image = ii.getImage();
        x = 40;
        y = 60;
    }
    
    public void move()
    {
        x += dx;
        y += dy;
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
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
        {
            dx = -1;
        }
        
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
        {
            dx = 1;
        }
        
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
        {
            dy = -1;
        }
        
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
        {
            dy = 1;
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
        {
            dx = 0;
        }
        
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
        {
            dx = 0;
        }
        
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
        {
            dy = 0;
        }
        
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
        {
            dy = 0;
        }
    }

}
