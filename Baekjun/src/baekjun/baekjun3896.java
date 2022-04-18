package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun3896 {
	public boolean isPrime(int num) {
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public int[] getPrimeLength(int[] primes) {
		int[] prime_length = new int[primes.length];
		for(int i = 0; i < primes.length; i++) {
			if(isPrime(primes[i])) {
				prime_length[i] = 0;
			} else {
				int count1 = 0;
				int count2 = 0;
				int upper = primes[i] + 1;
				int lower = primes[i] - 1;
				boolean flag1 = false;
				boolean flag2 = false;
				while(true) {
					if(!flag1) {
						if(isPrime(upper)) {
							flag1 = true;
						} else {
							count1++;
							upper++;
						}
					}
					if(!flag2) {
						if(isPrime(lower)) {
							flag2 = true;
						} else {
							count2++;
							lower--;
						}
					}
					if(flag1 && flag2) {
						prime_length[i] = count1 + count2 + 2;
						break;
					}
				}
			}
		}
		return prime_length;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		int[] primes = new int[num];
		for(int i = 0; i < primes.length; i++) {
			primes[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun3896 b = new baekjun3896();
		int[] prime_length = b.getPrimeLength(primes);
		for(int i = 0; i < prime_length.length; i++) {
			bw.write(prime_length[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
