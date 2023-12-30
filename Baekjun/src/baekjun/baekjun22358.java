package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun22358 {
    static int pointCount;
    static int courseCount;
    static int possibleLiftCount;
    static int startPointNum;
    static int targetPointNum;
    static Map<Integer, List<Course>> courses;
    static Map<Integer, List<Course>> lifts;

    static void input() {
        Reader scanner = new Reader();

        pointCount = scanner.nextInt();
        courseCount = scanner.nextInt();
        possibleLiftCount = scanner.nextInt();
        startPointNum = scanner.nextInt();
        targetPointNum = scanner.nextInt();
        courses = new HashMap<>();
        lifts = new HashMap<>();
        for (int point = 1; point <= pointCount; point++) {
            courses.put(point, new ArrayList<>());
            lifts.put(point, new ArrayList<>());
        }

        for (int course = 0; course < courseCount; course++) {
            int startPoint = scanner.nextInt();
            int endPoint = scanner.nextInt();
            int time = scanner.nextInt();

            courses.get(startPoint).add(new Course(endPoint, time, 0));
            lifts.get(endPoint).add(new Course(startPoint, time, 0));
        }
    }

    static void solution() {
        long[][] times = bfs(startPointNum);
        long maxTime = Arrays.stream(times[targetPointNum]).max().getAsLong();
        System.out.println(maxTime == Long.MIN_VALUE ? -1 : maxTime);
    }

    static long[][] bfs(int startPoint) {
        Queue<Course> queue = new LinkedList<>();
        long[][] times = new long[pointCount + 1][possibleLiftCount + 1];
        for (int row = 0; row < times.length; row++) {
            Arrays.fill(times[row], Long.MIN_VALUE);
        }

        queue.offer(new Course(startPoint, 0, 0));
        times[startPoint][0] = 0;

        while (!queue.isEmpty()) {
            Course cur = queue.poll();

            for (Course next : courses.get(cur.point)) {
                if (times[next.point][cur.liftCount] < cur.time + next.time) {
                    times[next.point][cur.liftCount] = cur.time + next.time;
                    queue.offer(new Course(next.point, times[next.point][cur.liftCount], cur.liftCount));
                }
            }

            if (cur.liftCount < possibleLiftCount) {
                for (Course next : lifts.get(cur.point)) {
                    if (times[next.point][cur.liftCount + 1] < times[cur.point][cur.liftCount]) {
                        times[next.point][cur.liftCount + 1] = times[cur.point][cur.liftCount];
                        queue.offer(new Course(next.point, times[next.point][cur.liftCount + 1], cur.liftCount + 1));
                    }
                }
            }
        }

        return times;
    }

    static class Course {
        int point;
        long time;
        int liftCount;

        public Course(int point, long time, int liftCount) {
            this.point = point;
            this.time = time;
            this.liftCount = liftCount;
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
