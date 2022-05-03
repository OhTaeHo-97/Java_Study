package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2110 {
	public int findGateWayNum(long length, int[] house_location) {
		int count = 1;
		int prev_loc = house_location[0];
		for(int i = 1; i < house_location.length; i++) {
			if(house_location[i] - prev_loc >= length) {
				count++;
				prev_loc = house_location[i];
			}
		}
		return count;
	}
	
	public long getMaxGatewayNum(int gateway_num, int[] house_location) {
		Arrays.sort(house_location);
		long start = 1;
		long end = (long)house_location[house_location.length - 1] - house_location[0] + 1;
		while(start < end) {
			long mid = (start + end) / 2;
			if(findGateWayNum(mid, house_location) < gateway_num) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int house_num = Integer.parseInt(input[0]);
		int gateway_num = Integer.parseInt(input[1]);
		int[] house_location = new int[house_num];
		for(int i = 0; i < house_location.length; i++) {
			house_location[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2110 b = new baekjun2110();
		bw.write(b.getMaxGatewayNum(gateway_num, house_location) + "\n");
		bw.flush();
		bw.close();
	}
}
