package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class baekjun25381 {
	public int getMaxCount(String s) {
		int max = 0;
		Deque<Integer> a_indexes = new ArrayDeque<Integer>();
		Deque<Integer> b_indexes = new ArrayDeque<Integer>();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'A') {
				a_indexes.addLast(i);
			} else if(s.charAt(i) == 'B') {
				b_indexes.addLast(i);
			} else if(s.charAt(i) == 'C') {
				if(b_indexes.size() > 0) {
					max++;
					b_indexes.pollFirst();
				}
			} else {
				System.exit(1);
			}
		}
		while(b_indexes.size() > 0 && a_indexes.size() > 0) {
			if(a_indexes.getFirst() < b_indexes.getFirst()) {
				max++;
				a_indexes.pollFirst();
				b_indexes.pollFirst();
			} else {
				b_indexes.pollFirst();
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();
		br.close();
		baekjun25381 b = new baekjun25381();
		bw.write(b.getMaxCount(s) + "\n");
		bw.flush();
		bw.close();
	}
}
