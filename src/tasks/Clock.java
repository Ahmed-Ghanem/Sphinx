package tasks;
/**
 * Clock.java.
 * Created on 1-Mar-2011, 1:16:01AM.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * responsible show the time om sphinx.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- guiComponents(JLabel l) => set the clock label on main frame.
 * 2- startClock(int delay)=> execute this action every delay.
 * @see Timer
 * @see Thread
 */
public class Clock {

    private JLabel clock;
    private Timer timer;
    private Date date;
    private SimpleDateFormat dateFormat;

    public Clock() {
        dateFormat = new SimpleDateFormat("hh:mm:ss");
    }

    public void guiComponents(JLabel l) {
        this.clock = l;
    }
    public void startClock(int delay) {

        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                date = new Date();
                String time = dateFormat.format(date);
                clock.setText(time+" ");
            }
        });
        timer.start();
        
    }
}
