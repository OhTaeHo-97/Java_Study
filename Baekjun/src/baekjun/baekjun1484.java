package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

// 해당 문제는 블로그 게시 X
public class baekjun1484 {
	public ArrayList<Integer> findAllPossibleWeight(int G) {
		long[] squared_nums = new long[100001];
		for(int i = 1; i < squared_nums.length; i++) {
			squared_nums[i] = i * i;
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		int left = 1;
		int right = 2;
		while(right <= 100000) {
			long dif = squared_nums[right] - squared_nums[left];
			if(dif == G) {
				result.add(right);
			}
			if(dif > G) {
				left++;
			}
			if(dif <= G) {
				right++;
			}
		}
		if(result.isEmpty() || G == 1) {
			return null;
		} else {
			Collections.sort(result);
			return result;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int G = Integer.parseInt(br.readLine());
		br.close();
		baekjun1484 b = new baekjun1484();
		if(b.findAllPossibleWeight(G) == null)
			bw.write(-1 + "\n");
		else {
			ArrayList<Integer> result = b.findAllPossibleWeight(G);
			for(int r : result)
				bw.write(r + "\n");
		}
		bw.flush();
		bw.close();
	}
}
