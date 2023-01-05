package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun2234 {
	static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] wallNum, roomNumber;
    static Room[][] map;
    static boolean[][] visited;
    static HashSet<Integer>[] neighbor;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static HashMap<Integer, Integer> nums;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        wallNum = new int[M][N];
        map = new Room[M][N];
        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++) {
                wallNum[row][col] = scanner.nextInt();
                map[row][col] = new Room();
            }
        }
    }

    static void solution() {
        makeMap();

        int roomNum = 0;
        visited = new boolean[M][N];
        roomNumber = new int[M][N];
        nums = new HashMap<>();
        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++) {
                if(!visited[row][col]) {
                    roomNum++;
                    dfs(row, col, roomNum);
                }
            }
        }
        sb.append(roomNum).append('\n');
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(nums.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> l1, Map.Entry<Integer, Integer> l2) {
                return l2.getValue() - l1.getValue();
            }
        });
        sb.append(list.get(0).getValue()).append('\n');

        findNeighbor(roomNum);

        sb.append(getMaxNum(roomNum));
        System.out.println(sb);
    }

    static int getMaxNum(int roomNum) {
        int answer = Integer.MIN_VALUE;
        for(int room = 1; room <= roomNum; room++) {
            for(int n : neighbor[room]) {
                int sum = nums.get(room) + nums.get(n);
                answer = Math.max(answer, sum);
            }
        }
         return answer;
    }

    static void findNeighbor(int roomNum) {
        neighbor = new HashSet[roomNum + 1];
        for(int room = 1; room <= roomNum; room++) neighbor[room] = new HashSet<>();
        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++) {
                int curRoom = roomNumber[row][col];
                for(int dir = 0; dir < 4; dir++) {
                    int cx = row + dx[dir], cy = col + dy[dir];
                    if(isInMap(cx, cy)) {
                        if(roomNumber[cx][cy] != curRoom)
                            neighbor[curRoom].add(roomNumber[cx][cy]);
                    }
                }
            }
        }
    }

    static void makeMap() {
        for(int row = 0; row < M; row++) {
            for(int col = 0; col < N; col++) {
                int wall = wallNum[row][col];
                for(int num = 8; num > 0; num /= 2) {
                    if(wall < num) continue;
                    wall -= num;
                    if(num == 8) map[row][col].south = true;
                    else if(num == 4) map[row][col].east = true;
                    else if(num == 2) map[row][col].north = true;
                    else if(num == 1) map[row][col].west = true;
                }
            }
        }
    }

    static void dfs(int x, int y, int roomNum) {
        visited[x][y] = true;
        roomNumber[x][y] = roomNum;
        nums.put(roomNum, nums.getOrDefault(roomNum, 0) + 1);
        for(int dir = 0; dir < 4; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];
            if(isInMap(cx, cy)) {
                if(dir == 0) {
                    if(map[x][y].north) continue;
                } else if(dir == 1) {
                    if(map[x][y].west) continue;
                } else if(dir == 2) {
                    if(map[x][y].south) continue;
                } else {
                    if(map[x][y].east) continue;
                }
                if(!visited[cx][cy]) {
                    dfs(cx, cy, roomNum);
                }
            }
        }
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < M && y >= 0 && y < N) return true;
        return false;
    }

    static class Room {
        boolean west, north, east, south;
        public Room() {
            this.west = false;
            this.north = false;
            this.east = false;
            this.south = false;
        }
        public Room(boolean west, boolean north, boolean east, boolean south) {
            this.west = west;
            this.north = north;
            this.east = east;
            this.south = south;
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
