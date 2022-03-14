package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1138 {
	public ArrayList<Integer> getSequence(int[] seq) {
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		sequence.add(seq.length);
		for(int i = seq.length - 2; i >= 0; i--) {
			sequence.add(seq[i], i + 1);
		}
		return sequence;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] seq = new int[num];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < seq.length; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		baekjun1138 b = new baekjun1138();
		ArrayList<Integer> sequence = b.getSequence(seq);
		for(int i = 0; i < sequence.size(); i++) {
			bw.write(sequence.get(i) + " ");
		}
		bw.flush();
		bw.close();
	}
}
