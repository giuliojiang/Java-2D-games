package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{

    private int cellSize;
    private Grid cells;
    private Snake snake;
    private Timer timer;
    private int DELAY = 180;
    private boolean inGame;
    private Random rnd;

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
        snake = new Snake(cells);

        // add key listener
        addKeyListener(new TAdapter());

        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(cellSize * Grid.GRID_WIDTH, cellSize
                * Grid.GRID_HEIGHT));

        inGame = true;

        // initialize random
        rnd = new Random();

        // start timer
        Thread timerThread = new Thread(new TimerRunnable(this));
        timerThread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
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
        if (!inGame)
        {
            g.drawImage(CellImages.getInstance().getGameOver(), 0, 0, this);
        }

    }

    private class TAdapter extends KeyAdapter
    {

        @Override
        public void keyPressed(KeyEvent e)
        {
            snake.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            // null

        }

    }

}
