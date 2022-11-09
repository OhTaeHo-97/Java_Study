package programmers;

public class Level3_GatheringSticker2 {
	public static int solution(int sticker[]) {
        if(sticker.length == 1)
            return sticker[0];
		// 두 번째 스티커부터 시작
        int[] dp = new int[sticker.length + 2];
        for (int i = 3; i < dp.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i - 2]);
        int secondMax = dp[dp.length - 1];
        // 첫 번째 스티커부터 시작
        for (int i = 2; i < dp.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i - 2]);
        int firstMax = dp[dp.length - 2];
        return Math.max(firstMax, secondMax);
    }
	
	public static void main(String[] args) {
		int[] sticker = {14, 6, 5, 11, 3, 9, 2, 10};
		System.out.println(solution(sticker));
	}
}
