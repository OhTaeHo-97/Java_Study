package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14238 {
	static String S;
    static int aCnt, bCnt, cCnt; // 주어진 출근 기록에서 A, B, C의 개수
    static boolean[][][][][] visited;
    // visited[a][b][c][p2][p] = A가 a개, B가 b개, C가 c개 남았고 2번째 전 알파벳이 p2, 바로 이전 알파벳이 p일 때 해당 위치를 방문했는지의 여부
    // p2, p -> 0, 1, 2의 값을 가지고, 0이 A, 1이 B, 2가 C를 나타낸다

    static void input() {
        Reader scanner = new Reader();

        S = scanner.nextLine();
    }

    static void solution() {
        countAlphabet();

        visited = new boolean[aCnt + 1][bCnt + 1][cCnt + 1][3][3];
        dfs(aCnt, bCnt, cCnt, "", 0, 0);

        // 가능한 출근 기록이 없다면 -1을 출력한다
        System.out.println(-1);
    }

    // a -> A의 남은 개수, b -> B의 남은 개수, c -> C의 남은 개수
    // prevPrev -> 2번째 이전의 값, prev -> 이전의 값
    static void dfs(int a, int b, int c, String attendanceRecord, int prevPrev, int prev) {
        // 만약 A, B, C의 개수가 모두 0이라면 모든 알파벳을 사용하여 가능한 출근 기록을 만들었다는 것을 의미한다
        if(a == 0 && b == 0 && c == 0) {
            // 그러므로 해당 출근 기록을 출력하고 프로그램을 끝낸다
            System.out.println(attendanceRecord);
            System.exit(0);
        }

        // 이미 이전에 방문한 적 있는 출근 기록이라면 이후는 탐색해보지 않고 다음 경우로 넘어간다
        if(visited[a][b][c][prevPrev][prev]) return;

        // 현재 출근 기록에 대한 방문 체크를 진행한다
        visited[a][b][c][prevPrev][prev] = true;

        // 만약 A가 아직 남아있다면
        // A를 출근 기록에 붙여주고 2번째 이전 값을 현재의 이전 값으로, 이전 값은 A로 변경하여 이후 탐색을 진행한다
        if(a > 0)
            dfs(a - 1, b, c, attendanceRecord + 'A', prev, 0);
        // 만약 B가 아직 남아있고 이전 값이 B가 아니라면(B는 출근한 다음날은 반드시 쉬어야 하므로)
        // B를 출근 기록에 붙여주고 2번째 이전 값을 현재의 이전 값으로, 이전 값은 B로 변경하여 이후 탐색을 진행한다
        if(b > 0 && prev != 1)
            dfs(a, b - 1, c, attendanceRecord + 'B', prev, 1);
        // 만약 C가 아직 남아있고 2번째 이전 값과 이전 값이 C가 아니라면(C는 출근한 다음날과 다다음날은 반드시 쉬어야 하므로)
        // C를 출근 기록에 붙여주고 2번째 이전 값을 현재의 이전 값으로, 이전 값은 C로 변경하여 이후 탐색을 진행한다
        if(c > 0 && prevPrev != 2 && prev != 2)
            dfs(a, b, c - 1, attendanceRecord + 'C', prev, 2);
    }

    static void countAlphabet() {
        for(int idx = 0; idx < S.length(); idx++) {
            char curAlphabet = S.charAt(idx);

            if(curAlphabet == 'A')
                aCnt++;
            else if(curAlphabet == 'B')
                bCnt++;
            else if(curAlphabet == 'C')
                cCnt++;
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
