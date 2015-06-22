package snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Snake
{

    private Direction direction;
    private Direction newDirection;
    // queue to store snake points. add to the beginning, remove from the end
    private Deque<CellCoordinate> snake;
    private boolean alive;
    private boolean eatingApple;
    private Grid cells;

    public Snake(Grid cells)
    {
        this.direction = Direction.UP;
        this.newDirection = direction;
        this.snake = new LinkedList<CellCoordinate>();
        this.alive = true;
        this.eatingApple = false;
        this.cells = cells;
        
        // initialize first snake 2 dots
        snake.addFirst(new CellCoordinate(10,10));
        cells.setCell(10, 10, CellType.SNAKE);
        snake.addFirst(new CellCoordinate(10,9));
        cells.setCell(10, 9, CellType.SNAKE);
    }

    public void drawSnake(Graphics g, JPanel obs)
    {
        for (CellCoordinate c : snake)
        {
            g.drawImage(CellImages.getInstance().getImage(CellType.SNAKE),
                    c.getXGlobal(), c.getYGlobal(), obs);
        }
    }
    
    public boolean isAlive()
    {
        return alive;
    }
    
    public void move()
    {
        // update new direction
        direction = newDirection;
        
        // get old head
        CellCoordinate head = snake.getFirst();
        int x = head.getX();
        int y = head.getY();
        
        // calculate new head position
        if (direction == Direction.UP)
        {
            y -= 1;
        } 
        if (direction == Direction.DOWN)
        {
            y += 1;
        } 
        if (direction == Direction.RIGHT)
        {
            x += 1;
        }
        if (direction == Direction.LEFT)
        {
            x -= 1;
        }
        CellCoordinate newHead = new CellCoordinate(x, y);
        
        // check for conflicts in new position
        if (cells.getCellType(newHead) == CellType.SNAKE)
        {
            System.out.println("The snake has bitten itself.");
            alive = false;
            return;
        }
        
        // check for apples in new position
        boolean hasJustEaten = false;
        if (cells.getCellType(newHead) == CellType.APPLE)
        {
            hasJustEaten = true;
        }
        
        // add new head position to queue and to grid
        snake.addFirst(newHead);
        cells.setCell(newHead, CellType.SNAKE);
        
        // remove tail if not eating apple
        if (!eatingApple)
        {
            CellCoordinate last = snake.getLast();
            snake.removeLast();
            cells.setCell(last, CellType.EMPTY);
        }
        
        // set eatingApple to false
        eatingApple = false;
        
        // update eatingApple if snake has eaten now
        if (hasJustEaten)
        {
            eatingApple = true;
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP)
        {
            if (direction != Direction.DOWN)
            {
                newDirection = Direction.UP;
            }
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            if (direction != Direction.LEFT)
            {
                newDirection = Direction.RIGHT;
            }
        }

        if (key == KeyEvent.VK_DOWN)
        {
            if (direction != Direction.UP)
            {
                newDirection = Direction.DOWN;
            }
        }

        if (key == KeyEvent.VK_LEFT)
        {
            if (direction != Direction.RIGHT)
            {
                newDirection = Direction.LEFT;
            }
        }
    }

}
