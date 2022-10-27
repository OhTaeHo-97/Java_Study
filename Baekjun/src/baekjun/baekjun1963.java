package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1963 {
	static StringBuilder sb = new StringBuilder();
	static Reader scanner = new Reader();
	static boolean[] primes;
	static char[] start;
	static int end;
	static final char[] candidate = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	static void input() {
		start = scanner.next().toCharArray();
		end = scanner.nextInt();
	}
	
	static void solution() {
		Queue<Prime> queue = new LinkedList<Prime>();
		queue.add(new Prime(start, 0));
		boolean[] visited = new boolean[10000];
		visited[Integer.parseInt(new String(start))] = true;
		while(!queue.isEmpty()) {
			Prime cur = queue.poll();
			if(Integer.parseInt(new String(cur.num)) == end) {
				sb.append(cur.count).append('\n');
				return;
			}
			for(int cipher = 0; cipher < 4; cipher++) {
				for(int index = 0; index < candidate.length; index++) {
					if(cipher == 0 && index == 0) continue;
					if(cur.num[cipher] == candidate[index]) continue;
					char[] other = cur.num.clone();
					other[cipher] = candidate[index];
					if(!visited[Integer.parseInt(new String(other))] && primes[Integer.parseInt(new String(other))]) {
						visited[Integer.parseInt(new String(other))] = true;
						queue.offer(new Prime(other, cur.count + 1));
					}
				}
			}
		}
		sb.append("Impossible").append('\n');
	}
	
	static void findAllPrimes() {
		primes = new boolean[10000];
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;
		for(int num = 2; num <= Math.sqrt(9999); num++) {
			if(primes[num]) {
				for(int n = num * num; n <= 9999; n += num) primes[n] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		findAllPrimes();
		int T = scanner.nextInt();
		while(T-- > 0) {
			input();
			solution();
		}
		System.out.println(sb);
	}
	
	static class Prime {
		char[] num;
		int count;
		public Prime(char[] num, int count) {
			this.num = num;
			this.count = count;
		}
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
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
