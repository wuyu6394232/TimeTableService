package com.yg.timetableservice.util;

import java.util.Calendar;

public class TimeUtil implements Comparable<TimeUtil> {
    private int hour;
    private int minute;
    private int second;
    private int nextDayFlag = 0;
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getSecond() {
        return second;
    }
    public boolean ifNextDay() {
        return nextDayFlag >= 1;
    }
    public TimeUtil(Calendar calendar) {
        Calendar currentTime = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        if (calendar.get(Calendar.DAY_OF_YEAR) > currentTime.get(Calendar.DAY_OF_YEAR)) {
            nextDayFlag = 1;
        }
    }
    public TimeUtil(int hour, int minute, int second) {
        this.hour = hour >= 24 ? hour - 24 : hour;
        this.minute = minute;
        this.second = second;
        if (hour >= 24) nextDayFlag = 1;
    }
    public TimeUtil(String timeStr) {
        String[] time = timeStr.split(":");
        int hour = Integer.parseInt(time[0]);
        this.hour = hour >= 24 ? hour - 24 : hour;
        this.minute = Integer.parseInt(time[1]);
        this.second = Integer.parseInt(time[2]);
        if (hour >= 24) nextDayFlag = 1;
    }
     public Calendar toCalendar() {
        Calendar result = Calendar.getInstance();
        result.set(Calendar.HOUR_OF_DAY, hour);
        result.set(Calendar.MINUTE, minute);
        result.set(Calendar.SECOND, second);
        result.add(Calendar.DAY_OF_YEAR, nextDayFlag);
        return result;
    }

    @Override
    public String toString() {
        return hour + ":" + minute + ":" + second;
    }

    @Override
    public int compareTo(TimeUtil other) {
        if (nextDayFlag > other.nextDayFlag) return 1;
        else {
            if (hour > other.hour) return 1;
            else if (hour < other.hour) return -1;
            else {
                if (minute > other.minute) return 1;
                else if (minute < other.minute) return -1;
                else {
                    if (second > other.second) return 1;
                    else if (second < other.second) return -1;
                    else return 0;
                }
            }
        }

    }

}
