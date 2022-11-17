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
		Time[] timeList = new Time[n];
		crewList = new HashMap<>();
		int hour = 9, minute = 0;
		for(int count = 0; count < n; count++) {
			crewList.put(new Time(hour, minute), new ArrayList<>());
			timeList[count] = new Time(hour, minute);
			minute += t;
			if(minute >= 60) {
				minute %= 60;
				hour += (minute / 60);
			}
		}
		for(String time : timetable) {
			String[] timeArr = time.split(":");
			hour = Integer.parseInt(timeArr[0]);
			minute = Integer.parseInt(timeArr[1]);
			for(Time key : timeList) {
				int keyHour = key.hour, keyMin = key.minute;
				if(hour > keyHour) {
					crewList.get(key).add(new Time(keyHour, keyMin));
					break;
				} else if(hour == keyHour) {
					if(minute >= keyMin) {
						crewList.get(key).add(new Time(keyHour, keyMin));
						break;
					}
				}
			}
		}
		Time answer = new Time(0, 0);
		if(n == 1) {
			for(Time key : crewList.keySet()) {
				int size = crewList.get(key).size();
				if(size == 0) answer = new Time(9, 0);
				else if(size >= m) {
					Time temp = crewList.get(key).get(m - 1);
					if(temp.minute == 0) answer = new Time(temp.hour - 1, 59);
					else answer = new Time(temp.hour, temp.minute - 1);
				}
			}
		} else {			
			for(int idx = 0; idx < timeList.length; idx++) {
				int size = crewList.get(timeList[idx]).size();
				Collections.sort(crewList.get(timeList[idx]), (t1, t2) -> (t1.hour != t2.hour) ? t1.hour - t2.hour : t1.minute - t2.minute);
				if(idx == timeList.length - 1 && m > 0) {
					answer = timeList[idx];
					break;
				}
				if(m <= size) {
					Time temp = crewList.get(timeList[idx]).get(m - 1);
					if(temp.minute == 0) {
						answer = new Time(temp.hour - 1, 59);
						break;
					} else {
						answer = new Time(temp.hour, temp.minute - 1);
						break;
					}
				}
			}
		}
		String h = Integer.toString(answer.hour);
		String min = Integer.toString(answer.minute);
		if(h.length() == 1) h = "0" + h;
		if(min.length() == 1) min = "0" + min;
		return (h + ":" + min);
	}
	
	public static void main(String[] args) {
//		int n = 1, t = 1, m = 5;
//		String[] timetable = {"08:00", "08:01", "08:02", "08:03"};
		int n = 2, t = 10, m = 2;
		String[] timetable = {"09:10", "09:09", "08:00"};
		System.out.println(solution(n, t, m, timetable));
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
}
