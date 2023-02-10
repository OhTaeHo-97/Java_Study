package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun17837 {
	static int N, K;
    static Section[][] map;
    static Stone[] stones;
    static int[][] direction = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int[] reverseDir = {0, 2, 1, 4, 3};

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        map = new Section[N][N];

        K = scanner.nextInt();
        stones = new Stone[K + 1];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++)
                map[row][col] = new Section(scanner.nextInt());
        }

        for(int stone = 1; stone <= K; stone++) {
            int row = scanner.nextInt(), col = scanner.nextInt(), dir = scanner.nextInt();

            stones[stone] = new Stone(row - 1, col - 1, stone, dir);

            map[row - 1][col - 1].stones.add(stone);
        }
    }

    static void solution() {
//        for(Stone stone : stones) {
//            if(stone != null) {
//                System.out.println("stone.stoneNum = " + stone.stoneNum);
//                System.out.println("stone.x = " + stone.x);
//                System.out.println("stone.y = " + stone.y);
//                System.out.println("stone.dir = " + stone.dir);
//            }
//        }

        boolean isFinish = false;
        int turn = 1;
        for(turn = 1; turn <= 1000; turn++) {
            boolean flag = oneTurn();
            if(flag) {
                isFinish = true;
                break;
            }

            if(turn == 1000)
                isFinish = true;
        }

        System.out.println(isFinish ? -1 : turn);
    }

    static void print() {
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                System.out.println("(" + row + ", " + col +") : " + map[row][col].stones);
            }
        }
        System.out.println();
    }

    static boolean oneTurn() {
        for(int stoneNum = 1; stoneNum <= K; stoneNum++) {
            moveStone(stones[stoneNum]);
            print();
        }

        return isFinishGame();
    }

    static boolean isFinishGame() {
        boolean isFinish = false;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(map[row][col].stones.size() >= 4) {
                    isFinish = true;
                    break;
                }
            }
        }

        return isFinish;
    }

    static void moveStone(Stone stone) {
        int dir = stone.dir;
        int dx = stone.x + direction[dir][0], dy = stone.y + direction[dir][1];

        if(!isInMap(dx, dy)) {
            dir = reverseDir[stone.dir];
            dx = stone.x + direction[dir][0];
            dy = stone.y + direction[dir][1];

            blueSection(dx, dy, stone);
        } else {
            if(map[dx][dy].color == 0)
                whiteSection(dx, dy, stone);
            else if(map[dx][dy].color == 1)
                redSection(dx, dy, stone);
            else if(map[dx][dy].color == 2)
                blueSection(dx, dy, stone);
        }
    }

    static void whiteSection(int x, int y, Stone stone) {
        ArrayList<Integer> stoneList = getStoneList(stone.x, stone.y, stone);

        insertStone(x, y, stoneList);

        moveStoneList(stone.x, stone.y, stone, x, y, stoneList);
    }

    static void redSection(int x, int y, Stone stone) {
        ArrayList<Integer> stoneList = getStoneList(stone.x, stone.y, stone);

        ArrayList<Integer> reverse = reverseStone(stoneList);

        insertStone(x, y, reverse);

        moveStoneList(stone.x, stone.y, stone, x, y, stoneList);
    }

    static void blueSection(int x, int y, Stone stone) {
        ArrayList<Integer> stoneList = getStoneList(stone.x, stone.y, stone);

        int dir = reverseDir[stone.dir];
        int dx = stone.x + direction[dir][0], dy = stone.y + direction[dir][1];

        System.out.println("dx = " + dx);
        System.out.println("dy = " + dy);
        System.out.println("stoneList = " + stoneList);

        if(map[dx][dy].color == 2)
            return;

        stones[stone.stoneNum].dir = dir;
        moveStoneList(stone.x, stone.y, stone, x, y, stoneList);
    }

    static void moveStoneList(int prevX, int prevY, Stone stone, int x, int y, ArrayList<Integer> stoneList) {
        removeStoneList(prevX, prevY, stone);

        for(int stoneNum : stoneList) {
            System.out.println("stoneNum = " + stoneNum);
            Stone s = stones[stoneNum];
            s.x = x;
            s.y = y;

//            map[x][y].stones.add(stoneNum);
        }
    }

    static void removeStoneList(int x, int y, Stone stone) {
        ArrayList<Integer> existing = map[x][y].stones;

        int index = 0, size = existing.size();
        for(index = 0; index < size; index++) {
            if(existing.get(index) == stone.stoneNum)
                break;
        }

        for(int idx = index; idx < size; idx++) {
            existing.remove(index);
        }
    }

    static void insertStone(int x, int y, ArrayList<Integer> stoneList) {
        ArrayList<Integer> existing = map[x][y].stones;
        existing.addAll(stoneList);
    }

    static ArrayList<Integer> reverseStone(ArrayList<Integer> stoneList) {
        ArrayList<Integer> reverse = new ArrayList<>();

        for(int idx = stoneList.size() - 1; idx >= 0; idx--)
            reverse.add(stoneList.get(idx));

        return reverse;
    }

    static ArrayList<Integer> getStoneList(int x, int y, Stone stone) {
        ArrayList<Integer> stoneList = new ArrayList<>();
        ArrayList<Integer> existing = map[x][y].stones;
        System.out.println("existing = " + existing);

        int index = 0, size = existing.size();
        for(index = 0; index < size; index++) {
            if(existing.get(index) == stone.stoneNum)
                break;
        }

        for(int idx = index; idx < size; idx++) {
            stoneList.add(existing.get(idx));
        }

        System.out.println("stoneList = " + stoneList);

        return stoneList;
    }

    static boolean isInMap(int x, int y) {
        if(x >= 0 && x < N && y >= 0 && y < N) return true;
        return false;
    }

    static class Stone {
        int x, y, stoneNum, dir;
        // dir -> 1 : 오, 2 : 왼, 3 : 위, 4 : 아래

        public Stone(int x, int y, int stoneNum, int dir) {
            this.x = x;
            this.y = y;
            this.stoneNum = stoneNum;
            this.dir = dir;
        }
    }

    static class Section {
        // 0 : 흰색, 1 : 빨강, 2 : 파랑
        int color;
        ArrayList<Integer> stones;

        public Section(int color) {
            this.color = color;
            stones = new ArrayList<>();
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
