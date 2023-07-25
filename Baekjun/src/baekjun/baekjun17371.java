package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun17371 {
    static int N;
    static List<Loc> facilities;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        facilities = new ArrayList<>();

        for(int idx = 0; idx < N; idx++)
            facilities.add(new Loc(scanner.nextInt(), scanner.nextInt()));
    }

    static void solution() {
        int minDistance = Integer.MAX_VALUE, minIdx = 0;

        // 이 문제에서 찾고자 하는 것은 가장 가까운 편의시설까지의 거리와 가장 먼 편의시설까지의 거리의 평균이 최소가 되는 지점
        // 평균이 최소가 되는 지점이 어디일지 생각해보면 가장 가까운 거리를 0으로 만들고 가장 먼 거리와의 평균을 구하는 지점이 될 것임
        // 문제에서 집의 좌표가 편의시설의 좌표면 안 된다는 조건이 없기 때문에 위 방법을 이용하여 집의 위치를 결정할 수 있음
        // 그러므로 한 편의시설에서 다른 모든 편의시설으로의 거리를 구해서 그 중 가장 먼 거리를 구하고 이 거리가 최소가 되는 한 편의시설을 집으로 잡음
        for(int house = 0; house < N; house++) {
            int maxDistance = Integer.MIN_VALUE;
            for(int furthest = 0; furthest < N; furthest++) {
                if(house == furthest) continue;
                int distance = getDistance(facilities.get(house), facilities.get(furthest));
                if(maxDistance < distance)
                    maxDistance = distance;
            }

            if(maxDistance < minDistance) {
                minDistance = maxDistance;
                minIdx = house;
            }
        }

        StringBuilder sb = new StringBuilder();
        Loc house = facilities.get(minIdx);
        sb.append(house.x).append(' ').append(house.y);
        System.out.println(sb);
    }

    static int getDistance(Loc facility1, Loc facility2) {
        return (int)(Math.pow(facility1.x - facility2.x, 2) + Math.pow(facility1.y - facility2.y, 2));
    }

    static class Loc {
        int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
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
