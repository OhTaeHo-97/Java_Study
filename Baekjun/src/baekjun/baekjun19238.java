package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun19238 {
	static int N, M, initial;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static Taxi taxi;
    static HashMap<Patron, int[]> patrons;
    static boolean isFinish = false; // 연료가 모자라서 더이상 움직일 수 없는지 나타냄

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt(); // 맵의 크기
        map = new int[N][N];

        M = scanner.nextInt(); // 손님 인원수
        patrons = new HashMap<>(); // 손님을 나타내는 HashMap

        initial = scanner.nextInt(); // 초기 연료양

        // 맵 완성
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                map[row][col] = scanner.nextInt();
        }

        // 처음 택시 위치 및 연료 지정
        taxi = new Taxi(scanner.nextInt() - 1, scanner.nextInt() - 1, initial);

        // 손님들 위치 및 도착 위치 입력
        for(int patron = 0; patron < M; patron++) {
            int startX = scanner.nextInt() - 1, startY = scanner.nextInt() - 1;
            int endX = scanner.nextInt() - 1, endY = scanner.nextInt() - 1;

            patrons.put(new Patron(startX, startY), new int[] {endX, endY});
        }
    }

    static void solution() {
        // 손님 인원수만큼 돌면서 모든 승객을 태우고 내릴 수 있는지 확인
        for(int count = 0; count < M; count++) {
            // 가장 가까운 승객 찾기
            Patron shortestPatron = findNearestPatron();
            // 가까운 승객에게 갈 때, 연료가 부족하다면 끝냄
            if(isFinish)
                break;

            // 가장 가까운 승객을 도착지에 내려주기
            Taxi destTaxi = moveToDestination(taxi, patrons.get(shortestPatron));
            // 도착지로 이동할 때, 연료가 부족하다면 끝냄
            if(destTaxi == null)
                break;

            // 가까운 승객의 출발지부터 도착지까지 사용한 연료 계산
            int usedFuel = taxi.fuel - destTaxi.fuel;
            // 도착지에 도착하여 충전한 연료를 계산
            int chargedFuel = destTaxi.fuel + (usedFuel * 2);

            // 택시의 현재 위치 및 연료 갱신
            taxi = new Taxi(destTaxi.x, destTaxi.y, chargedFuel, 0);
            // 승객을 내려줬기 때문에 해당 승객은 HashMap에서 제거
            patrons.remove(shortestPatron);
        }

        int answer = 0;
        // 연료가 없는 상황에서 아직 내려주지 않은 승객이 있으므로 -1 출력
        if(patrons.size() != 0)
            answer = -1;
        else
            answer = taxi.fuel;

        System.out.println(answer);
    }

    // 출발지부터 목적지까지 택시를 이동시키는 작업
    static Taxi moveToDestination(Taxi start, int[] destination) {
        Taxi taxi = null;

        // BFS를 통해 출발지에서 목적지로 이동하는 최단 거리 계산
        Queue<Taxi> queue = new LinkedList<>();
        queue.offer(start);

        int[][] visited = new int[N][N];
        for(int row = 0; row < N; row++)
            Arrays.fill(visited[row], Integer.MAX_VALUE);
        visited[start.x][start.y] = 0;

        while(!queue.isEmpty()) {
            Taxi cur = queue.poll();

            // 도착지에 도착했다면 그 때의 위치 및 연료를 반환
            if(cur.x == destination[0] && cur.y == destination[1]) {
                taxi = cur;
                break;
            }

            // 도착지에 도착하지 않은 상태에서 연료가 없다면 다른 경우의 수를 계산
            if(cur.fuel <= 0)
                continue;

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy)) {
                    if(map[cx][cy] == 0 && visited[cx][cy] > cur.moveNum + 1) {
                        visited[cx][cy] = cur.moveNum + 1;
                        queue.offer(new Taxi(cx, cy, cur.fuel - 1, cur.moveNum + 1));
                    }
                }
            }
        }

        return taxi;
    }

    // 가장 가까운 승객 찾기 및 택시의 위치 및 연료 갱신
    static Patron findNearestPatron() {
        // 거리상 가장 가까운 승객의 목록
        ArrayList<Patron> candidate = moveToNearestPatron(taxi, patrons);

        // 만약 승객 목록에 한 명도 없다면 모든 승객에 대해 태우러 갈 때 연료가 부족해서 태울 수 없는 상황
        // 이럴 때는 isFinish를 true로 만들어 더이상 진행할 수 없음을 나타냄
        if(candidate.size() == 0) {
            isFinish = true;
            return null;
        }

        // 거리상 가장 가까운 승객의 목록을 행에 대한 오름차순, 열에 대한 오름차순으로 정렬
        Collections.sort(candidate, new Comparator<Patron>() {
            @Override
            public int compare(Patron p1, Patron p2) {
                if(p1.x != p2.x) return p1.x - p2.x;
                return p1.y - p2.y;
            }
        });

        // 가장 가까운 승객의 위치 및 그 때의 연료로 택시 갱신
        Patron shortestPatron = candidate.get(0);
        taxi = new Taxi(shortestPatron.x, shortestPatron.y, shortestPatron.fuel, 0);

        return shortestPatron;
    }

    // 거리상 가장 가까운 승객의 목록 찾기
    static ArrayList<Patron> moveToNearestPatron(Taxi start, HashMap<Patron, int[]> patrons) {
        int shortest = Integer.MAX_VALUE; // 가장 가까운 승객에게의 거리

        // BFS를 통해 거리상 가장 가까운 승객들을 찾음
        Queue<Taxi> queue = new LinkedList<>();
        queue.offer(start);

        int[][] visited = new int[N][N];
        for(int row = 0; row < N; row++)
            Arrays.fill(visited[row], Integer.MAX_VALUE);
        visited[start.x][start.y] = 0;

        ArrayList<Patron> candidate = new ArrayList<>(); // 거리상 가장 가까운 승객들의 목록

        while(!queue.isEmpty()) {
            Taxi cur = queue.poll();

            // 만약 현재까지의 가장 가까운 손님에게의 최단거리보다 더 큰 거리가 들어왔다면
            // 이후를 진행해도 가장 가까운 손님에게 도달하는 것은 불가능하니 끝냄
            if(shortest < cur.moveNum)
                break;

            // 만약 현재 위치에 손님이 있다면
            // 가장 가까운 승객에게의 최단 거리를 갱신하고 승객 목록에 해당 승개의 위치 및 그 때의 연료량 추가
            if(patrons.containsKey(new Patron(cur.x, cur.y))) {
                shortest = cur.moveNum;
                candidate.add(new Patron(cur.x, cur.y, cur.fuel));
                continue;
            }

            // 만약 승객에게까지 도달하지 못했는데 연료가 없다면 해당 경우는 승객에게 도달할 수 없으므로 다음 경우 확인
            if(cur.fuel <= 0)
                continue;

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur.x + dx[dir], cy = cur.y + dy[dir];

                if(isInMap(cx, cy)) {
                    if(map[cx][cy] == 0 && visited[cx][cy] > cur.moveNum + 1) {
                        visited[cx][cy] = cur.moveNum + 1;
                        queue.offer(new Taxi(cx, cy, cur.fuel - 1, cur.moveNum + 1));
                    }
                }
            }
        }

        return candidate;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >=0 && y < N) return true;
        return false;
    }

    static class Taxi {
        int x, y, fuel, moveNum;

        public Taxi(int x, int y, int fuel) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
            moveNum = 0;
        }

        public Taxi(int x, int y, int fuel, int moveNum) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
            this.moveNum = moveNum;
        }

        @Override
        public String toString() {
            return "Taxi{" +
                    "x=" + x +
                    ", y=" + y +
                    ", fuel=" + fuel +
                    '}';
        }
    }

    static class Patron {
        int x, y, fuel;

        public Patron(int x, int y) {
            this.x = x;
            this.y = y;
            fuel = -1;
        }

        public Patron(int x, int y, int fuel) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Patron patron = (Patron) o;
            return x == patron.x && y == patron.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Patron{" +
                    "x=" + x +
                    ", y=" + y +
                    ", fuel=" + fuel +
                    '}';
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
