package programmers.PCCP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Level2_OilDrilling {
    // 세로 길이 n, 가로 길이 m
    // 석유 => 여러 덩어리로 나누어 묻혀있음
    // 시추관 => 수직으로 단 하나만 뚫을 수 있음
    // 가장 많은 석유를 뽑을 수 있는 시추관 위치를 찾으려고 함
    //  - 열과 열 사이에 시추관을 뚫을 수 없음

    private int[] dx = {0, -1, 0, 1};
    private int[] dy = {-1, 0, 1, 0};
    private int[][] lands;
    private boolean[][] visited;
    private List<List<int[]>> loc;

    public int solution(int[][] land) {
        lands = land;
        visited = new boolean[land.length][land[0].length];
        findOilSize();
        int[] oilSize = findOilSizeByColumn();
        return findMaxOil(oilSize);
    }

    private int findMaxOil(int[] oilSize) {
        return Arrays.stream(oilSize).max().getAsInt();
    }

    private int[] findOilSizeByColumn() {
        int[] oilSize = new int[lands[0].length];
        for(int idx = 0; idx < loc.size(); idx++) {
            List<int[]> list = loc.get(idx);
            boolean[] visited = new boolean[lands[0].length];
            for(int[] l : list) {
                int col = l[1];
                if(!visited[col]) {
                    visited[col] = true;
                    oilSize[col] += list.size();
                }
            }
        }
        return oilSize;
    }

    private void findOilSize() {
        loc = new ArrayList<>();
        for(int row = 0; row < lands.length; row++) {
            for(int col = 0; col < lands[row].length; col++) {
                if(!visited[row][col] && lands[row][col] == 1) {
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[] {row, col});
                    visited[row][col] = true;
//                    dfs(row, col, 1, list);
                    bfs(row, col, list);
                    loc.add(list);
                }
            }
        }
    }

//    private void dfs(int x, int y, int size, List<int[]> list) {
//        for(int dir = 0; dir < 4; dir++) {
//            int cx = x + dx[dir];
//            int cy = y + dy[dir];
//            if(isInMap(cx, cy) && !visited[cx][cy] && lands[cx][cy] == 1) {
//                visited[cx][cy] = true;
//                list.add(new int[] {cx, cy});
//                dfs(cx, cy, size + 1, list);
//            }
//        }
//    }

    private void bfs(int x, int y, List<int[]> list) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int dir = 0; dir < 4; dir++) {
                int cx = cur[0] + dx[dir];
                int cy = cur[1] + dy[dir];
                if(isInMap(cx, cy)) {
                    if(!visited[cx][cy] && lands[cx][cy] == 1) {
                        visited[cx][cy] = true;
                        list.add(new int[] {cx, cy});
                        queue.offer(new int[] {cx, cy});
                    }
                }
            }
        }
    }

    private boolean isInMap(int x, int y) {
        return x >= 0 && x < lands.length && y >= 0 && y < lands[0].length;
    }

    public static void main(String[] args) {
        Level2_OilDrilling l = new Level2_OilDrilling();
        System.out.println(l.solution(new int[][] {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}}));
        System.out.println(l.solution(new int[][] {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}}));
    }
}
