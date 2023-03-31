package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun20061 {
	static int N, point;
    static Block[] blocks;
    static boolean[][] blue, green;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        blocks = new Block[N];
        blue = new boolean[4][6];
        green = new boolean[6][4];

        for(int block = 0; block < N; block++) {
            int blockType = scanner.nextInt(), x = scanner.nextInt(), y = scanner.nextInt();
            blocks[block] = new Block(x, y, blockType);
        }
    }

    static void solution() {
        for(Block block : blocks) oneTurn(block);

        StringBuilder sb = new StringBuilder();
        sb.append(point).append('\n').append(countBlockNum());
        System.out.println(sb);
    }

    static int countBlockNum() {
        int count = 0;
        for(int row = 0; row < green.length; row++) {
            for(int col = 0; col < green[row].length; col++) {
                if(green[row][col]) count++;
                if(blue[col][row]) count++;
            }
        }

        return count;
    }

    static void oneTurn(Block block) {
        moveInGreen(block);
        moveInBlue(block);
    }

    static void moveInGreen(Block block) {
        // 블럭 타입이 1 또는 3이라면 해당 열만 확인
        // 0부터 시작해서 블럭이 있는 곳을 찾음
        // 해당 위치 직전에 블럭을 놓고 만약 블럭 타입이 3이라면 그 전 위치에도 블럭을 놓는다
        if(block.blockType == 1 || block.blockType == 3) {
            int row, col = block.y;
            for(row = 0; row < green.length; row++)
                if(green[row][col]) break;
            row--;

            green[row][col] = true;
            if(block.blockType == 3) green[row - 1][col] = true;
        } else { // 블럭 타입이 2라면
            // 0부터 시작해서 블럭이 있는 곳을 찾는데,
            // 해당 열과 다음 열까지 확인하여 해당 위치 직전에 해당 열 및 다음 열에 블럭을 놓는다
            int row, col = block.y;
            for(row = 0; row < green.length; row++)
                if(green[row][col] || green[row][col + 1]) break;
            row--;

            green[row][col] = green[row][col + 1] = true;
        }

        // 하나의 행이 모두 블럭으로 되어있는 곳을 찾는다
        for(int row = 0; row < green.length; row++) {
            boolean flag = true;
            for(int col = 0; col < green[row].length; col++) {
                if(!green[row][col]) {
                    flag = false;
                    break;
                }
            }

            // 만약 해당 행이 모두 블럭으로 되어 있다면
            // 모든 블럭을 한 줄씩 내리고 point를 1 증가시킨다
            if(flag) {
                Arrays.fill(green[row], false);
                if(row != 0) {
                    for(int prevRow = row - 1; prevRow >= 0; prevRow--)
                        green[prevRow + 1] = green[prevRow].clone();
                    Arrays.fill(green[0], false);
                }
                point++;
            }
        }

        // 흐린 영역에 블럭이 위치하는지 찾아본다
        for(int row = 0; row <= 1; row++) {
            boolean flag = false;
            for(int col = 0; col < green[row].length; col++) {
                if(green[row][col]) {
                    flag = true;
                    break;
                }
            }

            // 해당 행에 블럭이 존재한다면
            // 마지막 줄을 제거하고 모든 블럭을 아래로 한 줄씩 내린다
            if(flag) {
                for(int prevRow = green.length - 1; prevRow > 0; prevRow--)
                    green[prevRow] = green[prevRow - 1].clone();
                Arrays.fill(green[row], false);
            }
        }
    }

    static void moveInBlue(Block block) {
        // 블럭 타입이 1 또는 2이라면 해당 행만 확인
        // 0부터 시작해서 블럭이 있는 곳을 찾음
        // 해당 위치 직전에 블럭을 놓고 만약 블럭 타입이 2이라면 그 전 위치에도 블럭을 놓는다
        if(block.blockType == 1 || block.blockType == 2) {
            int row = block.x, col;
            for(col = 0; col < blue[0].length; col++)
                if(blue[row][col]) break;
            col--;

            blue[row][col] = true;
            if(block.blockType == 2) blue[row][col - 1] = true;
        } else { // 블럭 타입이 3이라면
            // 0부터 시작해서 블럭이 있는 곳을 찾는데,
            // 해당 행과 다음 행까지 확인하여 해당 위치 직전에 해당 행 및 다음 행에 블럭을 놓는다
            int row = block.x, col;
            for(col = 0; col < blue[0].length; col++)
                if(blue[row][col] || blue[row + 1][col]) break;
            col--;

            blue[row][col] = blue[row + 1][col] = true;
        }

        // 하나의 열이 모두 블럭으로 되어있는 곳을 찾는다
        for(int col = 0; col < blue[0].length; col++) {
            boolean flag = true;
            for(int row = 0; row < blue.length; row++) {
                if(!blue[row][col]) {
                    flag = false;
                    break;
                }
            }

            // 만약 해당 열이 모두 블럭으로 되어 있다면
            // 모든 블럭을 한 줄씩 오른쪽으로 밀고 point를 1 증가시킨다
            if(flag) {
                for(int row = 0; row < blue.length; row++) blue[row][col] = false;
                if(col != 0) {
                    for(int prevCol = col - 1; prevCol >= 0; prevCol--) {
                        for(int row = 0; row < blue.length; row++)
                            blue[row][prevCol + 1] = blue[row][prevCol];
                    }
                    for(int row = 0; row < blue.length; row++)
                        blue[row][0] = false;
                }
                point++;
            }
        }

        // 흐린 영역에 블럭이 위치하는지 찾아본다
        for(int col = 0; col <= 1; col++) {
            boolean flag = false;
            for(int row = 0; row < blue.length; row++) {
                if(blue[row][col]) {
                    flag = true;
                    break;
                }
            }

            // 해당 열에 블럭이 존재한다면
            // 마지막 줄을 제거하고 모든 블럭을 오른쪽으로 한 줄씩 민다
            if(flag) {
                for(int prevCol = blue[0].length - 1; prevCol > 0; prevCol--) {
                    for(int row = 0; row < blue.length; row++)
                        blue[row][prevCol] = blue[row][prevCol - 1];
                }

                for(int row = 0; row < blue.length; row++)
                    blue[row][col] = false;
            }
        }
    }

    static class Block {
        int x, y, blockType;

        public Block(int x, int y, int blockType) {
            this.x = x;
            this.y = y;
            this.blockType = blockType;
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
