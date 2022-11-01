package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class baekjun7662 {
	// PriorityQueue 이용
//	static StringBuilder sb = new StringBuilder();
//	static Reader scanner = new Reader();
//	static int T, k;
//	static HashMap<Integer, Integer> map;
//	static PriorityQueue<Integer> min, max;
//	static void input() {
//		k = scanner.nextInt();
//		map = new HashMap<>();
//		min = new PriorityQueue<>();
//		max = new PriorityQueue<>(Collections.reverseOrder());
//		for(int order = 1; order <= k; order++) {
//			String operator = scanner.next();
//			int num = scanner.nextInt();
//			if(operator.equals("I")) insert(num);
//			else if(operator.equals("D")) {
//				if(map.size() == 0) continue;
//				PriorityQueue<Integer> queue = num == 1 ? max : min;
//				delete(queue);
//			}
//		}
//	}
//	
//	static void insert(int num) {
//		map.put(num, map.getOrDefault(num, 0) + 1);
//		min.offer(num);
//		max.offer(num);
//	}
//	
//	static int delete(PriorityQueue<Integer> queue) {
//		int n;
//		while(true) {
//			n = queue.poll();
//			int num = map.getOrDefault(n, 0);
//			if(num == 0) continue;
//			if(num == 1) map.remove(n);
//			else map.put(n, num - 1);
//			break;
//		}
//		return n;
//	}
//	
//	static void printList() {
//		if(map.size() == 0) sb.append("EMPTY").append('\n');
//		else {
//			int maxNum = delete(max);
//			sb.append(maxNum).append(' ').append(map.size() > 0 ? delete(min) : maxNum).append('\n');
//		}
//	}
//	
//	public static void main(String[] args) {
//		T = scanner.nextInt();
//		for(int test = 0; test < T; test++) {
//			input();
//			printList();
//		}
//		System.out.println(sb);
//	}
	
	// TreeMap 이용
	static StringBuilder sb = new StringBuilder();
	static Reader scanner = new Reader();
	static int T, k;
	static TreeMap<Integer, Integer> map;
	static void input() {
		k = scanner.nextInt();
		map = new TreeMap<>();
		for(int order = 1; order <= k; order++) {
			String operator = scanner.next();
			int num = scanner.nextInt();
			if(operator.equals("I")) insert(num);
			else if(operator.equals("D")) {
				if(map.size() == 0) continue;
				int n = num == 1 ? map.lastKey() : map.firstKey();
				delete(n);
			}
		}
	}
	
	static void insert(int num) {
		map.put(num, map.getOrDefault(num, 0) + 1);
	}
	
	static void delete(int num) {
		if(map.put(num, map.get(num) - 1) == 1) map.remove(num);
	}
	
	static void printList() {
		if(map.size() == 0) sb.append("EMPTY").append('\n');
		else
			sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');
	}
	
	public static void main(String[] args) {
		T = scanner.nextInt();
		for(int test = 0; test < T; test++) {
			input();
			printList();
		}
		System.out.println(sb);
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
