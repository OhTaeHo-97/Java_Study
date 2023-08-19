package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2339 {
    static int N;
    static int[][] map;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new int[N][N];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                map[row][col] = scanner.nextInt();
        }
    }

    static void solution() {
        int answer = simulation(0, 0, N, N, -1);

        if(answer == 0)
            System.out.println(-1);
        else
            System.out.println(answer);
    }

    static int simulation(int startX, int startY, int endX, int endY, int isVertical) {
        int answer = 0;
        // impurityNum = 불순물 개수, crystalNum = 결정체 개수
        int impurityNum = 0, crystalNum = 0;
        // 현재 영역에서의 불순물 개수 및 결정체 개수를 구한다
        for(int row = startX; row < endX; row++) {
            for(int col = startY; col < endY; col++) {
                if(map[row][col] == 1) impurityNum++;
                else if(map[row][col] == 2) crystalNum++;
            }
        }

        // 결정체가 하나도 없다 -> 각 조각에 보석 결정체를 단 하나씩만 포함하고 있어야 한다는 전제를 어긴다
        // 불순물이 없는데 결정체가 두 개 이상 존재한다 -> 각 조각에 보석 결정체를 단 하나씩만 포함하고 있어야 한다는 전제를 어긴다
        // 결정체가 하나인데 불순물이 존재한다 -> 각 조각에 보석 결정체를 단 하나씩만 포함하고 불순물이 없도록 해야한다는 전제를 어긴다
        // 즉, 전제를 어기는 경우에는 0을 반환한다(이후에 경우의 수를 구할 때 경우의 수를 곱할 것인데 이 경우들은 자를 수 없는 경우들이므로 경우의 수를 구할 때 0이 될 수 있도록 0을 반환)
        if(crystalNum == 0 || (impurityNum == 0 && crystalNum > 1) || (impurityNum > 0 && crystalNum == 1))
            return 0;
            // 결정체가 하나이고 불순물이 없다 -> 전제에 적합하므로 1을 반환한다
        else if(crystalNum == 1 && impurityNum == 0)
            return 1;
        else { // 그 이외의 경우에는
            // 현재 영역들을 순회하며 불순물이 존재하는 곳에서 가로 혹은 세로로 나눠본다
            for(int row = startX; row < endX; row++) {
                for(int col = startY; col < endY; col++) {
                    // 만약 현재 위치가 불순물이 있는 위치라면 가로 혹은 세로로 나눠본다
                    if(map[row][col] == 1) {
                        // isVertical이 0이 아니라면 현재 행에 따라 가로로 잘라본다
                        if(isVertical != 0) {
                            // 현재 행을 순회해보며 결정체가 존재하는지 확인하고 만약 존재한다면 hasCrystal 값을 false로 변경한다
                            boolean hasCrystal = false;
                            for(int idx = startY; idx < endY; idx++) {
                                if(map[row][idx] == 2) {
                                    hasCrystal = true;
                                    break;
                                }
                            }

                            // 만약 결정체가 존재하지 않는다면 해당 행으로 자를 수 있다
                            // 그러므로 시작 행부터 현재 행까지, 현재 행 바로 다음 행부터 마지막 행까지로 나눠 각 영역을 재귀를 통해 나눈다
                            // 바로 다음에 자를 때에는 현재 가로로 잘라서 가로로 자를 수 없기 때문에 방향은 세로로 설정하여 재귀를 진행한다
                            if(!hasCrystal) {
                                int first = simulation(startX, startY, row, endY, 0);
                                int second = simulation(row + 1, startY, endX, endY, 0);
                                // first와 second에 두 개로 나눠진 각 영역을 나눌 수 있는 경우의 수가 들어간다
                                // 그러므로 이 둘을 곱하면 현재 영역을 가로로 나눴을 때 나누는 경우의 수를 구할 수 있다
                                answer += first * second;
                            }
                        }

                        // isVertical이 1이 아니라면 현재 열에 따라 세로로 잘라본다
                        if(isVertical != 1) {
                            // 현재 열을 순회해보며 결정체가 존재하는지 확인하고 만약 존재한다면 hasCrystal 값을 false로 변경한다
                            boolean hasCrystal = false;
                            for(int idx = startX; idx < endX; idx++) {
                                if(map[idx][col] == 2) {
                                    hasCrystal = true;
                                    break;
                                }
                            }

                            // 만약 결정체가 존재하지 않는다면 해당 열로 자를 수 있다
                            // 그러므로 시작 열부터 현재 열까지, 현재 열 바로 다음 열부터 마지막 열까지로 나눠 각 영역을 재귀를 통해 나눈다
                            // 바로 다음에 자를 때에는 현재 세로로 잘라서 세로로 자를 수 없기 때문에 방향은 가로로 설정하여 재귀를 진행한다
                            if(!hasCrystal) {
                                int first = simulation(startX, startY, endX, col, 1);
                                int second = simulation(startX, col + 1, endX, endY, 1);
                                // first와 second에 두 개로 나눠진 각 영역을 나눌 수 있는 경우의 수가 들어간다
                                // 그러므로 이 둘을 곱하면 현재 영역을 세로로 나눴을 때 나누는 경우의 수를 구할 수 있다
                                answer += first * second;
                            }
                        }
                    }
                }
            }
        }

        // 현재 영역을 자를 수 있는 모든 경우의 수를 반환한다
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
