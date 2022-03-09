package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1124 {
	public boolean isPrime(int num) {
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int getUnderPrimeNum(int min, int max) {
		int count = 0;
		for(int i = min; i <= max; i++) {
			int num = i;
			ArrayList<Integer> primes = new ArrayList<Integer>();
			for(int j = 2; j <= Math.sqrt(i); j++) {
				while(num % j == 0) {
					num /= j;
					primes.add(j);
				}
			}
			if(num != 1) {
				primes.add(num);
			}
			if(primes.size() != 0 && primes.size() != 1 && isPrime(primes.size())) {
				count++;
			}
		}
		return count;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		br.close();
		StringTokenizer st = new StringTokenizer(input);
		int min = Integer.parseInt(st.nextToken());
		int max = Integer.parseInt(st.nextToken());
		baekjun1124 b = new baekjun1124();
		bw.write(b.getUnderPrimeNum(min, max) + "\n");
		bw.flush();
		bw.close();
	}
}
