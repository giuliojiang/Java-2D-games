package snake;

import java.awt.Image;

/**
 * @author Giulio Jiang
 * 
 * represents a cell
 *
 */
public class Cell
{
    
    private CellType type;
    private CellImages images;
    
    public Cell(CellType type)
    {
        this.type = type;
        this.images = CellImages.getInstance();
    }
    
    public CellType getType()
    {
        return type;
    }
    
    public void setType(CellType type)
    {
        this.type = type;
    }
    
    public Image getImage()
    {
        return images.getImage(type);
    }

}
