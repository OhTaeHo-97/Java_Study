package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun2841 {
	public int getMinMoveNum(int[][] melody) {
		Stack<Integer>[] frets = new Stack[6];
		int count = 0;
		for(int i = 0; i < frets.length; i++) {
			frets[i] = new Stack<Integer>();
		}
		for(int i = 0; i < melody.length; i++) {
			if(frets[melody[i][0] - 1].size() == 0) {
				frets[melody[i][0] - 1].push(melody[i][1]);
				count++;
			}
			while(frets[melody[i][0] - 1].size() != 0 && frets[melody[i][0] - 1].peek() > melody[i][1]) {
				frets[melody[i][0] - 1].pop();
				count++;
			}
			if(frets[melody[i][0] - 1].size() != 0 && frets[melody[i][0] - 1].peek() == melody[i][1]) {
				continue;
			}
			frets[melody[i][0] - 1].push(melody[i][1]);
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		int note_num = Integer.parseInt(st.nextToken());
		int fret_num = Integer.parseInt(st.nextToken());
		int[][] melody = new int[note_num][2];
		for(int i = 0; i < melody.length; i++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			melody[i][0] = Integer.parseInt(st.nextToken());
			melody[i][1] = Integer.parseInt(st.nextToken());
		}
		br.close();
		baekjun2841 b = new baekjun2841();
		bw.write(b.getMinMoveNum(melody) + "\n");
		bw.flush();
		bw.close();
	}
}