package collision;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * @author Giulio Jiang
 * 
 * tutorial from http://zetcode.com/tutorials/javagamestutorial/collision/
 *
 */
public class Board extends JPanel implements ActionListener
{

    private Timer timer;
    private Craft craft;
    private ArrayList<Alien> aliens;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int DELAY = 15;

    private final int[][] pos = { { 2380, 29 }, { 2500, 59 }, { 1380, 89 },
            { 780, 109 }, { 580, 139 }, { 680, 239 }, { 790, 259 },
            { 760, 50 }, { 790, 150 }, { 980, 209 }, { 560, 45 }, { 510, 70 },
            { 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
            { 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
            { 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 } };

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens()
    {
        aliens = new ArrayList<Alien>();

        for (int[] p : pos)
        {
            aliens.add(new Alien(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (ingame)
        {
            drawObjects(g);
        } else
        {
            drawGameOver(g);
        }
    }

    private void drawObjects(Graphics g)
    {
        if (craft.isVisible())
        {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms)
        {
            if (m.isVisible())
            {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        for (Alien a : aliens)
        {
            if (a.isVisible())
            {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        g.setColor(Color.GRAY);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics g)
    {
        String msg = "Game Over";
        Font small = new Font("Sans", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.GRAY);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        inGame();
        
        updateCraft();
        updateMissiles();
        updateAliens();
        
        checkCollisions();
        
        repaint();
    }
    
    private void inGame()
    {
        if (!ingame)
        {
            timer.stop();
        }
    }
    
    private void updateCraft()
    {
        if (craft.isVisible())
        {
            craft.move();
        }
    }
    
    private void updateMissiles()
    {
        ArrayList<Missile> ms = craft.getMissiles();
        Set<Missile> rms = new HashSet<Missile>();
        
        for (Missile m : ms)
        {
            if (m.isVisible())
            {
                m.move();
            } else
            {
                rms.add(m);
            }
        }
        
        for (Missile m : rms)
        {
            ms.remove(m);
        }
    }
    
    private void updateAliens()
    {
        if (aliens.isEmpty())
        {
            ingame = false;
            return;
        }
        
        ArrayList<Alien> as = aliens;
        Set<Alien> ras = new HashSet<Alien>();
        
        for (Alien a : as)
        {
            if (a.isVisible())
            {
                a.move();
            } else
            {
                ras.add(a);
            }
        }
        
        for (Alien a : ras)
        {
            as.remove(a);
        }
    }
    
    public void checkCollisions()
    {
        Rectangle r3 = craft.getBounds();
        
        for (Alien alien : aliens)
        {
            Rectangle r2 = alien.getBounds();
            
            // when alien smashes the ship
            if (r3.intersects(r2)) 
            {
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }
        
        ArrayList<Missile> ms = craft.getMissiles();
        
        for (Missile m : ms)
        {
            Rectangle r1 = m.getBounds();
            for (Alien alien : aliens)
            {
                Rectangle r2 = alien.getBounds();
                
                // when missile hits alien
                if (r1.intersects(r2))
                {
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
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
