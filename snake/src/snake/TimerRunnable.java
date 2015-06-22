package snake;

public class TimerRunnable implements Runnable
{

    private long start;
    private long next;
    private Board board;
    private long DELAY = 250;
    
    public TimerRunnable(Board board)
    {
        this.board = board;
        this.start = System.currentTimeMillis();
        this.next = this.start + DELAY;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
            if (System.currentTimeMillis() > next)
            {
                next += DELAY;
                board.actionPerformed(null);
            }
        }
    }
    
    

}
