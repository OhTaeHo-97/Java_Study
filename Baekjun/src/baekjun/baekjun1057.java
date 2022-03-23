package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjun1057 {
	public int getRound(String input) {
		StringTokenizer st = new StringTokenizer(input);
		int num = Integer.parseInt(st.nextToken());
		int kim = Integer.parseInt(st.nextToken());
		int lim = Integer.parseInt(st.nextToken());
		int remain;
		int round = 1;
		for(int i = num; i >= 1; i /= 2) {
			if(((kim + 1 == lim) && (kim % 2 == 1)) || ((lim + 1 == kim) && (lim % 2 == 1))) {
				return round;
			}
			kim = kim / 2 + kim % 2;
			lim = lim / 2 + lim % 2;
			round++;
		}
		return round;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		baekjun1057 b = new baekjun1057();
		bw.write(b.getRound(input) + "\n");
		bw.flush();
		bw.close();
	}
}
