package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class baekjun1918 {
	static char[] expression;
    static void input() {
        Reader scanner = new Reader();
        expression = scanner.nextLine().toCharArray();
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        Stack<Character> operator = new Stack<>();
        for(char elem : expression) {
            if(elem == '(') {
                operator.push(elem);
            } else if(elem == ')') {
                while(!operator.isEmpty()) {
                    char op = operator.pop();
                    if(op == '(') break;
                    sb.append(op);
                }
            } else if(elem == '+' || elem == '-' || elem == '*' || elem == '/') {
                while(!operator.isEmpty() && getPriority(operator.peek()) >= getPriority(elem)) {
                    sb.append(operator.pop());
                }
                operator.push(elem);
            } else {
                sb.append(elem);
            }
        }

        while(!operator.isEmpty()) sb.append(operator.pop());

        System.out.println(sb);
    }

    static int getPriority(char op) {
        if(op == '(' || op == ')') return 0;
        else if(op == '+' || op == '-') return 1;
        else if(op == '*' || op == '/') return 2;
        return -1;
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;
        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
