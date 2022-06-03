package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SW10965 {
	// 시간 초과
//	public boolean isPrime(int num) {
//		boolean prime = true;
//		for(int i = 2; i <= Math.sqrt(num); i++) {
//			if(num % i == 0) {
//				prime = false;
//				break;
//			}
//		}
//		return prime;
//	}
//	
//	public int[] getSquareNum(int[] test_cases) {
//		int[] squareNum = new int[test_cases.length];
//		Arrays.fill(squareNum, 1);
//		for(int i = 0; i < test_cases.length; i++) {
//			HashMap<Integer, Integer> prime_fact = new HashMap<Integer, Integer>();
//			int num = 2;
//			while(true) {
//				if(isPrime(num)) {
//					if(test_cases[i] % num == 0) {
//						if(prime_fact.containsKey(num)) {
//							prime_fact.put(num, prime_fact.get(num) + 1);
//						} else {
//							prime_fact.put(num, 1);
//						}
//						test_cases[i] /= num;
//					} else {
//						num++;
//					}
//				} else {
//					num++;
//				}
//				if(isPrime(test_cases[i])) {
//					if(prime_fact.containsKey(test_cases[i])) {
//						prime_fact.put(test_cases[i], prime_fact.get(test_cases[i]) + 1);
//					} else {
//						prime_fact.put(test_cases[i], 1);
//					}
//					break;
//				}
//			}
//			for(int key : prime_fact.keySet()) {
//				if(prime_fact.get(key) % 2 != 0) {
//					squareNum[i] *= key;
//				}
//			}
//		}
//		return squareNum;
//	}
	
	// 시간 초과
//	public int[] getSquareNum(int[] test_cases) {
//		int[] squareNum = new int[test_cases.length];
//		for(int i = 0; i < test_cases.length; i++) {
//			for(int j = 1; j <= test_cases[i]; j++) {
//				if(Math.sqrt((long)test_cases[i] * j) == (int)Math.sqrt((long)test_cases[i] * j)) {
//					squareNum[i] = j;
//					break;
//				}
//			}
//		}
//		return squareNum;
//	}
	
	static ArrayList<Integer> prime;
	public static void findPrime() {
		prime = new ArrayList<Integer>();
		prime.add(2);
		for(int i = 3; i < Math.sqrt(10000000); i += 2) {
			boolean isPrime = true;
			for(int j : prime) {
				if(i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				prime.add(i);
			}
		}
	}
	
	public int getSquareNum(int test_case) {
		int result = 1;
		if(Math.sqrt(test_case) != (int)Math.sqrt(test_case)) {
			for(int i : prime) {
				int count = 0;
				while(test_case % i == 0) {
					test_case /= i;
					count++;
				}
				if(count % 2 != 0) {
					result *= i;
				}
				if(test_case == 1 || i > test_case) {
					break;
				}
			}
			if(test_case > 1) {
				result *= test_case;
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		SW10965 s = new SW10965();
		findPrime();
		for(int i = 1; i <= test_num; i++) {
			bw.write("#" + i + " " + s.getSquareNum(Integer.parseInt(br.readLine())) + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
