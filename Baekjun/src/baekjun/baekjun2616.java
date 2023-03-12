package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2616 {
	static final int LOCOMOTIVENUM = 3;
    static int passengerCarNum, maxPullNum;
    static int[] guestNum, prefixSum;
    static int[][] dp;
    
    static void input() {
    	Reader scanner = new Reader();
    	
    	passengerCarNum = scanner.nextInt();
    	guestNum = new int[passengerCarNum + 1];
    	prefixSum = new int[passengerCarNum + 1];
    	
    	for(int passengerCar = 1; passengerCar <= passengerCarNum; passengerCar++) {
    		guestNum[passengerCar] = scanner.nextInt();
    		prefixSum[passengerCar] = prefixSum[passengerCar - 1] + guestNum[passengerCar];
    	}
    	
    	maxPullNum = scanner.nextInt();
    }
    
    static void solution() {
    	dp = new int[LOCOMOTIVENUM + 1][passengerCarNum + 1];
    	
    	for(int locomotive = 1; locomotive <= LOCOMOTIVENUM; locomotive++) {
    		for(int passengerCar = locomotive * maxPullNum; passengerCar <= passengerCarNum; passengerCar++)
    			dp[locomotive][passengerCar] = Math.max(dp[locomotive][passengerCar - 1],
    					dp[locomotive - 1][passengerCar - maxPullNum] + prefixSum[passengerCar] - prefixSum[passengerCar - maxPullNum]);
    	}
    	
    	System.out.println(dp[LOCOMOTIVENUM][passengerCarNum]);
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
