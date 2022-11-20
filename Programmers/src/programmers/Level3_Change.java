package programmers;

public class Level3_Change {
	public static int solution(int n, int[] money) {
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for(int obj = 1; obj <= n; obj++) {
			if(obj % money[0] == 0) dp[obj] = 1;
		}
		for(int idx = 1; idx < money.length; idx++) {
			for(int obj = money[idx]; obj <= n; obj++) {
				dp[obj] += (dp[obj - money[idx]]) % 1000000007;
				dp[obj] %= 1000000007;
			}
		}
		return dp[n];
	}
	
	public static void main(String[] args) {
		int n = 5;
		int[] money = {1, 2, 5};
		System.out.println(solution(n, money));
	}
}
