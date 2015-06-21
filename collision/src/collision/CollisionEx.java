package collision;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author Giulio Jiang
 * 
 * tutorial from http://zetcode.com/tutorials/javagamestutorial/collision/
 *
 */
public class CollisionEx extends JFrame
{
    
    public CollisionEx()
    {
        initUI();
    }
    
    private void initUI()
    {
        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Collision");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                CollisionEx ex = new CollisionEx();
                ex.setVisible(true);
            }
            
        });
    }

}
