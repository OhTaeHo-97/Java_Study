package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1715 {
	static int N, result;
	static PriorityQueue<Integer> card_num;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		card_num = new PriorityQueue<Integer>();
		result = 0;
		for(int i = 0; i < N; i++) {
			card_num.offer(scanner.nextInt());
		}
	}
	
	static void solution() {
		while(card_num.size() > 1) {
			int card1 = card_num.poll();
			int card2 = card_num.poll();
			card_num.offer(card1 + card2);
			result += card1 + card2;
		}
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
		
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
