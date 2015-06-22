package snake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 * @author Giulio Jiang welcome menu class
 */
public class Menu
{

    private Image title;
    private Image selecting;
    private Image speedSelected;
    private Image speedSelecting;
    private Board board;
    private int speed;

    private MenuEntry current;
    private MenuEntry start;
    private MenuEntry speed1;
    private MenuEntry speed2;
    private MenuEntry speed3;
    private MenuEntry speed4;
    private MenuEntry quit;

    public Menu(Board board)
    {
        initMenu(board);
    }

    private void initMenu(Board board)
    {
        this.board = board;

        ImageIcon ii = new ImageIcon(getClass().getResource(
                "/resource/title.png"));
        title = ii.getImage();

        ii = new ImageIcon(getClass().getResource("/resource/selecting.png"));
        selecting = ii.getImage();

        ii = new ImageIcon(getClass().getResource(
                "/resource/speed_selected.png"));
        speedSelected = ii.getImage();

        ii = new ImageIcon(getClass().getResource(
                "/resource/speed_selecting.png"));
        speedSelecting = ii.getImage();

        speed = 1;

        start = new MenuEntry();
        speed1 = new MenuEntry();
        speed2 = new MenuEntry();
        speed3 = new MenuEntry();
        speed4 = new MenuEntry();
        quit = new MenuEntry();

        current = start;

        start.down = speed1;

        speed1.up = start;
        speed1.down = quit;
        speed1.right = speed2;

        speed2.up = start;
        speed2.down = quit;
        speed2.left = speed1;
        speed2.right = speed3;

        speed3.up = start;
        speed3.down = quit;
        speed3.left = speed2;
        speed3.right = speed4;

        speed4.up = start;
        speed4.down = quit;
        speed4.left = speed3;
        speed4.right = speed4;

        quit.up = speed1;

    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            current = current.left;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            current = current.right;
        }

        if (key == KeyEvent.VK_UP)
        {
            current = current.up;
        }

        if (key == KeyEvent.VK_DOWN)
        {
            current = current.down;
        }

        if (key == KeyEvent.VK_ENTER)
        {
            handleEnter();
        }
    }

    private void handleEnter()
    {
        if (current == start)
        {
            board.startGame(getDelay());
        }

        if (current == speed1)
        {
            speed = 1;
        }

        if (current == speed2)
        {
            speed = 2;
        }

        if (current == speed3)
        {
            speed = 3;
        }

        if (current == speed4)
        {
            speed = 4;
        }

        if (current == quit)
        {
            System.exit(0);
        }
    }

    public long getDelay()
    {
        if (speed == 1)
        {
            return 200;
        }

        if (speed == 2)
        {
            return 125;
        }

        if (speed == 3)
        {
            return 80;
        }

        if (speed == 4)
        {
            return 30;
        }

        return 200;
    }

    public void drawMenu(Graphics g)
    {
        // draw selected speed
        if (speed == 1)
        {
            g.drawImage(speedSelected, 145, 414, board);
        }
        if (speed == 2)
        {
            g.drawImage(speedSelected, 235, 414, board);
        }
        if (speed == 3)
        {
            g.drawImage(speedSelected, 319, 414, board);
        }
        if (speed == 4)
        {
            g.drawImage(speedSelected, 412, 414, board);
        }

        // draw current selection
        if (current == start)
        {
            g.drawImage(selecting, 188, 228, board);
        }
        if (current == speed1)
        {
            g.drawImage(speedSelecting, 145, 414, board);
        }
        if (current == speed2)
        {
            g.drawImage(speedSelecting, 235, 414, board);
        }
        if (current == speed3)
        {
            g.drawImage(speedSelecting, 319, 414, board);
        }
        if (current == speed4)
        {
            g.drawImage(speedSelecting, 412, 414, board);
        }
        if (current == quit)
        {
            g.drawImage(selecting, 187, 499, board);
        }

        // draw graphics
        g.drawImage(title, 0, 0, board);

    }

    private class MenuEntry
    {
        MenuEntry up;
        MenuEntry down;
        MenuEntry left;
        MenuEntry right;

        MenuEntry()
        {
            up = this;
            down = this;
            left = this;
            right = this;
        }
    }

}
