package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun1024 {
	public ArrayList<Integer> getSequences(int sum, int len) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int count = len;
		while(true) {
			int sum_copy = sum;
			int sum_equal_difference = 0;
			for(int i = 0; i < count; i++) {
				sum_equal_difference += i;
			}
			sum_copy -= sum_equal_difference;
			if(sum_copy % count == 0) {
				// 수열이 존재할 때
				for(int i = 0; i < count; i++) {
					result.add((sum_copy / count) + i);
				}
				if(result.get(0) < 0) {
					return new ArrayList<Integer>(Arrays.asList(-1));
				}
				return result;
			}
			count++;
			if(count > 100) {
				// 수열의 길이가 100보다 큼
				return new ArrayList<Integer>(Arrays.asList(-1));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int sum = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken());
		
		baekjun1024 b = new baekjun1024();
		ArrayList<Integer> result = b.getSequences(sum, len);
		for(int i = 0; i < result.size(); i++) {
			bw.write(result.get(i) + " ");
		}
		bw.flush();
		bw.close();
	}
}
