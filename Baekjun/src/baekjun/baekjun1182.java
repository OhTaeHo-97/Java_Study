package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1182 {
	static int count = 0;
	static int num;
	static int s;
	static int[] series;
	public void backtracking(int total, int depth) {
		if(depth == num - 1 && total == s) {
			count++;
		}
		depth++;
		if(depth < num) {
			backtracking(total + series[depth], depth);
			backtracking(total, depth);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		num = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		String nums = br.readLine();
		br.close();
		st = new StringTokenizer(nums);
		series = new int[num];
		for(int i = 0; i < num; i++) {
			series[i] = Integer.parseInt(st.nextToken());
		}
		baekjun1182 b = new baekjun1182();
		for(int i = 0; i < series.length; i++) {
			b.backtracking(series[i], i);
		}
		bw.write(count + "\n");
		bw.flush();
		bw.close();
	}
}
