package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class baekjun6198 {
	public long getSumOfBuliding(int[] heights) {
		long result = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < heights.length; i++) {
			while(!stack.isEmpty() && stack.peek() <= heights[i]) {
				stack.pop();
			}
			stack.push(heights[i]);
			result += stack.size() - 1;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] heights = new int[n];
		for(int i = 0; i < n; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun6198 b = new baekjun6198();
		bw.write(b.getSumOfBuliding(heights) + "\n");
		bw.flush();
		bw.close();
	}
}
