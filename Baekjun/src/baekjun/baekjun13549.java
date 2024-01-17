package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun13549 {
    static final int MAX_LOC = 100_000;

    static int subinLoc;
    static int brotherLoc;

    static void input() {
        Reader scanner = new Reader();

        subinLoc = scanner.nextInt();
        brotherLoc = scanner.nextInt();
    }

    static void solution() {
        System.out.println(findFastestTime(subinLoc, brotherLoc));
    }

    static int findFastestTime(int startLoc, int endLoc) {
        Queue<Position> positions = new PriorityQueue<>();
        int[] moveCounts = new int[MAX_LOC * 2];
        Arrays.fill(moveCounts, Integer.MAX_VALUE);

        positions.offer(new Position(startLoc, 0));
        moveCounts[startLoc] = 0;

        while (!positions.isEmpty()) {
            Position cur = positions.poll();
            if (cur.loc == endLoc) {
                return cur.moveCount;
            }
            if (moveCounts[cur.loc] < cur.moveCount) {
                continue;
            }

            int nextLoc = cur.loc + 1;
            addNextLoc(nextLoc, false, cur, moveCounts, positions);

            nextLoc = cur.loc - 1;
            addNextLoc(nextLoc, false, cur, moveCounts, positions);

            nextLoc = cur.loc * 2;
            addNextLoc(nextLoc, true, cur, moveCounts, positions);
        }

        return -1;
    }

    static void addNextLoc(int nextLoc, boolean isTeleport, Position cur, int[] moveCounts,
                           Queue<Position> positions) {
        if (isTeleport) {
            if (isPossibleForNextLoc(nextLoc, cur.moveCount, moveCounts)) {
                moveCounts[nextLoc] = cur.moveCount;
                positions.offer(new Position(nextLoc, cur.moveCount));
            }
            return;
        }

        if (isPossibleForNextLoc(nextLoc, cur.moveCount + 1, moveCounts)) {
            moveCounts[nextLoc] = cur.moveCount + 1;
            positions.offer(new Position(nextLoc, cur.moveCount + 1));
        }
    }

    static boolean isPossibleForNextLoc(int nextLoc, int nextMoveCount, int[] moveCounts) {
        return isInMap(nextLoc) && moveCounts[nextLoc] > nextMoveCount;
    }

    static boolean isInMap(int loc) {
        return 0 <= loc && loc < MAX_LOC * 2;
    }

    static class Position implements Comparable<Position> {
        int loc;
        int moveCount;

        public Position(int loc, int moveCount) {
            this.loc = loc;
            this.moveCount = moveCount;
        }

        @Override
        public int compareTo(Position o) {
            return moveCount - o.moveCount;
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
    }
}
