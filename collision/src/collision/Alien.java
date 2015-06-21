package collision;

/**
 * @author Giulio Jiang
 * 
 * tutorial from http://zetcode.com/tutorials/javagamestutorial/collision/
 *
 */
public class Alien extends Sprite
{
    
    private final int INITIAL_X = 600;
    
    public Alien(int x, int y)
    {
        super(x, y);
        initAlien();
    }
    
    private void initAlien()
    {
        loadImage(getClass().getResource("/resource/alien.png"));
        getImageDimensions();
    }
    
    public void move()
    {
        if (x < 0)
        {
            x = INITIAL_X;
        }
        
        x -= 1;
    }

}
