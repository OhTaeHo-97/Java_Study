package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1072 {
	public int increaseWinningRate(int prev_total_game, int prev_win_game) {
		int prev_winningRate = (int)((long)prev_win_game * 100) / prev_total_game;
		int winningRate = 0;
		int total_game = prev_total_game;
		int win_game = prev_win_game;
		int count = 0;
		if(prev_winningRate == 100) {
			return -1;
		}
		while(winningRate <= prev_winningRate) {
			count++;
			win_game++;
			total_game++;
			winningRate = (int)((long)win_game * 100) / total_game;
		}
		return count;
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
