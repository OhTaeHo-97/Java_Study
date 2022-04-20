package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1074 {
	static int count = 0;
	public void findNum(int size, int row, int col) {
		if(size == 1)
			return;
		if(row < size / 2 && col < size / 2) {
			findNum(size / 2, row, col);
		} else if(row >= size / 2 && col < size / 2) {
			count += (size * size / 4) * 2;
			findNum(size / 2, row - size / 2, col);
		} else if(row < size / 2 && col >= size / 2) {
			count += size * size / 4;
			findNum(size / 2, row, col - size / 2);
		} else {
			count += (size * size / 4) * 3;
			findNum(size / 2, row - size / 2, col - size / 2);
		}
	}
	
	public void getVisitNth(int size, int row, int col) {
		int n = (int)Math.pow(2, size);
		findNum(n, row, col);
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
		b.getVisitNth(size, row, col);
		bw.write(count + "\n");
		bw.flush();
		bw.close();
	}
}
