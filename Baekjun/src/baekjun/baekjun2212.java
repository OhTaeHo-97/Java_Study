package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class baekjun2212 {
	static int[] sensors;
	public int getMinSumOfArea(int office_num) {
		Arrays.sort(sensors);
		Integer[] dif = new Integer[sensors.length - 1];
		for(int i = 0; i < sensors.length - 1; i++) {
			dif[i] = sensors[i + 1] - sensors[i];
		}
		Arrays.sort(dif, Collections.reverseOrder());
		int sum = 0;
		for(int i = office_num - 1; i < dif.length; i++) {
			sum += dif[i];
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int sensor_num = Integer.parseInt(br.readLine());
		int office_num = Integer.parseInt(br.readLine());
		sensors = new int[sensor_num];
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < sensor_num; i++) {
			sensors[i] = Integer.parseInt(input[i]);
		}
		baekjun2212 b = new baekjun2212();
		bw.write(b.getMinSumOfArea(office_num) + "\n");
		bw.flush();
		bw.close();
	}
}
