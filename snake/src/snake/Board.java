package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

/**
 * @author Giulio Jiang
 *
 * represents the game board and game logic
 */
public class Board extends JPanel implements ActionListener
{

    private int cellSize;
    private Grid cells;
    private Snake snake;
    private Menu menu;
    private boolean inGame;
    private boolean inMenu;
    private Random rnd;
    TimerRunnable timer;
    Thread timerThread;

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        // calculate cell size
        cellSize = CellImages.getInstance().getImage(CellType.EMPTY)
                .getWidth(null);

        // initialize cells
        cells = new Grid();

        // initialize snake
        snake = new Snake(cells, this);

        // add key listener
        addKeyListener(new TAdapter());

        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(cellSize * Grid.GRID_WIDTH, cellSize
                * Grid.GRID_HEIGHT));

        inGame = false;
        inMenu = true;

        // initialize random
        rnd = new Random();

        // initialize menu
        menu = new Menu(this);

        // start timer
        timer = new TimerRunnable(this);
        timerThread = new Thread(new TimerRunnable(this));
        timerThread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!inMenu)
        {

            // check alive
            if (!snake.isAlive())
            {
                inGame = false;
            }

            // move snake
            if (inGame)
            {
                snake.move();
                addApple();
            }

            // draw
            repaint();
        } else
        {
            repaint();
        }
    }

    private void addApple()
    {
        if (rnd.nextInt(12) == 0)
        {
            addNewApple();
        }
    }

    private void addNewApple()
    {
        if (cells.countApples() > 2)
        {
            return;
        }

        int x = rnd.nextInt(Grid.GRID_WIDTH);
        int y = rnd.nextInt(Grid.GRID_HEIGHT);

        if (cells.getCellType(x, y) == CellType.SNAKE)
        {
            return;
        }

        cells.setCell(x, y, CellType.APPLE);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (!inMenu)
        {

            for (int i = 0; i < Grid.GRID_WIDTH; i++)
            {
                for (int j = 0; j < Grid.GRID_HEIGHT; j++)
                {
                    CellCoordinate coord = new CellCoordinate(i, j);
                    Cell c = cells.getCell(coord);
                    g.drawImage(c.getImage(), coord.getXGlobal(),
                            coord.getYGlobal(), this);
                }
            }

            paintScore(g);

            if (!inGame)
            {
                snake.drawFinalScreen(g);
                g.drawImage(CellImages.getInstance().getGameOver(), 0, 0, this);
            }
        } else
        {
            menu.drawMenu(g);
        }

    }

    private void paintScore(Graphics g)
    {
        g.setFont(new Font("Sans", Font.PLAIN, 13));
        g.drawString("Score: " + snake.getScore(), 20, 20);
    }

    public void startGame(long delay)
    {
        timerThread.interrupt();
        try
        {
            timerThread.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        timer.setDelay(delay);
        inMenu = false;
        inGame = true;
        
        timerThread = new Thread(timer);
        timerThread.start();

    }

    public void restartGame()
    {
        timerThread.interrupt();
        try
        {
            timerThread.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        // go back to menu
        inMenu = true;
        inGame = false;
        
        timerThread = new Thread(timer);
        timerThread.start();
        
    }

    private class TAdapter extends KeyAdapter
    {

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (inMenu)
            {
                menu.keyPressed(e);
            } else
            {
                snake.keyPressed(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            // null

        }

    }

}
