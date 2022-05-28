package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SW12741 {
	public int[] getLighteningTime(int[][] times) {
		int[] result = new int[times.length];
		for(int i = 0; i < times.length; i++) {
			if(times[i][0] == times[i][2]) {
				int min = Math.min(times[i][1], times[i][3]);
				result[i] = min - times[i][0];
				continue;
			}
			if(times[i][1] == times[i][3]) {
				int max = Math.max(times[i][0], times[i][2]);
				result[i] = times[i][1] - max;
				continue;
			}
			if(times[i][0] < times[i][2] && times[i][2] < times[i][1]) {
				int min = Math.min(times[i][1], times[i][3]);
				result[i] = min - times[i][2];
			} else if(times[i][2] < times[i][0] && times[i][0] < times[i][3]) {
				int min = Math.min(times[i][1], times[i][3]);
				result[i] = min - times[i][0];
			} else {
				result[i] = 0;
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		int[][] times = new int[test_num][4];
		for(int i = 0; i < test_num; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 4; j++) {
				times[i][j] = Integer.parseInt(input[j]);
			}
		}
		br.close();
		SW12741 s = new SW12741();
		int[] result = s.getLighteningTime(times);
		for(int i = 0; i < result.length; i++) {
			bw.write("#" + (i + 1) + " " + result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
