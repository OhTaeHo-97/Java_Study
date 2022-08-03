package programmers;

import java.util.HashMap;
import java.util.TreeMap;

public class Level2_CalcParkingFee {
	public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Integer> map = new TreeMap<>();
        HashMap<String, String> inTimes = new HashMap<>();
        HashMap<String, Integer> times = new HashMap<>();
        for(String record : records) {
            String[] record_content = record.split(" ");
            String time = record_content[0];
            String carNumber = record_content[1];
            if(record_content[2].equals("IN")) {
                inTimes.put(carNumber, time);
            } else if(record_content[2].equals("OUT")) {
                String[] time_parse = time.split(":");
                String[] inTime = inTimes.get(carNumber).split(":");
                inTimes.put(carNumber, "-1");
                int hour = Integer.parseInt(time_parse[0]) - Integer.parseInt(inTime[0]);
                int min = Integer.parseInt(time_parse[1]) - Integer.parseInt(inTime[1]);
                int totalTime = hour * 60 + min;
                if(times.containsKey(carNumber)) {
                    times.put(carNumber, totalTime + times.get(carNumber));
                } else {
                    times.put(carNumber, totalTime);
                }
            }
        }
        for(String key : inTimes.keySet()) {
            if(!inTimes.get(key).equals("-1")) {
                String[] inTime = inTimes.get(key).split(":");
                int hour = 23 - Integer.parseInt(inTime[0]);
                int min = 59 - Integer.parseInt(inTime[1]);
                int totalTime = hour * 60 + min;
                if(times.containsKey(key)) {
                    times.put(key, times.get(key) + totalTime);
                } else {
                    times.put(key, totalTime);
                }
            }
        }
        for(String key : times.keySet()) {
            int totalTime = times.get(key);
            int fee = fees[1];
            totalTime -= fees[0];
            if(totalTime > 0) {
                int extra_time = (int)Math.ceil((double)totalTime / fees[2]);
                fee += extra_time * fees[3];
            }
            map.put(key, fee);
        }
        int[] answer = new int[map.size()];
        int idx = 0;
        for(String key : map.keySet()) {
            answer[idx] = map.get(key);
            idx++;
        }
        return answer;
    }
}
