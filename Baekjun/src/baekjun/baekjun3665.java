package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun3665 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();

    static int n, m;
    static int[] prevOrder, precedeNum, InDegree;
    static Map<Integer, ArrayList<Integer>> map;

    static void input() {
        n = scanner.nextInt();
        prevOrder = new int[n];

        for(int idx = 0; idx < n; idx++)
            prevOrder[idx] = scanner.nextInt();

        precedeNum = new int[n + 1];
        map = new HashMap<>();
        for(int team = 1; team <= n; team++)
            map.put(team, new ArrayList<>());

        for(int basis = 0; basis < n; basis++) {
            int basisTeam = prevOrder[basis];
            for(int idx = basis + 1; idx < n; idx++) {
                map.get(basisTeam).add(prevOrder[idx]);
                precedeNum[prevOrder[idx]]++;
            }
        }

        m = scanner.nextInt();

        for(int idx = 0; idx < m; idx++) {
            int team1 = scanner.nextInt(), team2 = scanner.nextInt();

            if(map.get(team1).contains(team2)) {
                map.get(team1).remove(Integer.valueOf(team2));
                map.get(team2).add(team1);

                precedeNum[team1]++;
                precedeNum[team2]--;
            } else {
                map.get(team2).remove(Integer.valueOf(team1));
                map.get(team1).add(team2);

                precedeNum[team2]++;
                precedeNum[team1]--;
            }
        }
    }

    static void solution() {
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        for(int team = 1; team <= n; team++) {
            if(precedeNum[team] == 0) {
                queue.offer(team);
                count++;
            }
        }

        // 선행되는 순위가 없는 사람이 둘 이상 -> 1위가 두 명 이상이 된다는 뜻
        // 이러한 경우는 이들끼리는 순위를 매기는 것이 불가능하다
        // 그러므로 정확한 순서를 가릴 수 없다!
        if(count > 1) {
            sb.append('?').append('\n');
            return;
        }

        StringBuilder newOrder = new StringBuilder(); // 새로운 순위를 나타내는 StringBuilder
        boolean isImpossible = false; // 순위 매기는 것이 불가능한지를 나타내는 변수
        // 순위 매기기
        // 모든 팀의 순위를 매길 수 있다면 총 n번 반복문을 돌면 모든 팀의 순위를 매길 수 있다
        for(int idx = 1; idx <= n; idx++) {
            // 만약 반복문을 도는 중간에 Queue가 비어있다면 이후에 순위를 매길 팀이 없다는 것을 의미한다
            // 이는 데이터에 문제가 있어 순위를 매길 수 없다는 것을 의미한다
            // 그러므로 순위를 매기는 것이 불가능하다는 뜻으로 IMPOSSIBLE을 출력한다
            if(queue.isEmpty()) {
                sb.append("IMPOSSIBLE").append('\n');
                isImpossible = true;
                break;
            }

            // 만약 반복문을 도는 중간에 Queue에 두 개 이상의 원소가 들어있다면 같은 순위에 여러 팀이 들어갈 수 있음을 의미한다
            // 같은 순위에 있는 팀들끼리는 순위를 매기는 것이 불가능하다
            // 그러므로 정확한 순서를 가질 수 없다!
            if(queue.size() > 1) {
                sb.append("?").append('\n');
                isImpossible = true;
                break;
            }

            // 순위를 매기는 것이 가능한 경우
            // 현재 Queue에 있는 팀이 idx번째 순위에 해당하다는 뜻이므로 StringBuilder에 해당 팀을 추가한다
            int curTeam = queue.poll();
            newOrder.append(curTeam).append(' ');

            // 현재 팀과 이어져있고 이후 순위에 있어야 하는 팀들의 목록을 순회하며
            // 선행 팀의 수를 1 감소시켜주고 만약 더이상 선행 팀이 없다면 Queue에 해당 팀을 추가
            for(int nextTeam : map.get(curTeam)) {
                precedeNum[nextTeam]--;
                if(precedeNum[nextTeam] == 0) queue.offer(nextTeam);
            }
        }

        if(isImpossible) return;

        sb.append(newOrder.toString()).append('\n');
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
