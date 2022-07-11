package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun3079 {
	public long getMinTime(int people_num, int[] immigration_time) {
		int max = 0;
		for(int i = 0; i < immigration_time.length; i++) {
			if(max < immigration_time[i]) {
				max = immigration_time[i];
			}
		}
		long left = 0L;
		long right = max * 1000000000L;
		long time = 0L;
		while(left <= right) {
			long mid = (left + right) / 2;
			long count = 0;
			for(int i = 0; i < immigration_time.length; i++) {
				count += (mid / immigration_time[i]);
			}
			if(count >= people_num) {
				time = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int immigration_num = Integer.parseInt(input[0]);
		int people_num = Integer.parseInt(input[1]);
		int[] immigration_time = new int[immigration_num];
		for(int i = 0; i < immigration_num; i++) {
			immigration_time[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun3079 b = new baekjun3079();
		bw.write(b.getMinTime(people_num, immigration_time) + "\n");
		bw.flush();
		bw.close();
	}
}
