package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun16637 {
	static int N, answer;
    static ArrayList<Integer> operands;
    static ArrayList<Character> operators;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        String expression = scanner.nextLine();
        operators = new ArrayList<>();
        operands = new ArrayList<>();
        for(int idx = 0; idx < N; idx++) {
            if(idx % 2 == 0) operands.add(expression.charAt(idx) - '0');
            else operators.add(expression.charAt(idx));
        }
    }

    static void solution() {
        answer = Integer.MIN_VALUE;
        dfs(operands.get(0), 0);
        System.out.println(answer);
    }

    static void dfs(int result, int index) {
        if(index >= operators.size()) {
            answer = Math.max(answer, result);
            return;
        }
        int temp = calculation(operators.get(index), result, operands.get(index + 1));
        dfs(temp, index + 1);
        if(index + 1 < operators.size()) {
            int temp2 = calculation(operators.get(index + 1), operands.get(index + 1), operands.get(index + 2));
            dfs(calculation(operators.get(index), result, temp2), index + 2);
        }
    }

    static int calculation(char operator, int operand1, int operand2) {
        if(operator == '+') return operand1 + operand2;
        else if(operator == '-') return operand1 - operand2;
        else if(operator == '*') return operand1 * operand2;
        return 0;
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
                } catch(IOException e) {
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
            } catch(IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
