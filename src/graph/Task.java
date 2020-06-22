package graph;
import java.util.Date;
import java.util.TimerTask;

/**
 * This is class for setting update graph function with timer
 */
public class Task extends TimerTask{
    @Override
    /**
     * This function is used to run the update graph function
     */
    public void run(){
        Database.updateGraph();
    }
}