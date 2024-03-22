package src.programmers.kakao_winter_internship_2024;

import java.util.Arrays;

public class Level3_MountainShapeTiling {
    private final int DIVISOR = 10_007;

    public int solution(int n, int[] tops) {
        int[] oneCase = new int[n + 1];
        int[] otherCases = new int[n + 1];
        init(oneCase, otherCases);

        for(int idx = 1; idx <= n; idx++) {
            calculateTheNumberOfFillingTriangle(idx, tops[idx - 1], oneCase, otherCases);
        }

        return (oneCase[n] + otherCases[n]) % DIVISOR;
    }

    private void calculateTheNumberOfFillingTriangle(int index, int top, int[] oneCase, int[] otherCases) {
        calculateOneCase(index, oneCase, otherCases);
        calculateOtherCases(index, top == 1, oneCase, otherCases);
    }

    private void calculateOneCase(int index, int[] oneCase, int[] otherCases) {
        oneCase[index] = oneCase[index - 1] + otherCases[index - 1];
        oneCase[index] %= DIVISOR;
    }

    private void calculateOtherCases(int index, boolean hasTop, int[] oneCase, int[] otherCases) {
        if(hasTop) {
            otherCases[index] = oneCase[index - 1] * 2 + otherCases[index - 1] * 3;
        } else {
            otherCases[index] = oneCase[index - 1] + otherCases[index - 1] * 2;
        }
        otherCases[index] %= DIVISOR;
    }

    private void init(int[] oneCase, int[] otherCases) {
        oneCase[0] = 0;
        otherCases[0] = 1;
    }

    public static void main(String[] args) {
        Level3_MountainShapeTiling l = new Level3_MountainShapeTiling();
        System.out.println(l.solution(4, new int[] {1, 1, 0, 1}));
    }
}
