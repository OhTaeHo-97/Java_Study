package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1263 {
	public int getLatestTime(String[] cases) {
		int[] takeTime = new int[cases.length];
		int[] endTime = new int[cases.length];
		for(int i = 0; i < cases.length; i++) {
			StringTokenizer st = new StringTokenizer(cases[i]);
			takeTime[i] = Integer.parseInt(st.nextToken());
			endTime[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < endTime.length - 1; i++) {
			for(int j = i + 1; j < endTime.length; j++) {
				if(endTime[i] > endTime[j]) {
					int temp = endTime[i];
					endTime[i] = endTime[j];
					endTime[j] = temp;
					temp = takeTime[i];
					takeTime[i] = takeTime[j];
					takeTime[j] = temp;
				}
			}
		}
		int startTime = endTime[0] - takeTime[0];
		int curEndTime = endTime[0];
		if(startTime < 0) {
			return -1;
		}
		for(int i = 1; i < endTime.length; i++) {
			curEndTime += takeTime[i];
			if(curEndTime > endTime[i]) {
				startTime -= (curEndTime - endTime[i]);
				curEndTime -= (curEndTime - endTime[i]);
			}
		}
		if(startTime < 0) {
			return -1;
		}
		return startTime;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int case_num = Integer.parseInt(br.readLine());
		String[] cases = new String[case_num];
		for(int i = 0; i < case_num; i++) {
			cases[i] = br.readLine();
		}
		br.close();
		baekjun1263 b = new baekjun1263();
		bw.write(b.getLatestTime(cases) + "\n");
		bw.flush();
		bw.close();
	}
}
