package programmers;

import java.util.Arrays;
import java.util.HashSet;

public class Level3_CountDown {
	int[][] dp;
    HashSet<Integer> singleAndBull, doubleAndTriple;
    public int[] solution(int target) {
        init(target);
        return getMinDartNum(target);
    }

    public void init(int target) {
        dp = new int[target + 1][2];

        for(int score = 1; score <= target; score++)
            dp[score][0] = Integer.MAX_VALUE;

        singleAndBull = new HashSet<>(); doubleAndTriple = new HashSet<>();
        singleAndBull.add(50);
        for(int point = 1; point <= 20; point++)
            singleAndBull.add(point);

        for(int point = 1; point <= 20; point++) {
            for(int muiltiple = 2; muiltiple <= 3; muiltiple++) {
                if(point * muiltiple <= 20) continue;
                doubleAndTriple.add(point * muiltiple);
            }
        }
    }

    public int[] getMinDartNum(int point) {
        if(point == 0) return new int[] {0, 0};
        if(point < 0) return new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        if(dp[point][0] != Integer.MAX_VALUE) return dp[point];

        int[] result = new int[] {Integer.MAX_VALUE, 0};
        for(int sb : singleAndBull) {
            int[] midResult = getMinDartNum(point - sb);
            if(midResult[0] == Integer.MAX_VALUE) continue;
            findMin(result, new int[] {midResult[0] + 1, midResult[1] + 1});
        }
        for(int dt : doubleAndTriple) {
            int[] midResult = getMinDartNum(point - dt);
            if(midResult[0] == Integer.MAX_VALUE) continue;
            findMin(result, new int[] {midResult[0] + 1, midResult[1]});
        }

        return dp[point] = result.clone();
    }

    public void findMin(int[] result, int[] midResult) {
        if(result[0] > midResult[0])
            System.arraycopy(midResult, 0, result, 0, midResult.length);
        else if(result[0] == midResult[0])
            if(result[1] < midResult[1]) result[1] = midResult[1];
    }

    public static void main(String[] args) {
        Level3_CountDown l = new Level3_CountDown();
        System.out.println(Arrays.toString(l.solution(21)));
    }
}
