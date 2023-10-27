package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun9460 {
    static final double MIN_DIST = 0;
    static final double MAX_DIST = 200_000_000;

    static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int metalCnt; // 금속 개수
    static int tunnelCnt; // 수평 터널 개수
    static List<Metal> metals; // 금속 정보

    static void input() {
        metalCnt = scanner.nextInt();
        tunnelCnt = scanner.nextInt();
        metals = new ArrayList<>();

        for(int cnt = 0; cnt < metalCnt; cnt++) {
            metals.add(new Metal(scanner.nextInt(), scanner.nextInt()));
        }
    }

    /*
     * 금속 개수보다 터널 개수가 크거나 같다면 각 금속에 맞게 터널을 두면 되기 때문에 모든 cost(P_i)가 0이 된다
     * 그렇지 않다면 cost(P) 값을 이분 탐색을 이용하여 구할 수 있다
     *  - 금속들 사이에 터널을 둘 것인데, 우선 금속들 사이의 거리를 살펴본다
     *  - 금속들 사이 거리의 최솟값은 y값이 같은 0, 최댓값은 y값이 각각 최소와 최대인 200_000_000이 된다
     *  - 그러므로 처음 최솟값은 0, 최댓값은 200_000_000으로 설정하여 이중 탐색을 진행한다
     *  - 이중 탐색 과정 중 나오는 cost(P) 값을 C라고 할 때, 연속되는 금속들의 최대 거리가 C * 2 이하인 것들을 하나의 그룹으로 묶는다
     *  - 만약 C * 2보다 커지는 시점이 생긴다면 그 시점의 금속부터 다음 그룹을 만든다
     *  - 이렇게 구한 그룹의 개수가 터널 개수보다 작거나 같다면 cost(P)를 더 줄여도 되는 상황이니 최댓값을 C로 변경한다
     *  - 반대로 그룹의 개수가 터널 개수보다 크면 cost(P)를 더 늘려야 하는 상황이므로 최솟값을 C로 변경한다
     */
    static void solution() {
        if(metalCnt <= tunnelCnt) {
            sb.append(0.0).append('\n');
            return;
        }

        Collections.sort(metals);

        sb.append(String.format("%.1f", Math.abs(binarySearch()))).append('\n');
    }

    static double binarySearch() {
        double min = MIN_DIST;
        double max = MAX_DIST;

        // 반올림해서 첫째 자리까지 출력해야하므로 답에 영향을 미치는 것은 소수점 둘째자리까지이다
        // 그러므로 최댓값과 최솟값 사이의 차이가 0.01보다 큰 동안 이분 탐색을 진행한다
        while(0.01 < max - min) {
            double mid = (min + max) / 2;
            int groupCnt = findGroupNum(mid);

            if(groupCnt <= tunnelCnt) {
                max = mid;
            } else {
                min = mid;
            }
        }

        return max;
    }

    static int findGroupNum(double dist) {
        int groupCnt = 1;
        int min = metals.get(0).y;
        int max = metals.get(0).y;

        for(int idx = 1; idx < metals.size(); idx++) {
            if(metals.get(idx).y < min) {
                min = metals.get(idx).y;
            }
            if(metals.get(idx).y > max) {
                max = metals.get(idx).y;
            }

            if(dist * 2 < (max - min)) {
                min = metals.get(idx).y;
                max = metals.get(idx).y;
                groupCnt++;
            }
        }

        return groupCnt;
    }

    static class Metal implements Comparable<Metal> {
        int x;
        int y;

        public Metal(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Metal o) {
            return x - o.x;
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
