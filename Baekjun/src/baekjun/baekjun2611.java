package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun2611 {
    static int pointCount;
    static int roadCount;
    static int[] inDegree;
    static int[] path;
    static List<Integer> paths;
    static Map<Integer, List<Road>> roads;

    static void input() {
        Reader scanner = new Reader();

        pointCount = scanner.nextInt();
        roadCount = scanner.nextInt();
        inDegree = new int[pointCount + 1];
        path = new int[pointCount + 1];
        paths = new ArrayList<>();
        roads = new HashMap<>();
        for (int point = 1; point <= pointCount; point++) {
            roads.put(point, new ArrayList<>());
        }

        for (int road = 0; road < roadCount; road++) {
            int startPoint = scanner.nextInt();
            int endPoint = scanner.nextInt();
            int score = scanner.nextInt();
            roads.get(startPoint).add(new Road(endPoint, score));
            inDegree[endPoint]++;
        }
    }

    static void solution() {
        int maxScore = calculateMaxScore(1);
        print(maxScore);
    }

    static void print(int maxScore) {
        StringBuilder answer = new StringBuilder();
        answer.append(maxScore).append('\n');
        printPath(1);
        for (int idx = paths.size() - 1; idx >= 0; idx--) {
            answer.append(paths.get(idx)).append(' ');
        }
        System.out.println(answer);
    }

    static void printPath(int point) {
        paths.add(point);
        if (path[point] == 1) {
            paths.add(1);
        } else {
            printPath(path[point]);
        }
    }

    static int calculateMaxScore(int startPoint) {
        Queue<Road> queue = new LinkedList<>();
        int[] scores = new int[pointCount + 1];
        int maxScore = 0;

        queue.offer(new Road(startPoint, 0));
        scores[startPoint] = 0;
        while (!queue.isEmpty()) {
            Road cur = queue.poll();
            if (cur.point == startPoint && cur.score != 0) {
                maxScore = cur.score;
                break;
            }

            for (Road next : roads.get(cur.point)) {
                int nextPoint = next.point;
                int nextScore = next.score + cur.score;
                if (nextScore > scores[nextPoint]) {
                    scores[nextPoint] = nextScore;
                    path[nextPoint] = cur.point;
                }
                if (--inDegree[nextPoint] == 0) {
                    queue.offer(new Road(nextPoint, scores[nextPoint]));
                }
            }
        }

        return maxScore;
    }

    static class Road {
        int point;
        int score;

        public Road(int point, int score) {
            this.point = point;
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
