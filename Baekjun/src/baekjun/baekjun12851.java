package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun12851 {
	static StringBuilder sb = new StringBuilder();
	static int N, K, min, count;
	static final int MAX_SIZE = 100001;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		K = scanner.nextInt();
		min = Integer.MAX_VALUE;
		count = 0;
	}
	
	static void solution() {
		min = Integer.MAX_VALUE;
		count = 0;
		bfs();
		sb.append(min).append('\n').append(count).append('\n');
		System.out.println(sb);
	}
	
	static void bfs() {
		int[] dist = new int[MAX_SIZE];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N);
		dist[N] = 0;
		while(!queue.isEmpty()) {
			int curPos = queue.poll();
			if(curPos == K) {
				if(min > dist[curPos]) {
					min = dist[curPos];
					count = 1;
				} else if(min == dist[curPos]) count++;
			}
			int next = curPos + 1;
			if(next < MAX_SIZE && dist[next] >= dist[curPos] + 1) {
				dist[next] = dist[curPos] + 1;
				queue.offer(next);
			}
			next = curPos * 2;
			if(next < MAX_SIZE && dist[next] >= dist[curPos] + 1) {
				dist[next] = dist[curPos] + 1;
				queue.offer(next);
			}
			next = curPos - 1;
			if(next >= 0 && dist[next] >= dist[curPos] + 1) {
				dist[next] = dist[curPos] + 1;
				queue.offer(next);
			}
		}
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
