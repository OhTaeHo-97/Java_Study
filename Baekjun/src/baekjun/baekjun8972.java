package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class baekjun8972 {
    private static int[][] direction = new int[][] {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 0}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private static boolean isFinish = false;
    private static int rowLength;
    private static int colLength;
    private static int[] jongsu;
    private static int[] movingDirection;
    private static int[][] board;
    private static List<MadArduino> madArduinos;

    private static void input() {
        Reader scanner = new Reader();

        rowLength = scanner.nextInt();
        colLength = scanner.nextInt();
        board = new int[rowLength][colLength];
        madArduinos = new ArrayList<>();
        for(int row = 0; row < rowLength; row++) {
            String info = scanner.nextLine();
            for(int col = 0; col < colLength; col++) {
                char status = info.charAt(col);
                if(status == 'I') {
                    jongsu = new int[] {row, col};
                    board[row][col] = 1;
                } else if(status == 'R') {
                    board[row][col] = -1;
                    madArduinos.add(new MadArduino(row, col));
                }
            }
        }

        String directionInfo = scanner.nextLine();
        movingDirection = new int[directionInfo.length()];
        for(int idx = 0; idx < directionInfo.length(); idx++) {
            int direction = directionInfo.charAt(idx) - '0';
            movingDirection[idx] = direction - 1;
        }
    }

    private static void solution() {
        for(int idx = 0; idx < movingDirection.length; idx++) {
            moveJongsu(movingDirection[idx]);
            moveMadArduinos();
            if(isFinish) {
                System.out.println("kraj " + (idx + 1));
                return;
            }
        }
        printBoard();
    }

    private static void printBoard() {
        int[][] newBoard = new int[rowLength][colLength];
        newBoard[jongsu[0]][jongsu[1]] = 1;
        for(MadArduino madArduino : madArduinos) {
            newBoard[madArduino.row][madArduino.col] = -1;
        }

        for(int row = 0; row < rowLength; row++) {
            for(int col = 0; col < colLength; col++) {
                if(newBoard[row][col] == 1) {
                    System.out.print("I");
                } else if(newBoard[row][col] == -1) {
                    System.out.print("R");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static void moveMadArduinos() {
        Set<MadArduino> nextMadArduinos = new HashSet<>();
        Set<MadArduino> removedMadArduinos = new HashSet<>();
        for(MadArduino madArduino : madArduinos) {
            MadArduino nextMadArduinoLoc = findNearestLocToJongsu(madArduino);
            if(isFinish) {
                return;
            }
            if(!nextMadArduinos.add(nextMadArduinoLoc)) {
                removedMadArduinos.add(new MadArduino(nextMadArduinoLoc.row, nextMadArduinoLoc.col));
            }
        }
        removeDuplicatedMadArduinos(nextMadArduinos, removedMadArduinos);
        madArduinos = nextMadArduinos.stream().collect(Collectors.toUnmodifiableList());
    }

    private static void removeDuplicatedMadArduinos(Set<MadArduino> madArduinos, Set<MadArduino> removedMadArduinos) {
        for(MadArduino removedMadArduino : removedMadArduinos) {
            madArduinos.remove(removedMadArduino);
        }
    }

    private static MadArduino findNearestLocToJongsu(MadArduino madArduino) {
        int minDistance = Integer.MAX_VALUE;
        int[] nextMadArduinoLoc = new int[2];

        for(int dir = 0; dir < direction.length; dir++) {
            int[] nextDirection = direction[dir];
            int[] nextMadArduino = new int[] {madArduino.row + nextDirection[0], madArduino.col + nextDirection[1]};
            int distance = Math.abs(jongsu[0] - nextMadArduino[0]) + Math.abs(jongsu[1] - nextMadArduino[1]);
            if(distance < minDistance) {
                minDistance = distance;
                nextMadArduinoLoc = new int[] {nextMadArduino[0], nextMadArduino[1]};
                if(nextMadArduinoLoc[0] == jongsu[0] && nextMadArduinoLoc[1] == jongsu[1]) {
                    isFinish = true;
                    return new MadArduino(nextMadArduinoLoc[0], nextMadArduinoLoc[1]);
                }
            }
        }
        return new MadArduino(nextMadArduinoLoc[0], nextMadArduinoLoc[1]);
    }

    private static void moveJongsu(int dir) {
        int[] moveDirection = direction[dir];
        jongsu[0] += moveDirection[0];
        jongsu[1] += moveDirection[1];
    }

    static class MadArduino {
        int row;
        int col;

        public MadArduino(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) {
                return true;
            }
            if(o == null || o.getClass() != getClass()) {
                return false;
            }

            MadArduino madArduino = (MadArduino) o;
            return this.row == madArduino.row && this.col == madArduino.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
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
            while (st == null || !st.hasMoreElements()) {
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
