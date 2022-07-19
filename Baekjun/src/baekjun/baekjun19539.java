package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun19539 {
	public String isPossible(int[] heights) {
		int total_height = 0;
		int odd = 0;
		for(int i = 0; i < heights.length; i++) {
			total_height += heights[i];
			if(heights[i] % 2 == 1) {
				odd++;
			}
		}
		if(total_height % 3 == 0 && odd <= total_height / 3) {
			return "YES";
		} else {
			return "NO";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] heights = new int[num];
		String[] input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < num; i++) {
			heights[i] = Integer.parseInt(input[i]);
		}
		baekjun19539 b = new baekjun19539();
		bw.write(b.isPossible(heights) + "\n");
		bw.flush();
		bw.close();
	}
}
