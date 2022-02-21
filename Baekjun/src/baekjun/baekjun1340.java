package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun1340 {
	public int getTotalDate(ArrayList<String> input_arr, HashMap<String, Integer> month, int[] month_day) {
		int total_date = 0;
		if((Integer.parseInt(input_arr.get(2)) % 400 == 0) || ((Integer.parseInt(input_arr.get(2)) % 4 == 0) && (Integer.parseInt(input_arr.get(2)) % 100 != 0))) {
			// 윤달 -> 366일
			for(int i = 0; i < month.get(input_arr.get(0)) - 1; i++) {
				if(i == 1) {
					total_date += month_day[i] + 1;
					continue;
				}
				total_date += month_day[i];
			}
		} else {
			// 365일
			for(int i = 0; i < month.get(input_arr.get(0)) - 1; i++) {
				total_date += month_day[i];
			}
		}
		return total_date;
	}
	
	public double NewYearPer(ArrayList<String> input_arr) {
		double result = 0;
		HashMap<String, Integer> month = new HashMap<String, Integer>() {{
			put("January", 1);
			put("February", 2);
			put("March", 3);
			put("April", 4);
			put("May", 5);
			put("June", 6);
			put("July", 7);
			put("August", 8);
			put("September", 9);
			put("October", 10);
			put("November", 11);
			put("December", 12);
		}};
		int[] month_day = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		double total_min = 0;
		double current_min = 0;
		if((Integer.parseInt(input_arr.get(2)) % 400 == 0) || ((Integer.parseInt(input_arr.get(2)) % 4 == 0) && (Integer.parseInt(input_arr.get(2)) % 100 != 0))) {
			// 윤달 -> 366일
			total_min = 366 * 24 * 60;
		} else {
			// 365일
			total_min = 365 * 24 * 60;
		}
		int total_date = getTotalDate(input_arr, month, month_day);
		total_date += Integer.parseInt(input_arr.get(1)) - 1;
		current_min += (total_date * 24 * 60) + (Integer.parseInt(input_arr.get(3)) * 60) + Integer.parseInt(input_arr.get(4));
		result = (current_min * 100) / total_min;
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input, " ,:");
		ArrayList<String> input_arr = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			input_arr.add(st.nextToken());
		}
		baekjun1340 b = new baekjun1340();
		double result = b.NewYearPer(input_arr);
		System.out.println(result);
	}
}
