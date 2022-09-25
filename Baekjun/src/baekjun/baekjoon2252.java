package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon2252 {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] precedeNum;
	static HashMap<Integer, ArrayList<Integer>> precede;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		precedeNum = new int[N + 1];
		precede = new HashMap<Integer, ArrayList<Integer>>();
		for(int num = 1; num <= N; num++) precede.put(num, new ArrayList<Integer>());
		for(int count = 0; count < M; count++) {
			int first = scanner.nextInt();
			int second = scanner.nextInt();
			precedeNum[second]++;
			precede.get(first).add(second);
		}
	}
	
	static void solution() {
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int num = 1; num <= N; num++) {
			if(precedeNum[num] == 0) {
				queue.offer(num);
				sb.append(num).append(' ');
			}
		}
		while(!queue.isEmpty()) {
			int curStudent = queue.poll();
			for(int student : precede.get(curStudent)) {
				precedeNum[student]--;
				if(precedeNum[student] == 0) {
					queue.offer(student);
					sb.append(student).append(' ');
				}
			}
		}
		System.out.println(sb);
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
				} catch(IOException e) {
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
