package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjun13904 {
	static int N, maxDeadline;
    static LinkedList<Assignment> assignments;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        assignments = new LinkedList<>();
        maxDeadline = Integer.MIN_VALUE;
        for(int assignment = 0; assignment < N; assignment++) {
            int deadline = scanner.nextInt(), score = scanner.nextInt();
            maxDeadline = Math.max(maxDeadline, deadline);
            assignments.add(new Assignment(deadline, score));
        }
    }

    static void solution() {
        int answer = 0;
        for(int day = maxDeadline; day > 0; day--)
            answer += getMaxScoreInThatDay(assignments, day);
        
        System.out.println(answer);
    }

    static int getMaxScoreInThatDay(LinkedList<Assignment> assignments, int day) {
        int maxIdx = -1, maxScore = 0;
        for(int assignment = 0; assignment < assignments.size(); assignment++) {
            Assignment cur = assignments.get(assignment);
            if(cur.deadline >= day && maxScore < cur.score) {
                maxIdx = assignment;
                maxScore = cur.score;
            }
        }

        if(maxScore == 0) return 0;

        assignments.remove(maxIdx);
        return maxScore;
    }

    static class Assignment {
        int deadline, score;
        public Assignment(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
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
