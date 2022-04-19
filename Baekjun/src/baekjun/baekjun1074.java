package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1074 {
	public int getVisitNth(int size, int row, int col) {
		int[][] mul = {{0, 1}, {2, 3}};
		int s = (int)Math.pow(2, size - 1);
		int r = row;
		int c = col;
		int remainder_r = row;
		int remainder_c = col;
		int num = 0;
		int count = 0;
		while(true) {
			r = remainder_r / s;
			c = remainder_c / s;
			remainder_r = remainder_r % s;
			remainder_c = remainder_c % s;
			if(count == 0) {
				num += (s * s) * mul[r][c] - 1;
			} else {
				num += (s * s) * mul[r][c];
			}
			count++;
			if(s == 2) {
				num -= (mul[1][1] - mul[remainder_c][remainder_r]);
				return num;
			}
			s /= 2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int size = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		baekjun1074 b = new baekjun1074();
		bw.write(b.getVisitNth(size, row, col) + "\n");
		bw.flush();
		bw.close();
	}
}
