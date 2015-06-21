package collision;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * @author Giulio Jiang
 * 
 * tutorial from http://zetcode.com/tutorials/javagamestutorial/collision/
 *
 */
public class Craft extends Sprite
{
    
    private int dx;
    private int dy;
    private ArrayList<Missile> missiles;
    
    public Craft(int x, int y)
    {
        super(x, y);
        initCraft();
    }
    
    private void initCraft()
    {
        missiles = new ArrayList<Missile>();
        loadImage(getClass().getResource("/resource/craft.png"));
        getImageDimensions();
    }
    
    public void move()
    {
        x += dx;
        y += dy;
        
        if (x < 1)
        {
            x = 1;
        }
        
        if (y < 1)
        {
            y = 1;
        }
    }
    
    public ArrayList<Missile> getMissiles()
    {
        return missiles;
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
        
        if (key == KeyEvent.VK_SPACE)
        {
            fire();
        }
    }
    
    public void fire()
    {
        missiles.add(new Missile(x + width, y + height / 2));
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
        {
            if (dx == -1)
            {
                dx = 0;
            }
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
        {
            if (dx == 1)
            {
                dx = 0;
            }
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
        {
            if (dy == -1)
            {
                dy = 0;
            }
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
        {
            if (dy == 1)
            {
                dy = 0;
            }
        }
    }

}
