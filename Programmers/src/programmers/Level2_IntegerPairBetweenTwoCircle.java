package programmers;

public class Level2_IntegerPairBetweenTwoCircle {
    public long solution(int r1, int r2) {
//        long[] firstCirclePairCount = findIntegerPair(r1);
//        long[] secondCirclePairCount = findIntegerPair(r2);
//        return (secondCirclePairCount[0] - firstCirclePairCount[0]) + secondCirclePairCount[1];
        return findIntegerPairCount(r1, r2);
    }

    private long findIntegerPairCount(int r1, int r2) {
        long pairCount = 0L;

        double r1Pow = Math.pow(r1, 2);
        double r2Pow = Math.pow(r2, 2);

        int onAxis = (r2 - r1 + 1) * 4;

        for(int x = 0; x <= r2; x++) {
            double xPow = Math.pow(x, 2);

            double y1 = 0;
            if(x < r1) {
                y1 = Math.sqrt(r1Pow - xPow);
                if(y1 > Math.floor(y1)) {
                    y1 = Math.ceil(y1);
                }
            }

            double y2 = Math.sqrt(r2Pow - xPow);
            if(y2 > Math.floor(y2)) {
                y2 = Math.floor(y2);
            }

            pairCount += (int) y2 - (int) y1 + 1;
        }

        return pairCount * 4 - onAxis;
    }

//    private long[] findIntegerPair(int r) {
//        long rSquare = (long) r * r;
//        long[] pairCount = new long[2];
//
//        for(long x = 1; x < r; x++) {
//            long difference = rSquare - (x * x);
//            double y = Math.sqrt(difference);
//            long yLong = (long) y;
//            if(y == yLong) {
//                pairCount[1]++;
//                pairCount[0] += (yLong - 1);
//            } else {
//                pairCount[0] += yLong;
//            }
//        }
//
//        pairCount[0] *= 4;
//        pairCount[0] += (r - 1) * 4;
//        pairCount[1] *= 4;
//        pairCount[1] += 4;
//        return pairCount;
//    }

    public static void main(String[] args) {
        Level2_IntegerPairBetweenTwoCircle l = new Level2_IntegerPairBetweenTwoCircle();
        System.out.println(l.solution(2, 3));
    }
}
