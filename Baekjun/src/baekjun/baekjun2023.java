package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjun2023 {
	static String[] first_primes = {"2", "3", "5", "7"};
	String[] other_values = {"1", "3", "7", "9"};
	static int n;
	public boolean isPrime(int num) {
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
	
	public void getPrimes(String num, int count) {
		if(count >= n) {
			System.out.println(num);
			return;
		}
		for(int i = 0; i < other_values.length; i++) {
			String next_num_str = num + other_values[i];
			int next_num = Integer.parseInt(next_num_str);
			if(isPrime(next_num)) {
				getPrimes(next_num_str, count + 1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		br.close();
		baekjun2023 b = new baekjun2023();
		for(int i = 0; i < first_primes.length; i++) {
			b.getPrimes(first_primes[i], 1);
		}
	}
}
