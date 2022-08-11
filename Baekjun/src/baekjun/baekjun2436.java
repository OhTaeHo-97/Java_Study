package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2436 {
	static StringBuilder sb = new StringBuilder();
	static int A, B;
	static long n1, n2;
	
	static void input() {
		Reader scan = new Reader();
		A = scan.nextInt();
		B = scan.nextInt();
	}
	
	static void solution() {
		n1 = (long)A;
		n2 = (long)B;
		long multiply_n = n1 * n2; // gcd(x, y) * lcm(x, y) = x * y
		for(long i = 2 * (long)A; i * i <= multiply_n; i += A) {
			if(multiply_n % i == 0) {
				long temp = multiply_n / i;
				if(gcd(i, temp) == A) {
					if(n1 + n2 > i + temp) {
						n1 = i;
						n2 = temp;
					}
				}
			}
		}
		sb.append(n1 + " " + n2 + "\n");
	}
	
	static long gcd(long i, long temp) {
		return temp == 0 ? i : gcd(temp, i % temp);
	}
	
	public static void main(String[] args) {
		input();
		solution();
		System.out.println(sb.toString());
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
