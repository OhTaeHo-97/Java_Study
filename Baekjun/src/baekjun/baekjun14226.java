package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun14226 {
	static StringBuilder sb = new StringBuilder();
	static int S;
	static void input() {
		Reader scanner = new Reader();
		S = scanner.nextInt();
	}
	
	static void solution() {
		bfs();
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Emoticon> length = new LinkedList<Emoticon>();
		length.offer(new Emoticon(1, 0, 0));
		HashMap<Emoticon, Integer> map = new HashMap<>();
		map.put(new Emoticon(1, 0, 0), 0);
		while(!length.isEmpty()) {
			Emoticon cur = length.poll();
			if(cur.length == S) {
				sb.append(cur.time);
				return;
			}
			if(cur.length + cur.clipLength <= S) {
				Emoticon next = new Emoticon(cur.length, cur.length, cur.time + 1);
				if(!map.containsKey(next) || (map.containsKey(next) && map.get(next) > cur.time + 1)) {
					map.put(next, cur.time + 1);
					length.offer(next);
				}
			}
			if(cur.length < S) {
				Emoticon next = new Emoticon(cur.length + cur.clipLength, cur.clipLength, cur.time + 1);
				if(!map.containsKey(next) || (map.containsKey(next) && map.get(next) > cur.time + 1)) {
					map.put(next, cur.time + 1);
					length.offer(next);
				}
			}
			if(cur.length > 1) {
				Emoticon next = new Emoticon(cur.length - 1, cur.clipLength, cur.time + 1);
				if(!map.containsKey(next) || (map.containsKey(next) && map.get(next) > cur.time + 1)) {
					map.put(next, cur.time + 1);
					length.offer(next);
				}
			}
		}
	}
	
	static class Emoticon {
		int length, clipLength, time;
		public Emoticon(int length, int clipLength, int time) {
			this.length = length;
			this.clipLength = clipLength;
			this.time = time;
		}
		@Override
		public boolean equals(Object obj) {
			Emoticon e = (Emoticon)obj;
			return (length == e.length) && (clipLength == e.clipLength);
		}
		@Override
		public int hashCode() {
			return Objects.hash(length, clipLength);
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
