package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1072 {
	public long increaseWinningRate(int prev_total_game, int prev_win_game) {
		long prev_winningRate = (long)prev_win_game * 100 / prev_total_game;
		if(prev_winningRate >= 99) {
			return -1;
		}
		long winningRate = 0;
		long start = 1;
		long end = prev_total_game;
		long mid = 0;
		while(start <= end) {
			mid = (start + end) / 2;
			winningRate = (prev_win_game + mid) * 100 / (prev_total_game + mid);
			if(winningRate > prev_winningRate) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int total_game = Integer.parseInt(st.nextToken());
		int win_game = Integer.parseInt(st.nextToken());
		baekjun1072 b = new baekjun1072();
		bw.write(b.increaseWinningRate(total_game, win_game) + "\n");
		bw.flush();
		bw.close();
	}
}
