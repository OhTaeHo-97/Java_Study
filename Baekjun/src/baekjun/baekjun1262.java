package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1262 {
	public String[] getAlphabetDiamond(int n, int x1, int y1, int x2, int y2) {
		String[] alphabetDiamond = new String[x2 - x1 + 1];
		int length = 2 * n - 1;
		int row = x1;
		for(int i = 0; i < (x2- x1 + 1); i++) {
			String temp = "";
			int col = y1;
			for(int j = 0; j < (y2 - y1 + 1); j++) {
				int x = row % length;
				int y = col % length;
				int distance = Math.abs((n - 1) - x) + Math.abs((n - 1) - y);
				if(distance > (n - 1)) {
					temp += ".";
				} else {
					temp += Character.toString((char)(97 + (distance % 26)));
				}
				col++;
			}
			alphabetDiamond[i] = temp;
			row++;
		}

		return alphabetDiamond;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int n = Integer.parseInt(st.nextToken());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		baekjun1262 b = new baekjun1262();
		String[] result = b.getAlphabetDiamond(n, x1, y1, x2, y2);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}