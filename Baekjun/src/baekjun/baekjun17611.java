package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun17611 {
    // 수평선, 수직선이 이동할 좌표가 2배로 늘어났다
    // 그 이유는 차이가 1인 두 정수 사이에 실수 부분도 수평선, 수직선은 지날 수 있으니
    // 좌표를 2배로 늘려서 그러한 실수 부분을 표현한다
    static final int SIZE = 2_000_001; // 수평선, 수직선이 이동할 좌표의 개수
    static final int GAP = 1_000_000; // 인덱스는 음수를 표현할 수 없으므로 인덱스 표현 시에 더해줄 값
    static int n;
    static int[] vertical, horizontal; // 각 지점에서 수직선, 수평선이 교차하는 횟수

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        vertical = new int[SIZE];
        horizontal = new int[SIZE];

        // start : 시작점의 좌표
        // prev : 바로 이전 점의 좌표
        int[] start = new int[2], prev = new int[2];

        for(int idx = 0; idx < n; idx++) {
            // 좌표를 2배로 늘렸으니 입력받은 좌표 역시 2배로 늘린다
            int x = scanner.nextInt() * 2, y = scanner.nextInt() * 2;

            if(idx == 0) {// 만약 처음 입력이라면
                start = new int[] {x, y}; // 시작점을 설정한다
            } else { // 그렇지 않다면
                // 바로 이전 점과 비교하여 지날 수 있는 지점의 시작점과 지날 수 없는 곳의 시작점을 표시한다
                //  -> 표시하는 방법은
                //      -> 지날 수 있는 지점의 시작점에는 1을 더해주고
                //      -> 지날 수 없는 곳의 시작점에는 1을 빼준다
                //  -> 순서대로 좌표가 주어지기 때문에 수평선의 시작점과 끝점에는 수직선이, 수직선의 시작점과 끝점에는 수평선이 위치한다
                //  -> 그러므로 수평선/수직선의 시작점 + 1인 좌표에 1을 더해주고 수평선/수직선의 끝점에 1을 뺴준다
                // 만약 x좌표가 같다면 horizontal에, y좌표가 같다면 vertical에 표시한다
                if(prev[0] == x) {
                    int min = Math.min(prev[1], y), max = Math.max(prev[1], y);
                    horizontal[min + 1 + GAP]++;
                    horizontal[max + GAP]--;
                } else if(prev[1] == y) {
                    int min = Math.min(prev[0], x), max = Math.max(prev[0], x);
                    vertical[min + 1 + GAP]++;
                    vertical[max + GAP]--;
                }
            }

            // 마지막 입력이라면 마지막 점과 시작점을 이어줘야하므로 위 작업을 시작점과 마지막 점 사이에도 진행한다
            if(idx == n - 1)  {
                if(start[0] == x) {
                    int min = Math.min(start[1], y), max = Math.max(start[1], y);
                    horizontal[min + 1 + GAP]++;
                    horizontal[max + GAP]--;
                } else if(start[1] == y) {
                    int min = Math.min(start[0], x), max = Math.max(start[0], x);
                    vertical[min + 1 + GAP]++;
                    vertical[max + GAP]--;
                }
            }

            // 바로 이전 좌표를 현재 좌표로 갱신한다
            prev = new int[] {x, y};
        }
    }

    static void solution() {
        // vertical, horizontal에는
        // 수직선, 수평선에서 지날 수 있는 곳의 시작점과 지날 수 없는 곳의 시작점이 표시되어 있다
        // 그러므로 해당 배열의 누적합을 구하면 각 지점에서 수평선/수직선이 교차하는 횟수를 구할 수 있다
        // 이를 구하기 위해 두 배열의 누적합을 구한다
        for(int idx = 1; idx < vertical.length; idx++) {
            vertical[idx] += vertical[idx - 1];
            horizontal[idx] += horizontal[idx - 1];
        }

        // 수직선과 수평선 각각에서 교차하는 횟수의 최댓값을 구한다
        int verticalMax = Arrays.stream(vertical).max().getAsInt();
        int horizontalMax = Arrays.stream(horizontal).max().getAsInt();

        // 그 중 큰 값을 출력한다
        System.out.println(Math.max(verticalMax, horizontalMax));
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
