package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun12869 {
	static final int[] ATTACKS = new int[] {9, 3, 1}; // 뮤탈리스크의 공격
    static int N;
    static int[] hps;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        hps = new int[N];

        for(int idx = 0; idx < N; idx++) hps[idx] = scanner.nextInt();
    }

    static void solution() {
        // SCVs에 equals(), hashCode()를 구현했는데, equals() 판별 시 순서가 중요하기 때문에 오름차순으로 정렬한다
        Arrays.sort(hps);
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<SCVs> queue = new LinkedList<>();
        HashMap<SCVs, Integer> map = new HashMap<>(); // 방문체크용 Map
        // key : 현재 체력 상태, value : 현재 체력 상태까지 오는데 걸리는 최소 시간

        queue.offer(new SCVs(hps, 0));
        map.put(new SCVs(hps, 0), 0);

        while(!queue.isEmpty()) {
            SCVs cur = queue.poll();
            if(cur.getTotal() == 0) return cur.time; // 모든 SCV의 체력이 없어지면 그 때의 시간을 반환

            // 어떤 SCV부터 공격할지 순열을 통해 구함
            ArrayList<int[]> orders = new ArrayList<>();
            getOrders(0, cur.healths.length, new int[cur.healths.length], new boolean[cur.healths.length], orders);

            // 위에서 구한 각 순서들에 따라 공격을 진행
            for(int[] order : orders) {
                int[] scvHealths = cur.healths.clone(); // 현재 SCV들의 체력 상태 복사

                // 주어진 순서대로 공격, 만약 체력이 다 닳았다면 0을 저장
                for(int idx = 0; idx < order.length; idx++)
                    scvHealths[order[idx]] = Math.max(scvHealths[order[idx]] - ATTACKS[idx], 0);

                // 공격한 후에 남은 체력들을 오름차순으로 정렬
                // equals()에서 순서가 중요하기 때문!
                Arrays.sort(scvHealths);

                // 공격한 체력을 이용하여 새롭게 SCVs 객체를 생성
                SCVs newSCVs = new SCVs(scvHealths, cur.time + 1);

                // 아직 방문한 적이 없다면 방문체크를 진행하고 Queue에 탐색하기 위해 추가
                if(!map.containsKey(newSCVs)) {
                    map.put(newSCVs, newSCVs.time);
                    queue.offer(newSCVs);
                }
            }
        }

        return -1;
    }

    // 순열을 통해 순서를 구하는 과정
    static void getOrders(int index, int size, int[] order, boolean[] visited, ArrayList<int[]> orders) {
        if(size == index) {
            orders.add(order.clone());
            return;
        }

        for(int idx = 0; idx < size; idx++) {
            if(!visited[idx]) {
                visited[idx] = true;
                order[index] = idx;

                getOrders(index + 1, size, order, visited, orders);

                visited[idx] = false;
            }
        }
    }

    static class SCVs {
        int[] healths; // scv들의 남은 체력
        int time; // 해당 체력까지 오는데 걸린 시간

        public SCVs(int[] healths, int time) {
            this.healths = healths;
            this.time = time;
        }

        public int getTotal() { // 남은 체력의 합을 구하는 메서드
            return Arrays.stream(healths).sum();
        }

        @Override
        public boolean equals(Object o) { // 같은 객체인지 확인하기 위해 오버라이딩한 메서드
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SCVs scVs = (SCVs) o;
            return Arrays.equals(healths, scVs.healths);
        }

        // HashMap에서 hashcode를 통해 객체를 찾고 중복 검사를 하는데 hashCode를 어떻게 구할지 나타내기 위해 오버라이딩한 메서드
        @Override
        public int hashCode() {
            int result = Arrays.hashCode(healths);
            return result;
        }
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    // Scanner 클래스
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
