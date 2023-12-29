package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun16491 {
    static int robotCount;
    static double[][] robots;
    static double[][] shelters;
    static int[] shelterByRobot;

    static void input() {
        Reader scanner = new Reader();

        robotCount = scanner.nextInt();
        robots = new double[robotCount][2];
        shelters = new double[robotCount][2];
        shelterByRobot = new int[robotCount];

        for (int robotIdx = 0; robotIdx < robotCount; robotIdx++) {
            robots[robotIdx][0] = scanner.nextInt();
            robots[robotIdx][1] = scanner.nextInt();
        }

        for (int shelterIdx = 0; shelterIdx < robotCount; shelterIdx++) {
            shelters[shelterIdx][0] = scanner.nextInt();
            shelters[shelterIdx][1] = scanner.nextInt();
        }
    }

    static void solution() {
        backtracking(0, new boolean[robotCount]);
    }

    static void backtracking(int robotIdx, boolean[] visited) {
        if (robotIdx >= robotCount) {
            if (isNotCross()) {
                StringBuilder answer = new StringBuilder();
                for (int idx = 0; idx < robotCount; idx++) {
                    answer.append(shelterByRobot[idx] + 1).append('\n');
                }
                System.out.print(answer);
                System.exit(0);
            }
            return;
        }

        for (int shelterIdx = 0; shelterIdx < robotCount; shelterIdx++) {
            if (!visited[shelterIdx]) {
                shelterByRobot[robotIdx] = shelterIdx;
                visited[shelterIdx] = true;
                backtracking(robotIdx + 1, visited);
                visited[shelterIdx] = false;
            }
        }
    }

    static boolean isNotCross() {
        double[] slope = new double[robotCount];
        double[] yIntercept = new double[robotCount];

        for (int idx = 0; idx < robotCount; idx++) {
            double[] robot = robots[idx];
            double[] shelter = shelters[shelterByRobot[idx]];

            slope[idx] = (robot[1] - shelter[1]) / (robot[0] - shelter[0]);
            yIntercept[idx] = robot[1] - (slope[idx] * robot[0]);
        }

        for (int basis = 0; basis < robotCount - 1; basis++) {
            for (int other = basis + 1; other < robotCount; other++) {
                double x = (yIntercept[other] - yIntercept[basis]) / (slope[basis] - slope[other]);
                double y = slope[basis] * x + yIntercept[basis];

                double minX1 = Math.min(robots[basis][0], shelters[shelterByRobot[basis]][0]);
                double maxX1 = Math.max(robots[basis][0], shelters[shelterByRobot[basis]][0]);
                double minX2 = Math.min(robots[other][0], shelters[shelterByRobot[other]][0]);
                double maxX2 = Math.max(robots[other][0], shelters[shelterByRobot[other]][0]);

                if (!((minX1 > x || maxX1 < x) || (minX2 > x || maxX2 < x))) {
                    return false;
                }
            }
        }

        return true;
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
