package programmers;

import java.util.*;

public class Level3_InsertAd {
	public static String solution(String play_time, String adv_time, String[] logs) {
        int play_time_int = timeToInt(play_time);
        int adv_time_int = timeToInt(adv_time);
        long[] watchPeopleNum = new long[play_time_int + 2];
        for(int index = 0; index < logs.length; index++) {
        	String[] splitTime = logs[index].split("-");
        	int time1 = timeToInt(splitTime[0]) + 1;
        	int time2 = timeToInt(splitTime[1]) + 1;
        	watchPeopleNum[time1]++;
        	watchPeopleNum[time2]--;
        }
        int startTime = 0;
        long totalTime = 0;
        for(int time = 1; time < watchPeopleNum.length; time++) watchPeopleNum[time] += watchPeopleNum[time - 1];
        for(int time = 1; time < watchPeopleNum.length; time++) watchPeopleNum[time] += watchPeopleNum[time - 1];
        for(int time = 0; time + adv_time_int < watchPeopleNum.length; time++) {
        	long watchTime = watchPeopleNum[time + adv_time_int] - watchPeopleNum[time];
        	if(totalTime < watchTime) {
        		totalTime = watchTime;
        		startTime = time;
        	}
        }
        return timeToStr(startTime);
    }
	
	public static int timeToInt(String strTime) {
		String[] times = strTime.split(":");
		int intTime = 0;
		int second = 3600;
		for(int index = 0; index < times.length; index++) {
			intTime += second * Integer.parseInt(times[index]);
			second /= 60;
		}
		return intTime;
	}
	
	public static String timeToStr(int intTime) {
		String strTime = "";
		int second = 3600;
		while(second > 0) {
			int time = intTime / second;
			if(time < 10) strTime += "0" + Integer.toString(time);
			else strTime += Integer.toString(time);
			strTime += ":";
			intTime %= second;
			second /= 60;
		}
		return strTime.substring(0, strTime.length() - 1);
	}
	
	public static void main(String[] args) {
		String play_time = "02:03:55";
//		String play_time = "99:59:59";
//		String play_time = "50:00:00";
		String adv_time = "00:14:15";
//		String adv_time = "25:00:00";
//		String adv_time = "50:00:00";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
//		String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
//		String[] logs = {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"};
		System.out.println(solution(play_time, adv_time, logs));
	}
}
