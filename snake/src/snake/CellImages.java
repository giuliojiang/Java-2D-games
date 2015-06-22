package snake;

import java.awt.Image;

import javax.swing.ImageIcon;

public class CellImages
{

    private Image emptyImage;
    private Image snakeImage;
    private Image appleImage;
    private Image gameOverImage;

    private static CellImages instance;

    private CellImages()
    {
        // load empty cell image
        ImageIcon ii = new ImageIcon(getClass().getResource(
                "/resource/empty.png"));
        this.emptyImage = ii.getImage();

        // load snake image
        ii = new ImageIcon(getClass().getResource("/resource/snake.png"));
        this.snakeImage = ii.getImage();

        // load apple image
        ii = new ImageIcon(getClass().getResource("/resource/apple.png"));
        this.appleImage = ii.getImage();
        
        // load game over image
        ii = new ImageIcon(getClass().getResource("/resource/gameover.png"));
        this.gameOverImage = ii.getImage();
    }

    public static CellImages getInstance()
    {
        if (instance == null)
        {
            instance = new CellImages();
        }

        return instance;
    }
    
    public Image getImage(CellType type)
    {
        if (type == CellType.EMPTY)
        {
            return emptyImage;
        }
        
        if (type == CellType.SNAKE)
        {
            return snakeImage;
        }
        
        if (type == CellType.APPLE)
        {
            return appleImage;
        }
        
        // impossible
        return null;
    }
    
    public Image getGameOver()
    {
        return gameOverImage;
    }

}
