package sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{

    private Timer timer;
    private Craft craft;
    private final int DELAY = 2;
    
    public Board()
    {
        initBoard();
    }
    
    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        
        craft = new Craft();
        
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
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        craft.move();
        repaint();
    }
    
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e){
            craft.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            craft.keyPressed(e);
        }
    }
    
}
