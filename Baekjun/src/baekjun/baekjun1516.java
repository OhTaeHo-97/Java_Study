package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1516 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] related, time;
	static HashMap<Integer, ArrayList<Integer>> map;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		related = new int[N + 1];
		time = new int[N + 1];
		map = new HashMap<>();
		for(int build = 1; build <= N; build++) map.put(build, new ArrayList<>());
		for(int build = 1; build <= N; build++) {
			int t = scanner.nextInt();
			int relatedNum = 0;
			int neighbor = 0;
			while(true) {
				neighbor = scanner.nextInt();
				if(neighbor == -1) break;
				// 선행되어야 하는 map에 후에 따라올 작업들을 넣기
				map.get(neighbor).add(build);
				relatedNum++;
			}
			time[build] = t;
			related[build] = relatedNum;
 		}
	}
	
	static void solution() {
		Queue<Integer> queue = new LinkedList<>();
		int[] eachTime = new int[time.length];
		for(int build = 1; build <= N; build++) {
			eachTime[build] = time[build];
			if(related[build] == 0) queue.offer(build);
		}
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int following : map.get(cur)) {
				related[following]--;
				eachTime[following] = Math.max(eachTime[following], eachTime[cur] + time[following]);
				if(related[following] == 0) queue.offer(following);
			}
		}
		for(int build = 1; build <= N; build++) sb.append(eachTime[build]).append('\n');
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
