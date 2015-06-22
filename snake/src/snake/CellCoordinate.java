package snake;

public class CellCoordinate
{
    
    static int MAX_SIZE = Grid.GRID_WIDTH;
    private static int CELL_SIZE = 30;
    
    private int x;
    private int y;
    
    public CellCoordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
        wrapAround();
    }
    
    private void wrapAround()
    {
        while (x < 0)
        {
            x += MAX_SIZE;
        }
        
        while (x >= MAX_SIZE)
        {
            x -= MAX_SIZE;
        }
        
        while (y < 0)
        {
            y += MAX_SIZE;
        }
        
        while (y >= MAX_SIZE)
        {
            y -= MAX_SIZE;
        }
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getXGlobal()
    {
        return x * CELL_SIZE;
    }
    
    public int getYGlobal()
    {
        return y * CELL_SIZE;
    }
    
    public void setX(int x)
    {
        this.x = x;
        wrapAround();
    }
    
    public void setY(int y)
    {
        this.y = y;
        wrapAround();
    }
    
    public void setCoordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
        wrapAround();
    }

}
