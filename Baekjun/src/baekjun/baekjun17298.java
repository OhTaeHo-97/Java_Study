package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun17298 {
	static StringBuilder sb = new StringBuilder();
	
	// 시간 초과
//	static ArrayList<Integer> arr;
//	static int[] series;
//	static int N;
//	static void input() {
//		Reader scanner = new Reader();
//		N = scanner.nextInt();
//		series = new int[N + 1];
//		arr = new ArrayList<Integer>();
//		for(int i = 1; i <= N; i++) {
//			series[i] = scanner.nextInt();
//			if(i != 1) arr.add(series[i]);
//		}
//	}
//	
//	static int lower_bound(int L, int R, int X) {
//		int result = -1;
//		while(L <= R) {
//			int mid = (L + R) / 2;
//			if(arr.get(mid) <= X) {
//				L = mid + 1;
//			} else {
//				result = mid;
//				R = mid - 1;
//			}
//		}
//		return result;
//	}
//	
//	static void solution() {
//		int[] result = new int[N + 1];
//		Collections.sort(arr);
//		for(int i = 1; i < N; i++) {
//			int idx = lower_bound(0, arr.size() - 1, series[i]);
//			if(idx != -1) result[i] = arr.get(idx);
//			else result[i] = -1;
//			arr.remove(Integer.valueOf(series[i + 1]));
//		}
//		result[N] = -1;
//		for(int i = 1; i <= N; i++) sb.append(result[i]).append(' ');
//		System.out.println(sb.toString());
//	}
	
	static int N;
	static int[] series;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		series = new int[N];
		for(int i = 0; i < N; i++) {
			series[i] = scanner.nextInt();
		}
	}
	
	static void solution() {
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < N; i++) {
			while(!stack.isEmpty() && (series[stack.peek()] < series[i])) series[stack.pop()] = series[i];
			stack.push(i);
		}
		while(!stack.isEmpty()) series[stack.pop()] = -1;
		for(int i = 0; i < N; i++) sb.append(series[i]).append(' ');
		System.out.println(sb.toString());
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
