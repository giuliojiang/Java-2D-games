package snake;

public class Grid
{

    public static int GRID_WIDTH = 20;
    public static int GRID_HEIGHT = GRID_WIDTH;
    
    private Cell[][] cells;
    
    public Grid()
    {
        cells = new Cell[GRID_WIDTH][GRID_HEIGHT];
        for (int i = 0; i < GRID_WIDTH; i++)
        {
            for (int j = 0; j < GRID_HEIGHT; j++)
            {
                cells[i][j] = new Cell(CellType.EMPTY);
            }
        }
    }
    
    public Cell getCell(int x, int y)
    {
        CellCoordinate c = new CellCoordinate(x, y);
        return getCell(c);
    }
    
    public Cell getCell(CellCoordinate c)
    {
        return cells[c.getX()][c.getY()];
    }
    
    public CellType getCellType(int x, int y)
    {
        return getCell(x, y).getType();
    }
    
    public CellType getCellType(CellCoordinate c)
    {
        return getCell(c).getType();
    }
    
    public void setCell(int x, int y, CellType t)
    {
        CellCoordinate c = new CellCoordinate(x, y);
        setCell(c, t);
    }
    
    public void setCell(CellCoordinate c, CellType t)
    {
        getCell(c).setType(t);
    }
    
    public int countApples()
    {
        int n = 0;
        for (int i = 0; i < GRID_WIDTH; i++)
        {
            for (int j = 0; j < GRID_HEIGHT; j++)
            {
                if (cells[i][j].getType() == CellType.APPLE)
                {
                    n++;
                }
            }
        }
        return n;
    }
    
}
