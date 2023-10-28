package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2026 {
    static StringBuilder sb = new StringBuilder();
    static int studentCnt;
    static int picnicStudentCnt;
    static int relationCnt;
    static boolean isFinished;
    static int[][] relation;
    static int[] inDegree;
    static boolean[] visited;

    static void input() {
        Reader scanner = new Reader();

        picnicStudentCnt = scanner.nextInt();
        studentCnt = scanner.nextInt();
        relationCnt = scanner.nextInt();
        relation = new int[studentCnt + 1][studentCnt + 1];
        inDegree = new int[studentCnt + 1];
        visited = new boolean[studentCnt + 1];

        for (int idx = 0; idx < relationCnt; idx++) {
            int student1 = scanner.nextInt();
            int student2 = scanner.nextInt();

            relation[student1][student2] = 1;
            relation[student2][student1] = 1;
            inDegree[student1]++;
            inDegree[student2]++;
        }
    }

    static void solution() {
        for (int student = 1; student <= studentCnt; student++) {
            if (inDegree[student] < picnicStudentCnt - 1) {
                continue;
            }
            if (isFinished) {
                break;
            }

            visited[student] = true;
            dfs(student, 1);
            visited[student] = false;
        }

        if (!isFinished) {
            System.out.println(-1);
        } else {
            System.out.print(sb);
        }
    }

    static void dfs(int curStudent, int cnt) {
        if (isFinished) {
            return;
        }

        if (cnt == picnicStudentCnt) {
            print();
            isFinished = true;
            return;
        }

        for (int student = curStudent + 1; student <= studentCnt; student++) {
            if (relation[curStudent][student] == 1 && isFriend(student)) {
                visited[student] = true;
                dfs(student, cnt + 1);
                visited[student] = false;
            }
        }
    }

    static boolean isFriend(int targetStudent) {
        for (int student = 1; student <= studentCnt; student++) {
            if (visited[student] && relation[targetStudent][student] != 1) {
                return false;
            }
        }

        return true;
    }

    static void print() {
        for (int student = 1; student <= studentCnt; student++) {
            if (visited[student]) {
                sb.append(student).append('\n');
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
