package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun2287 {
    static final int MAX_LENGTH = 8;

    static Set<Integer>[] possibleNumbers;
    static int k;
    static int testCnt;
    static int[] testCase;

    static void input() {
        Reader scanner = new Reader();

        k = scanner.nextInt();
        testCnt = scanner.nextInt();
        testCase = new int[testCnt];

        for (int idx = 0; idx < testCnt; idx++) {
            testCase[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        findAllPossibleNumbers();

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < testCnt; idx++) {
            sb.append(findMinLength(testCase[idx])).append('\n');
        }
        System.out.print(sb);
    }

    static String findMinLength(int targetNumber) {
        for (int len = 1; len <= MAX_LENGTH; len++) {
            if (possibleNumbers[len].contains(targetNumber)) {
                return String.valueOf(len);
            }
        }

        return "NO";
    }

    static void findAllPossibleNumbers() {
        init();

        for (int len = 2; len <= MAX_LENGTH; len++) {
            for (int basis = 1; basis <= len / 2; basis++) {
                calculatePossibleNumbersBetweenTwoSize(len, basis, len - basis);
                calculatePossibleNumbersBetweenTwoSize(len, len - basis, basis);
            }

            String kStr = String.valueOf(k);
            kStr = kStr.repeat(len);
            possibleNumbers[len].add(Integer.parseInt(kStr));
        }
    }

    static void init() {
        possibleNumbers = new HashSet[MAX_LENGTH + 1];
        for (int idx = 0; idx <= MAX_LENGTH; idx++) {
            possibleNumbers[idx] = new HashSet<>();
        }

        possibleNumbers[1].add(k);
    }

    static void calculatePossibleNumbersBetweenTwoSize(int length, int firstLength, int secondLength) {
        for (int firstLenPossibleNumber : possibleNumbers[firstLength]) {
            for (int secondLenPossibleNumber : possibleNumbers[secondLength]) {
                possibleNumbers[length].add(firstLenPossibleNumber + secondLenPossibleNumber);
                possibleNumbers[length].add(firstLenPossibleNumber - secondLenPossibleNumber);
                possibleNumbers[length].add(firstLenPossibleNumber * secondLenPossibleNumber);
                try {
                    possibleNumbers[length].add(firstLenPossibleNumber / secondLenPossibleNumber);
                } catch (Exception e) {
                }
            }
        }
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
            while (st == null || !st.hasMoreElements()) {
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
