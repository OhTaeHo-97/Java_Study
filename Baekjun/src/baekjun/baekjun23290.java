package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun23290 {
    static final int SIZE = 4;
    static int M, S, maxFishNum, answer;
    static List<Fish>[][] fishArr;
    static List<Fish> fishes;
    static int[][] fishSmell;
    static int[] sharkLoc, tempSharkMove, finalSharkMove;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1}, dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sharkDx = {-1, 0, 1, 0}, sharkDy = {0, -1, 0, 1};

    static void input() {
        Reader scanner = new Reader();

        M = scanner.nextInt();
        S = scanner.nextInt();
        fishSmell = new int[SIZE][SIZE]; // 각 위치에서 물고기 냄새가 남아있는 최대 횟수
        fishArr = new ArrayList[SIZE][SIZE]; // 각 위치에 있는 물고기를 나타내는 배열
        tempSharkMove = new int[3]; // 상어가 백트래킹을 통해 이동할 때, 이동 경로를 나타내는 배열
        finalSharkMove = new int[3]; // 최종적으로 상어가 가장 많은 물고기를 제외시키며 이동하는 이동 경로를 나타내는 배열
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++)
                fishArr[row][col] = new ArrayList<>();
        }
        fishes = new ArrayList<>();

        for(int idx = 0; idx < M; idx++)
            fishes.add(new Fish(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt()));

        sharkLoc = new int[] {scanner.nextInt() - 1, scanner.nextInt() - 1};
    }

    static void solution() throws CloneNotSupportedException {
        for(int count = 0; count < S; count++) {
            // 현재 남아있는 물고기들을 복사해놓는다(이후에 복제 마법이 완료된 후에 이용)
            List<Fish> curFishes = copyFish(fishes);

            // 각 물고기들을 순회하며 물고기를 이동시키고 위치, 방향을 갱신한다
            for(int idx = 0; idx < fishes.size(); idx++) {
                Fish curFish = fishes.get(idx);
                curFish = moveFish(curFish);
            }

            // 이동시킨 물고기들을 각 위치에 배치시킨다
            setFishInArr();

            // 상어의 이동경로 중 가장 많은 물고기를 제외시키는 경로를 찾고 그때 제외시킨 물고기 수를 구한다
            maxFishNum = Integer.MIN_VALUE;
            dfs(0);
            // 그리고 상어를 실제로 이동시킨다
            moveShark();

            // 남아있는 물고기 냄새의 남은 횟수를 1씩 줄인다
            removeFishSmell();

            // 복제해놓은 원래 있었던 물고기들을 각 위치에 배치시킨다
            addExistsFishesInArr(curFishes);

            // 각 위치에 알맞게 물고기들을 재배치시킨다
            // 이때 물고기 수를 세어 답을 구한다
            updateFishes();
        }

        System.out.println(answer);
    }

    static void updateFishes() {
        fishes.clear(); // 리스트를 비워준다(새롭게 변경된, 추가된 물고기들과 함께 갱신시키기 위해)
        int fishNum = 0; // 물고기 수

        // 각 위치를 돌며 각 위치에 있는 물고기들을 리스트에 넣고 물고기 수를 센다
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                for(Fish fish : fishArr[row][col]) {
                    fishes.add(fish);
                    fishNum++;
                }
                fishArr[row][col].clear();
            }
        }

        // 답을 갱신한다
        answer = fishNum;
    }

    static void addExistsFishesInArr(List<Fish> curFishes) {
        // 각 위치에 기존에 있던 물고기들을 배치시킨다
        for(Fish fish : curFishes)
            fishArr[fish.x][fish.y].add(fish);
    }

    static void removeFishSmell() {
        // 남아있는 물고기 냄새의 남은 횟수를 1씩 감소시킨다
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                if(fishSmell[row][col] > 0) fishSmell[row][col]--;
            }
        }
    }

    static void moveShark() {
        // 최종적으로 상어가 이동할 경로를 구했으니 이를 이용해 상어를 이동시킨다
        // 이동 위치마다 만약 물고기가 있다면 해당 위치에 있는 물고기를 제외시키고
        // 해당 위치에 물고기 냄새의 남은 횟수를 3으로 설정한다(두 번 전 연습에서 생긴 냄새가 사라져야하므로)
        for(int idx = 0; idx < 3; idx++) {
            sharkLoc[0] += sharkDx[finalSharkMove[idx]];
            sharkLoc[1] += sharkDy[finalSharkMove[idx]];

            if(fishArr[sharkLoc[0]][sharkLoc[1]].size() > 0) {
                fishSmell[sharkLoc[0]][sharkLoc[1]] = 3;
                fishArr[sharkLoc[0]][sharkLoc[1]].clear();
            }
        }
    }

    static void dfs(int index) {
        if(index == 3) { // 이동할 3칸의 경로를 정했다면
            // 실제로 이동할 수 있는지 확인하고 그때 제외시키는 물고기 수를 구한다
            int fishNum = findFishNum();
            if(fishNum == -1) return; // 만약 물고기 수가 -1이라면 이동할 수 없다는 뜻이므로 다음 경우 탐색
            // 만약 최대 물고기 수보다 현재 경로로 이동했을 때 제외시키는 물고기 수가 더 크다면
            if(maxFishNum < fishNum) {
                // 최대 물고기 수를 갱신시키고 최종 경로를 현재 경로로 설정한다
                maxFishNum = fishNum;
                for(int idx = 0; idx < 3; idx++)
                    finalSharkMove[idx] = tempSharkMove[idx];
            }
            return;
        }

        // 3칸의 경로를 설정한다
        // 사전 순서로 구해야하므로 상, 좌, 하, 우 순으로 경로를 설정한다
        for(int dir = 0; dir < sharkDx.length; dir++) {
            tempSharkMove[index] = dir;
            dfs(index + 1);
        }
    }

    // dfs() 메서드에서 구한 각 경로대로 이동해보면서 물고기 수를 구한다
    static int findFishNum() {
        boolean[][] visited = new boolean[SIZE][SIZE]; // 각 위치를 방문했는지를 나타내는 배열
        int fishNum = 0; // 제거되는 물고기 수
        int cx = sharkLoc[0], cy = sharkLoc[1]; // 이동할 때의 상어의 위치

        for(int idx = 0; idx < 3; idx++) {
            // 설정한 경로의 방향대로 한 칸 이동해본다
            cx += sharkDx[tempSharkMove[idx]];
            cy += sharkDy[tempSharkMove[idx]];

            // 만약 이동할 수 없는 칸이라면 이동할 수 없음을 나타내기 위해 -1을 반환한다
            if(!isInMap(cx, cy)) {
                fishNum = -1;
                break;
            }
            if(visited[cx][cy]) continue; // 이미 방문한 곳이라면 다시 확인할 필요가 없으니 다음 이동으로 넘어간다

            // 현재 위치에 있는 물고기 수를 누적해가며 제거되는 물고기 수를 구한다
            fishNum += fishArr[cx][cy].size();
            // 방문 체크
            visited[cx][cy] = true;
        }

        return fishNum;
    }

    static void setFishInArr() {
        // 각 위치에 이동한 물고기들을 배치한다
        for(Fish fish : fishes)
            fishArr[fish.x][fish.y].add(fish);
    }

    static Fish moveFish(Fish fish) {
        int cx = 0, cy = 0, rotateNum = 0;
        while(true) {
            // 현재 물고기가 바라보고 있는 방향으로 한 칸 이동해본다
            cx = fish.x + dx[fish.dir];
            cy = fish.y + dy[fish.dir];
            // 만약 이동할 수 있는 칸이라면 해당 위치로 이동시키기 위해 반복문을 빠져나간다
            if(isInMap(cx, cy)) {
                if(fishSmell[cx][cy] == 0 && !(cx == sharkLoc[0] && cy == sharkLoc[1]))
                    break;
            }

            // 이동할 수 없다면 방향을 변환한다
            fish.dir--;
            if(fish.dir <= 0) fish.dir = 8;
            // 방향을 바꾼 횟수를 1 증가시킨다
            // 만약 방향을 바꾼 횟수가 8보다 크거나 같다면 이동할 수 없다는 뜻이므로 반복문을 빠져나간다
            rotateNum++;
            if(rotateNum >= 8) break;
        }

        // 이동할 수 있는 물고기라면 이동시킨다
        if(rotateNum < 8) {
            fish.x = cx;
            fish.y = cy;
        }

        return fish;
    }

    static boolean isInMap(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    static List<Fish> copyFish(List<Fish> fishes) throws CloneNotSupportedException {
        // 기존에 존재하고 있는 물고기들을 새로운 리스트에 복사한다
        List<Fish> copyList = new ArrayList<>();
        for(int idx = 0; idx < fishes.size(); idx++) {
            Fish curFish = fishes.get(idx);
            copyList.add(curFish.clone());
        }

        return copyList;
    }

    static class Fish implements Cloneable {
        int x, y, dir;
        // 왼, 왼위, 위, 오위, 오, 오아, 아, 왼아

        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        protected Fish clone() throws CloneNotSupportedException {
            return (Fish) super.clone();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
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
