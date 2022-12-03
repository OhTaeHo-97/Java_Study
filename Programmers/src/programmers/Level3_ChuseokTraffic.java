package programmers;

public class Level3_ChuseokTraffic {
	public static int solution(String[] lines) {
		int[] start = new int[lines.length];
		int[] end = new int[lines.length];
		
		for(int idx = 0; idx < lines.length; idx++) {
			String line = lines[idx];
			String[] info = line.split(" ");
			String[] endArr = info[1].split(":");
			int processTime = (int)(Double.parseDouble(info[2].substring(0, info[2].length() - 1)) * 1000);
			int startTime = 0, endTime = 0;
			endTime += Integer.parseInt(endArr[0]) * 60 * 60 * 1000;
			endTime += Integer.parseInt(endArr[1]) * 60 * 1000;
			endTime += (int)(Double.parseDouble(endArr[2]) * 1000);
			startTime = endTime - processTime + 1;
			start[idx] = startTime;
			end[idx] = endTime;
		}
		
		int answer = 0;
		
		for(int idx = 0; idx < lines.length; idx++) {
			int count = 0;
			int startTime = start[idx];
			int endTime = startTime + 1000;
			for(int idx2 = 0; idx2 < lines.length; idx2++) {
				if(start[idx2] >= startTime && start[idx2] < endTime) count++;
				else if(end[idx2] >= startTime && end[idx2] < endTime) count++;
				else if(start[idx2] <= startTime && end[idx2] >= endTime) count++;
			}
			answer = Math.max(answer, count);
		}
		
		for(int idx = 0; idx < lines.length; idx++) {
			int count = 0;
			int startTime = end[idx];
			int endTime = startTime + 1000;
			for(int idx2 = 0; idx2 < lines.length; idx2++) {
				if(start[idx2] >= startTime && start[idx2] < endTime) count++;
				else if(end[idx2] >= startTime && end[idx2] < endTime) count++;
				else if(start[idx2] <= startTime && end[idx2] >= endTime) count++;
			}
			answer = Math.max(answer, count);
		}
		return answer;
	}
	
	public static void main(String[] args) {
//		String[] lines = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
//		String[] lines = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
		String[] lines = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", 
				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
		System.out.println(solution(lines));
	}
}
