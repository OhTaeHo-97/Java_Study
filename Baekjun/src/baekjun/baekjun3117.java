package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun3117 {
    static final int BASE = 2;

    static int studentCount;
    static int videoCount;
    static int time;
    static int logTime;

    static int[] startVideoInfos;
    static int[][] topRecommendationVideo;

    static void input() {
        Reader scanner = new Reader();

        studentCount = scanner.nextInt();
        videoCount = scanner.nextInt();
        time = scanner.nextInt();
        logTime = calculateLog(time, BASE);
        startVideoInfos = new int[studentCount];
        topRecommendationVideo = new int[logTime + 1][videoCount + 1];

        for (int student = 0; student < studentCount; student++) {
            startVideoInfos[student] = scanner.nextInt();
        }

        for (int video = 1; video <= videoCount; video++) {
            topRecommendationVideo[0][video] = scanner.nextInt();
        }
    }

    static int calculateLog(int number, int base) {
        return (int) (Math.log(number) / Math.log(base));
    }

    static void solution() {
        makeSparseTree();

        int[] answer = new int[studentCount];
        for (int student = 0; student < studentCount; student++) {
            answer[student] = findVideoAfterMTimes(startVideoInfos[student], time - 1);
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(answer).forEach(video -> sb.append(String.format("%d ", video)));
        System.out.println(sb);
    }

    static void makeSparseTree() {
        for (int t = 1; t <= logTime; t++) {
            for (int video = 1; video <= videoCount; video++) {
                int nextVideo = topRecommendationVideo[t - 1][video];
                topRecommendationVideo[t][video] = topRecommendationVideo[t - 1][nextVideo];
            }
        }
    }

    static int findVideoAfterMTimes(int video, int time) {
        for (int bit = logTime; bit >= 0; bit--) {
            if ((time & (1 << bit)) != 0) {
                video = topRecommendationVideo[bit][video];
            }
        }

        return video;
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
