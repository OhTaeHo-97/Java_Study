package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun11758 {
	static int[][] points;
	
	public int getCCW() {
		int D = (points[1][0] - points[0][0]) * (points[2][1] - points[0][1]) - (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]);
		if(D > 0) {
			return 1;
		} else if(D < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		points = new int[3][2];
		for(int i = 0; i < points.length; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < points[i].length; j++) {
				points[i][j] = Integer.parseInt(input[j]);
			}
		}
		br.close();
		baekjun11758 b = new baekjun11758();
		bw.write(b.getCCW() + "\n");
		bw.flush();
		bw.close();
	}
}
