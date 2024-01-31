package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun22345 {
    static int villageCount;
    static int candidateCount;
    static int[] candidates;
    static long[] peopleCountSum;
    static long[] weightSum;
    static List<Village> villages;

    static void input() {
        Reader scanner = new Reader();

        villageCount = scanner.nextInt();
        candidateCount = scanner.nextInt();
        villages = new ArrayList<>();
        peopleCountSum = new long[villageCount + 1];
        weightSum = new long[villageCount + 1];

        for (int count = 0; count < villageCount; count++) {
            villages.add(new Village(scanner.nextInt(), scanner.nextInt()));
        }

        candidates = new int[candidateCount];
        for (int idx = 0; idx < candidateCount; idx++) {
            candidates[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        Collections.sort(villages);
        init();

        StringBuilder answer = new StringBuilder();

        for (int idx = 0; idx < candidateCount; idx++) {
            int target = candidates[idx];
            if (target < villages.get(0).position) {
                answer.append(weightSum[villageCount] - (target * peopleCountSum[villageCount])).append('\n');
                continue;
            }
            if (target > villages.get(villageCount - 1).position) {
                answer.append((target * peopleCountSum[villageCount]) - weightSum[villageCount]).append('\n');
                continue;
            }

            int index = binarySearch(candidates[idx]);
            answer.append(
                    (target * peopleCountSum[index] + (weightSum[villageCount] - weightSum[index])) - (weightSum[index]
                            + target * (peopleCountSum[villageCount] - peopleCountSum[index]))).append('\n');
        }

        System.out.print(answer);
    }

    static int binarySearch(int target) {
        int left = 0;
        int right = villageCount - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            Village village = villages.get(mid);

            if (village.position == target) {
                return mid;
            }

            if (village.position < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static void init() {
        for (int idx = 0; idx < villageCount; idx++) {
            peopleCountSum[idx + 1] = peopleCountSum[idx] + villages.get(idx).peopleCount;
            weightSum[idx + 1] = weightSum[idx] + (villages.get(idx).peopleCount * villages.get(idx).position);
        }
    }

    static class Village implements Comparable<Village> {
        int peopleCount;
        int position;

        public Village(int peopleCount, int position) {
            this.peopleCount = peopleCount;
            this.position = position;
        }

        @Override
        public int compareTo(Village o) {
            return position - o.position;
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
