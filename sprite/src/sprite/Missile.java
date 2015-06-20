package sprite;

public class Missile extends Sprite
{
    
    private final int BOARD_WIDTH = 800;
    private final int MISSILE_SPEED = 2;
    
    public Missile(int x, int y)
    {
        super(x, y);
        initMissile();
    }
    
    private void initMissile()
    {
        loadImage(getClass().getResource("/resource/bullet.png"));
        getImageDimensions();
    }
    
    public void move()
    {
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH)
        {
            vis = false;
        }
    }

}
