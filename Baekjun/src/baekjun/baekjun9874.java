package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun9874 {
    /*
     * N개의 웜홀(2 <= N <= 12)이 형성됨
     * 웜홀은 각각 농장의 2D 지도에서 뚜렷한 지점에 위치
     * 그의 웜홀은 N / 2 쌍을 형성
     * ex. A, B의 웜홀의 쌍으로 연결되어 있다면, A 웜홀로 들어간 어떤 물체든 같은 방향으로 B 웜홀로 나옴,
     * 반대로도 성립
     * 이는 다소 불쾌한 결과를 초래할 수 있다
     * ex. A 웜홀은 (0, 0), B 웜홀은 (1, 0)에 위치, 베시는 (1/2, 0)로부터 시작해서 +x 방향으로 이동
     * 베시는 웜홀 B로 들어가고, A로 나오고, 다시 B에 들어가고 해서 무한 사이클에 빠짐
     * 농부 John은 각 웜홀의 위치를 알고 있음, 베시는 항상 +x 방향으로 이동한다는 것을 앎
     * 그럼에도 불구하고 베시가 현재 어디 위치해있는지 기억할 수 없음
     * 불운한 위치에서 출발할 경우 무한한 주기로 갇힐 웜홀의 서로 다른 쌍의 수를 세자
     *
     * 입력
     * N
     * N개의 줄에 (x, y) 좌표 주어짐 -> 웜홀의 좌표
     *
     * 출력
     * 베시가 무한 사이클에 빠질 수 있는 웜홀 쌍의 개수 찾기
     */

    private static int answer;
    private static int wormholeCount;
    private static boolean[] visited;
    private static List<Wormhole> wormholes;

    private static void input() {
        Reader scanner = new Reader();

        wormholeCount = scanner.nextInt();
        visited = new boolean[wormholeCount];
        wormholes = new ArrayList<>();

        for(int wormhole = 0; wormhole < wormholeCount; wormhole++) {
            wormholes.add(new Wormhole(scanner.nextInt(), scanner.nextInt()));
        }
    }

    private static void solution() {
        // x 좌표 오름차순으로 정렬
        Collections.sort(wormholes);
        setWormholePairs(0, 0);
        System.out.println(answer);
    }

    // 백트래킹을 통해 가능한 모든 웜홀 짝 지어주기
    private static void setWormholePairs(int count, int wormhole) {
        // 모든 웜홀에 대해 쌍을 지어줬다면
        if(count == wormholeCount) {
            // 각 웜홀부터 출발한다고 했을 때 무한한 주기에 갇히는지 확인
            for(int wormholeIdx = 0; wormholeIdx < wormholeCount; wormholeIdx++) {
                if(isInfiniteCycle(wormholeIdx)) { // 무한한 주기에 갇힌다면
                    // 현재 만든 쌍은 무한한 주기에 갇히게 만드므로 정답 1 증가
                    answer++;
                    return;
                }
            }
        }

        // 웜홀 짝 지어주기
        // 이전에 짝 지어준 웜홀 다음 웜홀부터 다른 웜홀과 짝 지어주기
        for(int wormholeIdx = wormhole; wormholeIdx < wormholeCount; wormholeIdx++) {
            if(visited[wormholeIdx]) {
                continue;
            }
            visited[wormholeIdx] = true;

            // 다음 웜홀부터 짝 지어주기
            for(int wormholeIdx2 = wormholeIdx + 1; wormholeIdx2 < wormholeCount; wormholeIdx2++) {
                if(visited[wormholeIdx2]) {
                    continue;
                }

                visited[wormholeIdx2] = true;
                wormholes.get(wormholeIdx).connect(wormholeIdx2);
                wormholes.get(wormholeIdx2).connect(wormholeIdx);
                setWormholePairs(count + 2, wormholeIdx + 1);
                visited[wormholeIdx2] = false;
            }
            visited[wormholeIdx] = false;
        }
    }

    // 무한한 주기에 갇히는 웜홀 짝인지 확인
    private static boolean isInfiniteCycle(int wormhole) {
        // 이전에 방문한 웜홀인지 확인하기 위해 boolean 배열 사용
        boolean[] visited = new boolean[wormholeCount];

        while(true) {
            // 이전에 방문한 웜홀에 다시 방문했다면
            // 이는 무한한 주기에 갇힌다는 의미이기에 무한한 주기에 갇힌다는 의미로 true 반환
            if(visited[wormhole]) {
                return true;
            }
            // 방문처리
            visited[wormhole] = true;
            // 현재 웜홀과 연결된 웜홀의 x, y 좌표 구하기
            int x = wormholes.get(wormholes.get(wormhole).connectedWormhole).x;
            int y = wormholes.get(wormholes.get(wormhole).connectedWormhole).y;
            // 다음 이동할 웜홀이 있는지 확인
            // 이동할 수 있다면 해당 웜홀의 인덱스를, 없다면 -1을 반환
            wormhole = findNextWormhole(x, y);
            // 이동할 수 없는 웜홀이라면 무한한 주기에 갇힐 수 없기 때문에 false 반환
            if(wormhole == -1) {
                return false;
            }
        }
    }

    // 다음 웜홀 구하기
    // 특정 x, y 좌표에서 +x 방향으로 이동했을 때 들어가게 되는 웜홀 구하기
    // 있으면 해당 웜홀의 인덱스를, 없으면 -1을 반환
    private static int findNextWormhole(int x, int y) {
        for(int wormhole = 0; wormhole < wormholeCount; wormhole++) {
            if(wormholes.get(wormhole).y == y && wormholes.get(wormhole).x > x) {
                return wormhole;
            }
        }
        return -1;
    }

    static class Wormhole implements Comparable<Wormhole> {
        int x;
        int y;
        int connectedWormhole;

        public Wormhole(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void connect(int wormhole) {
            this.connectedWormhole = wormhole;
        }

        @Override
        public int compareTo(Wormhole o) {
            return this.x - o.x;
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
