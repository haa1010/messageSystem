package graph;
import java.util.Date;
import java.util.TimerTask;
public class Task extends TimerTask{
    @Override
    public void run(){
        Database.updateGraph();
    }
}