package snake;

/**
 * @author Giulio Jiang
 * time scheduler thread
 */
public class TimerRunnable implements Runnable
{

    private long start;
    private long next;
    private Board board;
    private long delay;

    public TimerRunnable(Board board)
    {
        this.board = board;
        this.start = System.currentTimeMillis();
        this.delay = 2;
        this.next = this.start + delay;
    }

    @Override
    public void run()
    {
        start = System.currentTimeMillis();
        next = start + delay;
        while (true)
        {
            if (Thread.currentThread().isInterrupted())
            {
                return;
            }

            if (System.currentTimeMillis() > next)
            {
                next += delay;
                board.actionPerformed(null);
            }
        }
    }

    public void setDelay(long delay)
    {
        this.delay = delay;
    }

}
