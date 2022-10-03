package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1644 {
	static int N, answer = 0;
	static ArrayList<Integer> prime;
	static int[] cumulativeSum;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		prime = new ArrayList<Integer>();
	}
	
	static void solution() {
		if(N == 1) {
			System.out.println(0);
			return;
		}
		getAllPrimes();
		cumulativeSum = new int[prime.size() + 1];
		cumulative();
		twoPointer();
		System.out.println(answer);
	}
	
	static void twoPointer() {
		for(int L = 0, R = 1; L < cumulativeSum.length; L++) {
			while(L < R && R < cumulativeSum.length) {
				int dif = cumulativeSum[R] - cumulativeSum[L];
				if(dif == N) {
					answer++;
					break;
				}
				if(dif > N) break;
				if(dif < N) R++;
			}
		}
	}
	
	static void cumulative() {
		for(int index = 1; index <= prime.size(); index++)
			cumulativeSum[index] = prime.get(index - 1);
		for(int index = 1; index <= prime.size(); index++)
			cumulativeSum[index] += cumulativeSum[index - 1];
	}
	
	static void getAllPrimes() {
		// 에라토스테네스의 체
		boolean[] primes = new boolean[N + 1];
		primes[0] = primes[1] = true;
		for(int num = 2; num <= Math.sqrt(N); num++) {
			if(!primes[num]) {
				for(int n = num * num; n <= N; n += num) primes[n] = true;
			}
		}
		for(int num = 2; num <= N; num++) {
			if(!primes[num]) prime.add(num);
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
