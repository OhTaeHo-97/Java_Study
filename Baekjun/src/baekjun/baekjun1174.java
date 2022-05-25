package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class baekjun1174 {
	static int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
	static ArrayList<Long> arr = new ArrayList<Long>();
	static int num;
	public void dfs(long sum, int index) {
		// sum이 이전에 나온적이 없는 수라면 arr에 저장
		if(!arr.contains(sum)) {
			arr.add(sum);
		}
		// nums 배열 모두 탐색했으면 재귀호출 종료
		if(index >= 10) {
			return;
		}
		dfs(sum * 10 + nums[index], index + 1); // 현재 수 선택하는 경우
		dfs(sum, index + 1); // 현재 수 선택하지 않는 경우
	}
	
	public long getDecreasingNum() {
		dfs(0, 0);
		Collections.sort(arr);
		if(num > 1023) {
			return -1;
		} else {
			return arr.get(num - 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		num = Integer.parseInt(br.readLine());
		br.close();
		baekjun1174 b = new baekjun1174();
		bw.write(b.getDecreasingNum() + "\n");
		bw.flush();
		bw.close();
	}
}
