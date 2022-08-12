package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class baekjun2251 {
	static int[] size;
	static boolean[][] visited;
	static TreeSet<Integer> result;
	
	static void input() {
		Reader scan = new Reader();
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		size = new int[] {a, b, c};
		visited = new boolean[201][201];
		result = new TreeSet<Integer>();
	}
	
	static void solution(int a, int b, int c) {
		if(visited[a][b]) return;
		if(a == 0)
			result.add(c);
		visited[a][b] = true;
		
		if(a + b > size[1]) {
			solution(a + b - size[1], size[1], c);
		} else {
			solution(0, a + b, c);
		}
		
		if(a + b > size[0]) {
			solution(size[0], a + b - size[0], c);
		} else {
			solution(a + b, 0, c);
		}
		
		if(a + c > size[0]) {
			solution(size[0], b, a + c - size[0]);
		} else {
			solution(a + c, b, 0);
		}
		
		if(b + c > size[1]) {
			solution(a, size[1], b + c - size[1]);
		} else {
			solution(a, b + c, 0);
		}
		
		solution(a, 0, b + c);
		solution(0, b, a + c);
	}
	
	public static void main(String[] args) {
		input();
		solution(0, 0, size[2]);
		for(int r : result)
			System.out.print(r + " ");
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
