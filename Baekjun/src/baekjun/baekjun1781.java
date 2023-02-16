package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1781 {
	static int N;
    static PriorityQueue<Problem> problems;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        problems = new PriorityQueue<>();

        for (int problem = 0; problem < N; problem++) {
            int deadline = scanner.nextInt(), cupNum = scanner.nextInt();
            problems.offer(new Problem(deadline, cupNum));
        }
    }

    static void solution() {
        PriorityQueue<Integer> answerList = new PriorityQueue<>();

        for(int idx = 0; idx < N; idx++) {
            Problem cur = problems.poll();
            answerList.add(cur.cupNum);

            if(cur.deadline < answerList.size()) {
                answerList.poll();
            }
        }

        int answer = 0;
        while(!answerList.isEmpty()) {
            answer += answerList.poll();
        }

        System.out.println(answer);
    }

    static class Problem implements Comparable<Problem> {
        int deadline, cupNum;

        public Problem(int deadline, int cupNum) {
            this.deadline = deadline;
            this.cupNum = cupNum;
        }

        @Override
        public int compareTo(Problem p) {
            if(deadline != p.deadline) return deadline- p.deadline;
            return p.cupNum - cupNum;
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
    }
}
