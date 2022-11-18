package programmers.kakaoBlindRecruiment2018;

import java.util.*;

public class Level3_ShuttleBus {
	// 0 < 셔틀 운행 횟수 n <= 10
	// 0 < 셔틀 운행 간격 t <= 60
	// 0 < 한 셔틀에 탈 수 있는 최대 크루 수 m <= 45
	// 크루가 대기열에 도착하는 시간을 모은 배열 timetable
	// 1 <= timetable 길이 <= 2000
	
	static HashMap<Time, ArrayList<Time>> crewList;
	public static String solution(int n, int t, int m, String[] timetable) {
		Time[] timeList = new Time[timetable.length];
		for(int idx = 0; idx < timetable.length; idx++) timeList[idx] = strToTime(timetable[idx]);
		Arrays.sort(timeList, (t1, t2) -> (t1.hour != t2.hour) ? t1.hour - t2.hour : t1.minute - t2.minute);
		int limit = m, index = 0;
		Time answer = new Time(9, 0);
		for(int hour = 9, min = 0, count = 0; count < n; min += t, count++) {
			if(min >= 60) {
				hour += min / 60;
				min %= 60;
			}
			int tempIdx = index;
			for(int idx = tempIdx; idx < timeList.length; idx++) {
				if(limit == 0) break;
				Time temp = timeList[idx];
				if(temp.hour < hour) {
					index = idx + 1;
					limit--;
				}
				else if(temp.hour == hour) {
					if(temp.minute <= min) {
						index = idx + 1;
						limit--;
					}
				}
			}
			if(count == n - 1) {
				if(limit != 0) {
					answer = new Time(hour, min);
					break;
				} else {
					Time temp = timeList[index - 1];
					if(temp.minute == 0) answer = new Time(temp.hour - 1, 59);
					else answer = new Time(temp.hour, temp.minute - 1);
					break;
				}
			}
			limit = m;
		}
		return timeToStr(answer);
	}
	
	static Time strToTime(String time) {
		String[] t = time.split(":");
		int hour = Integer.parseInt(t[0]), min = Integer.parseInt(t[1]);
		return new Time(hour, min);
	}
	
	static String timeToStr(Time time) {
		String hour = Integer.toString(time.hour), min = Integer.toString(time.minute);
		if(hour.length() == 1) hour = "0" + hour;
		if(min.length() == 1) min = "0" + min;
		return (hour + ":" + min);
	}
	
	static class Time {
		int hour, minute;
		public Time(int hour, int minute) {
			this.hour = hour;
			this.minute = minute;
		}
		@Override
		public boolean equals(Object obj) {
			Time t = (Time)obj;
			if(hour == t.hour && minute == t.minute) return true;
			return false;
		}
		@Override
		public int hashCode() {
			return Objects.hash(hour, minute);
		}
	}
	
	public static void main(String[] args) {
//		int n = 1, t = 1, m = 5;
//		String[] timetable = {"08:00", "08:01", "08:02", "08:03"};
//		int n = 2, t = 10, m = 2;
//		String[] timetable = {"09:10", "09:09", "08:00"};
		int n = 2, t = 1, m = 2;
		String[] timetable = {"09:00", "09:00", "09:00", "09:00"};
//		int n = 1, t = 1, m = 5;
//		String[] timetable = {"00:01", "00:01", "00:01", "00:01", "00:01"};
//		int n = 1, t = 1, m = 1;
//		String[] timetable = {"23:59"};
//		int n = 10, t = 60, m = 45;
//		String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
		System.out.println(solution(n, t, m, timetable));
	}
}
