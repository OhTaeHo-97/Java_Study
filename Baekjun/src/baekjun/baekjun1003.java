package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun1003 {	
	public int[] getFibo(ArrayList<Integer> testcases) {
		int[] num_list = new int[testcases.size() * 2];
		for(int i = 0; i < testcases.size(); i++) {
			if(testcases.get(i) == 0) {
				num_list[2 * i] = 1;
				num_list[2 * i + 1] = 0;
			} else if(testcases.get(i) == 1) {
				num_list[2 * i] = 0;
				num_list[2 * i + 1] = 1;
			} else if(testcases.get(i) == 2) {
				num_list[2 * i] = 1;
				num_list[2 * i + 1] = 1;
			} else {
				int prev = 1;
				int pre_prev = 1;
				int num_one = 0;
				for(int j = 3; j <= testcases.get(i); j++) {
					num_one = prev + pre_prev;
					int temp = prev;
					prev += pre_prev;
					pre_prev = temp;
				}
				num_list[2 * i] = pre_prev;
				num_list[2 * i + 1] = num_one;
			}
		}
		return num_list;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		ArrayList<Integer> testcases = new ArrayList<Integer>();
		for(int i = 0; i < num; i++) {
			testcases.add(Integer.parseInt(br.readLine()));
		}
		br.close();
		baekjun1003 b = new baekjun1003();
		int[] num_list = b.getFibo(testcases);
		for(int i = 0; i < testcases.size(); i++) {
			bw.write(num_list[2 * i] + " " + num_list[2 * i + 1] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
