package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1508 {
    static int length;
    static int refereeCount;
    static int pointCount;
    static int[] points;
    static int[] status;

    static void input() {
        Reader scanner = new Reader();

        length = scanner.nextInt();
        refereeCount = scanner.nextInt();
        pointCount = scanner.nextInt();
        points = new int[pointCount];

        for (int idx = 0; idx < pointCount; idx++) {
            points[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        int minDistance = binarySearch();
        findAssignedRefereeCount(minDistance, true);
        printAnswer();
    }

    static void printAnswer() {
        StringBuilder answer = new StringBuilder();
        int count = 0;
        for (int idx = 0; idx < pointCount; idx++) {
            if (count >= refereeCount) {
                answer.append(0);
                continue;
            }

            answer.append(status[idx]);
            if (status[idx] == 1) {
                count++;
            }
        }
        System.out.println(answer);
    }

    static int binarySearch() {
        int left = 0;
        int right = length;

        while (left <= right) {
            int mid = (left + right) / 2;
            int assignedRefereeCount = findAssignedRefereeCount(mid, false);

            if (assignedRefereeCount >= refereeCount) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    static int findAssignedRefereeCount(int minDistance, boolean isFindingAnswerProceed) {
        int answer = 0;

        for (int basisIdx = 0; basisIdx < pointCount; basisIdx++) {
            status = new int[pointCount];
            status[basisIdx] = 1;
            int count = 1;
            int curPoint = points[basisIdx];
            for (int otherIdx = basisIdx + 1; otherIdx < pointCount; otherIdx++) {
                if (points[otherIdx] - curPoint >= minDistance) {
                    status[otherIdx] = 1;
                    curPoint = points[otherIdx];
                    count++;
                }
            }

            if (isFindingAnswerProceed && count >= refereeCount) {
                return count;
            }
            answer = Math.max(answer, count);
        }

        return answer;
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
