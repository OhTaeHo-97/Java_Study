package programmers;

public class Level3_SumOfSequentialSubSequence {
	public static long solution(int[] sequence) {
        long[] prefixSum = new long[sequence.length + 1];
        long[] prefixSum2 = new long[sequence.length + 1];

        for(int idx = 1; idx <= sequence.length; idx++) {
            if(idx % 2 == 0)
                prefixSum[idx] = prefixSum[idx - 1] + sequence[idx - 1];
            else
                prefixSum[idx] = prefixSum[idx - 1] + sequence[idx - 1] * (-1);
        }

        for(int idx = 1; idx <= sequence.length; idx++) {
            if(idx % 2 == 0)
                prefixSum2[idx] = prefixSum2[idx - 1] + sequence[idx - 1] * (-1);
            else
                prefixSum2[idx] = prefixSum2[idx - 1] + sequence[idx - 1];
        }

        long max1 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE;
        long max2 = Integer.MIN_VALUE, min2 = Integer.MAX_VALUE;
        for(int idx = 0; idx < prefixSum.length; idx++) {
            max1 = Math.max(max1, prefixSum[idx]);
            min1 = Math.min(min1, prefixSum[idx]);

            max2 = Math.max(max2, prefixSum2[idx]);
            min2 = Math.min(min2, prefixSum2[idx]);
        }

        long max = Math.max(max1 - min1, max2 - min2);

        return max;
    }
	
	public static void main(String[] args) {
        System.out.println(solution(new int[] {2, 3, -6, 1, 3, -1, 2, 4}));
    }
}
