package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun16639 {
    static int N;
    static char[] expression;
    static int[][] min, max;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        min = new int[N][N];
        max = new int[N][N];
        for(int idx = 0; idx < N; idx++) {
            Arrays.fill(min[idx], Integer.MAX_VALUE);
            Arrays.fill(max[idx], Integer.MIN_VALUE);
        }

        String expressionStr = scanner.nextLine();
        expression = expressionStr.toCharArray();
        for(int idx = 0; idx < N; idx += 2) {
            int operand = expressionStr.charAt(idx) - '0';
            min[idx][idx] = max[idx][idx] = operand;
        }
    }

    static void solution() {
        for(int endLength = 2; endLength < N; endLength += 2) {
            for(int start = 0; start < N - endLength; start += 2) {
                for(int middleLength = 2; middleLength <= endLength; middleLength += 2) {
                    char operator = expression[start + middleLength - 1];
                    List<Integer> tentativeResults = new ArrayList<>();

                    tentativeResults.add(calculate(min[start][start + middleLength - 2], min[start + middleLength][start + endLength], operator));
                    tentativeResults.add(calculate(min[start][start + middleLength - 2], max[start + middleLength][start + endLength], operator));
                    tentativeResults.add(calculate(max[start][start + middleLength - 2], min[start + middleLength][start + endLength], operator));
                    tentativeResults.add(calculate(max[start][start + middleLength - 2], max[start + middleLength][start + endLength], operator));

                    Collections.sort(tentativeResults);

                    min[start][start + endLength] = Math.min(min[start][start + endLength], tentativeResults.get(0));
                    max[start][start + endLength] = Math.max(max[start][start + endLength], tentativeResults.get(tentativeResults.size() - 1));
                }
            }
        }

        System.out.println(max[0][N - 1]);
    }

    static int calculate(int operand1, int operand2, char operator) {
        if (operator == '+') {
            return operand1 + operand2;
        } else if(operator == '-') {
            return operand1 - operand2;
        } else {
            return operand1 * operand2;
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

        String nextLine() {
            String str = "";

            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
