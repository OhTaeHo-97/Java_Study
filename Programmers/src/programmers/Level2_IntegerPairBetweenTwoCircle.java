package programmers;

import java.util.Arrays;

public class Level2_IntegerPairBetweenTwoCircle {
    public long solution(int r1, int r2) {
        long[] firstCirclePairCount = findIntegerPair(r1);
        long[] secondCirclePairCount = findIntegerPair(r2);

        return (secondCirclePairCount[0] - firstCirclePairCount[0]) + secondCirclePairCount[1];
    }

    private long[] findIntegerPair(int r) {
        long rSquare = (long) r * r;
        long[] pairCount = new long[2];

        for(long x = 1; x < r; x++) {
            long difference = rSquare - (x * x);
            double y = Math.sqrt(difference);
            long yLong = (long) y;
            if(y == yLong) {
                pairCount[1]++;
                pairCount[0] += (yLong - 1);
            } else {
                pairCount[0] += yLong;
            }
        }

        pairCount[0] *= 4;
        pairCount[0] += (r - 1) * 4;
        pairCount[1] *= 4;
        pairCount[1] += 4;
        return pairCount;
    }

    public static void main(String[] args) {
        Level2_IntegerPairBetweenTwoCircle l = new Level2_IntegerPairBetweenTwoCircle();
        System.out.println(l.solution(2, 3));
    }
}
