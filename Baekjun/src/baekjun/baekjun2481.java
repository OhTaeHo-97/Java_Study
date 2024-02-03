package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun2481 {
    static int codeCount;
    static int length;
    static int questionCount;
    static int firstCode;
    static int[] visited;
    static int[] questions;
    static Map<Integer, Integer> map;

    static void input() {
        Reader scanner = new Reader();

        codeCount = scanner.nextInt();
        length = scanner.nextInt();
        visited = new int[codeCount + 1];
        Arrays.fill(visited, -1);
        map = new HashMap<>();

        for (int codeIdx = 1; codeIdx <= codeCount; codeIdx++) {
            int code = Integer.parseInt(scanner.next(), 2);
            map.put(code, codeIdx);
            if (codeIdx == 1) {
                firstCode = code;
            }
        }

        questionCount = scanner.nextInt();
        questions = new int[questionCount];
        for (int idx = 0; idx < questionCount; idx++) {
            questions[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        makeMap();

        StringBuilder answer = new StringBuilder();
        for (int idx = 0; idx < questionCount; idx++) {
            findPath(questions[idx], answer);
            answer.append('\n');
        }

        System.out.print(answer);
    }

    static void findPath(int targetIdx, StringBuilder answer) {
        if (visited[targetIdx] == -1) {
            answer.append(-1).append('\n');
            return;
        }

        Stack<Integer> stack = new Stack<>();
        int num = targetIdx;
        while (true) {
            stack.push(num);
            if (num == 1) {
                break;
            }
            num = visited[num];
        }

        while (!stack.isEmpty()) {
            answer.append(stack.pop()).append(' ');
        }
    }

    static void makeMap() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(firstCode);
        visited[1] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curIdx = map.get(cur);

            for (int idx = 0; idx < length; idx++) {
                int shiftedNum = cur ^ (1 << idx);
                if (map.containsKey(shiftedNum)) {
                    int nextIdx = map.get(shiftedNum);
                    if (visited[nextIdx] == -1) {
                        queue.offer(shiftedNum);
                        visited[nextIdx] = curIdx;
                    }
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
