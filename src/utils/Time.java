package utils;

import java.util.Vector;

/**
 * Time.java.
 * Created on 1-Mar-2011, 1:16:01AM.
 */
public class Time {

    /**
     * responsible time managing on sphinx.
     * @author Ahmed Gahenm.
     * class Methods:-
     * 1- setter and getter methods for all fields.
     * 2 - addSecond(int second)=> to add seconds to a time and get the result as String.
     */
    private int hour;
    private int minutes;
    private int seconds;

    public Time() {
        hour = minutes = seconds = 0;
    }

    public Time(int h, int m, int s) {
        this.hour = h;
        this.minutes = m;
        this.seconds = s;
    }

    public Time(String time) {
        String[] times = new String[3];
        times = timeSplit(convertToTime(time));
        this.hour = Integer.parseInt(times[0]);
        this.minutes = Integer.parseInt(times[1]);
        this.seconds = Integer.parseInt(times[2]);
    }

    /**
     * 
     * @param time is an array of custom sphinx time
     */
    public Time(String[] time) {
        this.hour = Integer.parseInt(time[0]);
        this.minutes = Integer.parseInt(time[1]);
        this.seconds = Integer.parseInt(time[2]);
    }

    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param minutes the minutes to set
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * @param seconds the seconds to set
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * 
     * @param second seconds to add to the current time
     * @return the total time as string
     */
    public String addSecond(int second) {
        this.seconds = this.seconds + second;
        if (this.seconds >= 60) {
            ++this.minutes;
            this.seconds = 0;
            if (this.minutes >= 60) {
                ++this.hour;
                this.minutes = 0;
            }
        }
        return hour + ":" + minutes + ":" + seconds;
    }

    public String subSecond(int second) {
        this.seconds = this.seconds - second;
        if (this.seconds <= 0) {
            --this.minutes;
            this.seconds = 59;

        }
        return hour + ":" + minutes + ":" + seconds;
    }

    @Override
    public String toString() {
        return this.hour + ":" + this.minutes + ":" + this.seconds;
    }

    /** 
     *@param time String as custom sphinx time
     * @return it's only convert string to custom time
     */
    public Time convertToTime(String time) {
        return new Time(time.split(":"));
    }

    /**
     * 
     * @param time custom sphinx time
     * @return  array of strings as [hh:mm:ss]
     */
    public String[] timeSplit(Time time) {
        String[] args = time.toString().split(":");
        return args;
    }

    /**
     * 
     * @param times
     * @return 
     */
    public Time addTimes(String time1, String time2) {
        Time totalTime = null;
        Time t1 = convertToTime(time1);
        Time t2 = convertToTime(time2);
        totalTime = new Time();
        totalTime.hour = t1.hour + t2.hour;
        totalTime.minutes = t1.minutes + t2.minutes;
        totalTime.seconds = t1.seconds + t2.seconds;
        while (totalTime.seconds >= 60) {
            ++totalTime.minutes;
            totalTime.seconds = 0;
            while (totalTime.minutes >= 60) {
                ++totalTime.hour;
                totalTime.minutes = 0;
            }
        }
        return totalTime;
    }

    public String addMoreTimes(Vector<String> times) {
        Time temp = null;
        temp = new Time();
        for (int i = 0; i < times.size(); i++) {
            temp = addTimes(temp.toString(), times.get(i).toString());
        }
        return temp.toString();
    }

    public double gettimeValue(String time) {
        Time t = convertToTime(time);
        String timeValue = "" + t.hour + "." + t.minutes + "" + t.seconds;
        double timeV = Double.parseDouble(timeValue);
        return timeV;
    }
}
