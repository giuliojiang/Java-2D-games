package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private Timer timer;
    private Craft craft;
    private final int DELAY = 10;

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        
        ArrayList<Missile> ms = craft.getMissiles();
        
        for (Missile m : ms)
        {
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        updateMissiles();
        updateCraft();
        repaint();
    }
    
    private void updateMissiles()
    {
        ArrayList<Missile> ms = craft.getMissiles();
        HashSet<Missile> msr = new HashSet<Missile>();
        
        for (Missile m : ms)
        {
            if (m.isVisible())
            {
                m.move();
            } else
            {
                msr.add(m);
            }
        }
        
        for (Missile m : msr)
        {
            ms.remove(m);
        }
    }
    
    private void updateCraft()
    {
        craft.move();
    }

    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            craft.keyPressed(e);
        }
    }

}
