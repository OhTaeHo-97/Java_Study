package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun16638 {
    static int length, answer;
    static boolean[] brackets;
    static Element[] expression;

    static void input() {
        Reader scanner = new Reader();

        length = scanner.nextInt();
        answer = Integer.MIN_VALUE;
        brackets = new boolean[length];
        expression = new Element[length];
        String info = scanner.nextLine();

        for(int idx = 0; idx < length; idx++) {
            char element = info.charAt(idx);
            if(element == '*') expression[idx] = new Element(element, 2);
            else if(element == '+' || element == '-') expression[idx] = new Element(element, 3);
            else expression[idx] = new Element(element, 0);
        }
    }

    static void solution() {
        findMaxByBracket(1);
        System.out.println(answer);
    }

    static void findMaxByBracket(int index) {
        if(index >= length) {
            int expressionResult = postfix(inorderToPostOrder());
            answer = Math.max(answer, expressionResult);
            return;
        }

        if(index == 1) {
            brackets[index] = true;
            findMaxByBracket(index + 2);
            brackets[index] = false;
        } else {
            if(!brackets[index - 2]) {
                brackets[index] = true;
                findMaxByBracket(index + 2);
                brackets[index] = false;
            }
        }
        findMaxByBracket(index + 2);
    }

    static List<Element> inorderToPostOrder() {
        List<Element> postOrder = new ArrayList<>();
        Stack<Element> stack = new Stack<>();

        for(int idx = 0; idx < length; idx++) {
            if(expression[idx].priority == 0) postOrder.add(expression[idx]);
            else {
                int priority = expression[idx].priority;
                if(brackets[idx]) priority = 1;
                while(!stack.isEmpty() && stack.peek().priority <= priority)
                    postOrder.add(stack.pop());

                stack.push(new Element(expression[idx].element, priority));
            }
        }

        while(!stack.isEmpty()) postOrder.add(stack.pop());

        return postOrder;
    }

    static int postfix(List<Element> postOrder) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;

        for(int idx = 0; idx < length; idx++) {
            if(postOrder.get(idx).priority == 0) stack.push(postOrder.get(idx).element - '0');
            else {
                int n1 = stack.pop();
                int n2 = stack.pop();

                result = calculate(n2, n1, postOrder.get(idx).element);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    static int calculate(int n1, int n2, char operator) {
        int result = 0;
        switch(operator) {
            case '+':
                result = n1 + n2;
                break;
            case '-':
                result = n1 - n2;
                break;
            case '*':
                result = n1 * n2;
                break;
        }

        return result;
    }

    static class Element {
        char element;
        int priority;

        public Element(char element, int priority) {
            this.element = element;
            this.priority = priority;
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
