package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun9576 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int N, M;
    static Student[] students;
    static boolean[] isGiven;

    static void input() {
        N = scanner.nextInt();
        M = scanner.nextInt();
        students = new Student[M];
        isGiven = new boolean[N + 1];

        for(int idx = 0; idx < M; idx++) {
            int start = scanner.nextInt(), end = scanner.nextInt();
            students[idx] = new Student(start, end);
        }
    }

    static void solution() {
        Arrays.sort(students);

        int answer = 0;

        for(int idx = 0; idx < M; idx++) {
            int start = students[idx].startNum, end = students[idx].endNum;

            for(int book = start; book <= end; book++) {
                if(!isGiven[book]) {
                    isGiven[book] = true;
                    answer++;
                    break;
                }
            }
        }

        sb.append(answer).append('\n');
    }

    static class Student implements Comparable<Student> {
        int startNum, endNum;

        public Student(int startNum, int endNum) {
            this.startNum = startNum;
            this.endNum = endNum;
        }

        @Override
        public int compareTo(Student s) {
            if(this.endNum != s.endNum) return this.endNum - s.endNum;
            return this.startNum - s.startNum;
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        while(T-- > 0) {
            input();
            solution();
        }

        System.out.print(sb);
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
