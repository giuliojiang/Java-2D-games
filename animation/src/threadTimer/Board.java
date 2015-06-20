package threadTimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Giulio Jiang
 * 
 * tutorial from http://zetcode.com/tutorials/javagamestutorial/animation/
 *
 */
public class Board extends JPanel implements Runnable
{
    private final int B_WIDTH = 1900;
    private final int B_HEIGHT = 1000;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;
    private final double SPEED = B_HEIGHT / 2000d;

    private Image star;
    private Thread animator;
    private int x, y;
    private long startTime;
    private long currentTime;
    private long paintedTimes;
    private long fpsTimeStart;

    public Board()
    {
        initBoard();
    }

    private void loadImage()
    {
        ImageIcon ii = new ImageIcon(getClass().getResource(
                "/resource/star.png"));
        star = ii.getImage();
    }

    private void initBoard()
    {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;
    }

    @Override
    public void addNotify()
    {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawStar(g);
    }

    private void drawStar(Graphics g)
    {
        g.drawImage(star, x, y, this);
    }

    /** using a space-time equation, it calculates
     * how much time has passed, and calculates the new
     * position. This does not depend on any
     * sleep time calculation.
     * 
     */
    private void cycle()
    {
        x = getSpace(currentTime - startTime);
        y = x;
        paintedTimes++;
    }

    int getSpace(long time)
    {
        double space = SPEED * time;
        return ((int) space) % B_HEIGHT;
    }

    @Override
    public void run()
    {
        long beforeTime, sleep;

        beforeTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        fpsTimeStart = System.currentTimeMillis();

        while (true)
        {
            currentTime = System.currentTimeMillis();
            // System.out.println(currentTime);
            cycle();
            repaint();
            if (paintedTimes % 1000000 == 0)
            {
                try
                {
                    long current = System.currentTimeMillis();
                    double timeDiff = current - fpsTimeStart;
                    double fps = ((double) paintedTimes) / (timeDiff / 1000);
                    System.out.println("FPS = " + fps);
                } catch (ArithmeticException e)
                {
                }
                fpsTimeStart = System.currentTimeMillis();
                paintedTimes = 0;
            }
        }
    }

}
