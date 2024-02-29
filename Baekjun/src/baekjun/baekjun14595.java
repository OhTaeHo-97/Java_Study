package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14595 {
    static int roomCount;
    static int actionCount;
    static int[] endRoom;

    static void input() {
        Reader scanner = new Reader();

        roomCount = scanner.nextInt();
        actionCount = scanner.nextInt();
        endRoom = new int[roomCount + 1];
        for (int room = 1; room <= roomCount; room++) {
            endRoom[room] = room;
        }

        for (int idx = 0; idx < actionCount; idx++) {
            endRoom[scanner.nextInt()] = scanner.nextInt();
        }
    }

    static void solution() {
        int answer = 1;
        int start = 1;
        int end = endRoom[1];

        for (int room = 2; room <= roomCount; room++) {
            if (isBetweenStartAndEnd(start, end, room) && end < endRoom[room]) {
                end = endRoom[room];
                continue;
            }
            if (room > end) {
                start = room;
                end = endRoom[room];
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean isBetweenStartAndEnd(int start, int end, int room) {
        return start <= room && room <= end;
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
