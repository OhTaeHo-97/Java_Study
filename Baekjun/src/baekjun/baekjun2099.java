package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2099 {
    static int participantNum;
    static int maxTurnNum;
    static int gameNum;

    static int[][] relations;
    static int[][] games;

    static void input() {
        Reader scanner = new Reader();

        participantNum = scanner.nextInt();
        maxTurnNum = scanner.nextInt();
        gameNum = scanner.nextInt();
        relations = new int[participantNum][participantNum];
        games = new int[gameNum][2];

        for(int idx = 0; idx < participantNum; idx++) {
            int person1 = scanner.nextInt() - 1;
            int person2 = scanner.nextInt() - 1;

            relations[idx][person1] = relations[idx][person2] = 1;
        }

        for(int idx = 0; idx < gameNum; idx++) {
            games[idx][0] = scanner.nextInt() - 1;
            games[idx][1] = scanner.nextInt() - 1;
        }
    }

    static void solution() {
        int[][] finalRelations = findFinalRelations(relations, maxTurnNum);

        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < gameNum; idx++) {
            sb.append(finalRelations[games[idx][0]][games[idx][1]] == 0 ? "life" : "death").append('\n');
        }

        System.out.print(sb);
    }

    static int[][] findFinalRelations(int[][] relations, int gameNum) {
        if(gameNum == 1) {
            return relations;
        }

        int[][] temp = findFinalRelations(relations, gameNum / 2);
        int[][] result = multiplyMatrix(temp, temp);
        if(gameNum % 2 == 1) {
            result = multiplyMatrix(result, relations);
        }
        return result;
    }

    static int[][] multiplyMatrix(int[][] mat1, int[][] mat2) {
        int[][] result = new int[mat1.length][mat1[0].length];

        for(int row = 0; row < participantNum; row++) {
            for(int col = 0; col < participantNum; col++) {
                int sum = 0;
                for(int idx = 0; idx < participantNum; idx++) {
                    sum += (mat1[row][idx] * mat2[idx][col]);
                }

                if(sum != 0) {
                    result[row][col] = 1;
                }
            }
        }

        return result;
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
