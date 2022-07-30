package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2531 {
	public int getMaxTypeNum(int[] sushi_nums, int d, int k, int c) {
		int max = 0; // 최대 가짓수
		int[] sushies = new int[d + 1]; // 전체 초밥 번호(k개를 골랐을 때, 각각이 몇 개가 들어가있는지를 나타냄)
		for(int i = 0; i < k; i++) { // 첫 번째 초밥부터 k개
			if(sushies[sushi_nums[i]] == 0) {
				max++;
			}
			sushies[sushi_nums[i]]++;
		}
		int count = max;
		for(int i = 1; i < sushi_nums.length; i++) { // 시작점 변경
			if(max <= count) {
				if(sushies[c] == 0) { // 쿠폰 번호를 k개에서 고르지 않은 경우
					max = count + 1;
				} else { // 쿠폰 번호를 k개에서 고른 않은 경우
					max = count;
				}
			}
			int end = (i + k - 1) % sushi_nums.length; // i를 시작으로 할 때, k개 중 마지막 index
			if(sushies[sushi_nums[end]] == 0) {
				count++;
			}
			sushies[sushi_nums[end]]++;
			sushies[sushi_nums[i - 1]]--; // 시작지점이 바뀌기 때문에 이전 시작지점의 초밥은 개수를 하나 줄임
			if(sushies[sushi_nums[i - 1]] == 0) { // 해당 초밥이 더 이상 선택되지 않았으므로 가짓수에서 하나 뺌
				count--;
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int d = Integer.parseInt(input[1]);
		int k = Integer.parseInt(input[2]);
		int c = Integer.parseInt(input[3]);
		int[] sushi_nums = new int[n];
		for(int i = 0; i < sushi_nums.length; i++) {
			sushi_nums[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun2531 b = new baekjun2531();
		bw.write(b.getMaxTypeNum(sushi_nums, d, k, c) + "\n");
		bw.flush();
		bw.close();
	}
}