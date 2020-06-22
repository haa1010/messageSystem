package graph;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Minh Thong- 20176881
 * This is a class for daily scheduling to update graph and database in 4pm
 */
public class Update {
    public static void main(String args){
        Task task = new Task();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date dateSchedule = calendar.getTime();
        long period = 24*60*60*1000;

        Timer timer = new Timer();
        timer.schedule(task, dateSchedule, period);
    }
}
