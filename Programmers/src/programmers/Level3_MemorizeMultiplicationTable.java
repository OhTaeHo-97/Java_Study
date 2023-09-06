package src.programmers;

import java.util.Arrays;

public class Level3_MemorizeMultiplicationTable {
    public int[] solution(int e, int[] starts) {
        int[] numbers = findAllNumberDivisorNum(e);
        int[] maxNums = findAllMaxNumber(e, numbers);

        int[] answer = new int[starts.length];
        for(int idx = 0; idx < starts.length; idx++) {
            answer[idx] = maxNums[starts[idx]];
        }

        return answer;
    }

    public int[] findAllMaxNumber(int e, int[] numbers) {
        int maxNum = 0;
        int[] maxNums = new int[numbers.length];

        for(int num = e; num >= 1; num--) {
            if(numbers[num] >= maxNum) {
                maxNum = numbers[num];
                maxNums[num] = num;
            } else {
                maxNums[num] = maxNums[num + 1];
            }
        }

        return maxNums;
    }

    public int[] findAllNumberDivisorNum(int e) {
        int[] numbers = new int[e + 1];

        // 초기화
        Arrays.fill(numbers, 1);
        numbers[0] = 0;

        // 모든 숫자들의 약수의 개수 구하기
        for(int num = 2; num <= e; num++) {
            for(int divisor = 1; divisor <= e / num; divisor++) {
                numbers[num * divisor]++;
            }
        }

        return numbers;
    }

    public static void main(String[] args) {
        Level3_MemorizeMultiplicationTable l = new Level3_MemorizeMultiplicationTable();
        System.out.println(Arrays.toString(l.solution(8, new int[] {1, 3, 7})));
    }
}
