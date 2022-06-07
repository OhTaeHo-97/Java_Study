package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class SW9778 {
	public String canContinue(int[] nums, int sum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 2; i < 10; i++) {
			map.put(i, 4);
		}
		map.put(10, 16);
		map.put(11, 4);
		for(int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.get(nums[i]) - 1);
		}
		int dif = 21 - sum;
		if(dif <= 0) {
			return "STOP";
		}
		int high = 0;
		int low = 0;
		for(int i = 2; i <= 11; i++) {
			if(dif - i < 0) {
				high += map.get(i);
			} else {
				low += map.get(i);
			}
		}
		if(high >= low) {
			return "STOP";
		} else {
			return "GAZUA";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		SW9778 s = new SW9778();
		int test_case = Integer.parseInt(br.readLine());
		for(int i = 1; i <= test_case; i++) {
			int sum = 0;
			int n = Integer.parseInt(br.readLine());
			int[] nums = new int[n];
			for(int j = 0; j < n; j++) {
				nums[j] = Integer.parseInt(br.readLine());
				sum += nums[j];
			}
			bw.write("#" + i + " " + s.canContinue(nums, sum) + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
