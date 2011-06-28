package tasks;

/**
 * ProjectManager.java.
 * Created on 1-Mar-2011, 4:16:01AM.
 */
import com.nitido.utils.toaster.Toaster;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import manager.MusicManager;
import utils.Time;

/**
 * responsible for manage the projects durations.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- guiComponents(JLabel l) => duration time.
 * 2- startTime(int delay)=> start duration increase with delay.
 * 3- stopTime() => stop the theard timer.
 * @see Timer
 * @see Thread
 */
public class StartTask {

    private JLabel startLabel;
    private JLabel restLabel;
    private JLabel sesstionCount;
    private Time time;
    private Timer timer;
    private MusicManager music;
    public static boolean restFlag = true;
    private int count = 0;
    private int timeCounter = 0;
    private String timeTemp;
    private Toaster toaster;
    private ImageIcon toastIcon;

    public StartTask() {
        toaster = new Toaster();
        toaster.setBorderColor(Color.WHITE);
        toaster.setToasterColor(Color.WHITE);
        toaster.setDisplayTime(6000);
    }

    public void guiComponents(JLabel l) {
        this.startLabel = l;
    }

    public void guiComponents(JLabel l, JLabel rL, JLabel sC) {
        this.startLabel = l;
        this.restLabel = rL;
        this.sesstionCount = sC;
    }

    public void startTime(int delay) {
        time = new Time();
        timer = new Timer(delay, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                startLabel.setText(time.addSecond(1));
            }
        });
        timer.start();

    }
    // 1000              0:2:0                      asd              0:1:0

    public void startAlarmTime(final int delay, final String startTime, final String tone, final String restTime) {
        if (timeCounter == 0) {
            timeTemp = startTime;
        }
        timeCounter = 1;
        time = new Time(startTime);
        timer = new Timer(delay, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                startLabel.setText(time.subSecond(1));
                if (startLabel.getText().equals("0:-1:59")) {
                    startLabel.setText("--:--:--");
                    stopTime();
                    music = new MusicManager("data/" + tone + ".au");
                    music.start();
                    if (restFlag == false) {
                        restLabel.setText("");
                        startAlarmTime(delay, timeTemp, tone, restTime);
                        restFlag = true;
                        sesstionCount.setText("" + (++count));
                        showToaster(0);

                    } else {
                        startAlarmTime(delay, restTime, tone, restTime);
                        restLabel.setText("Break");
                        restFlag = false;
                        showToaster(1);

                    }

                }
            }
        });
        timer.start();

    }

    public void stopTime() {
        timer.stop();
    }

    public void showToaster(int state) {
        toastIcon = null;
        if (state == 0) {
            toastIcon = new ImageIcon(getClass().getResource("/images/work.png"));
            toaster.showToaster(toastIcon, "Work Time ...");
        } else {
            toastIcon = new ImageIcon(getClass().getResource("/images/rest.png"));
            toaster.showToaster(toastIcon, "Rest Time ...");
        }
    }
}
