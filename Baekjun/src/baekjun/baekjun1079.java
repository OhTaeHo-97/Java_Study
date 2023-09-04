package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1079 {
    static int answer;
    static int N;
    static int[] guiltyScores;
    static int[][] R;
    static int ej;
    static boolean[] isDeleted;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        answer = Integer.MIN_VALUE;
        guiltyScores = new int[N];
        R = new int[N][N];
        isDeleted = new boolean[N];

        for(int idx = 0; idx < N; idx++) {
            guiltyScores[idx] = scanner.nextInt();
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                R[row][col] = scanner.nextInt();
            }
        }

        ej = scanner.nextInt();
    }

    static void solution() {
        doMafiaGame(N, 0);
        System.out.println(answer);
    }

    static void doMafiaGame(int participantNum, int nightNum) {
        if((participantNum == 1 && !isDeleted[ej]) || isDeleted[ej]) {
            answer = Math.max(answer, nightNum);
            return;
        }

        if(participantNum % 2 == 1) {
            int maxParticipant = afternoonTurn();
            isDeleted[maxParticipant] = true;
            doMafiaGame(participantNum - 1, nightNum);
            isDeleted[maxParticipant] = false;
        } else {
            for(int idx = 0; idx < N; idx++) {
                if(isDeleted[idx])
                    continue;

                changeGuiltyScores(idx, true);
                isDeleted[idx] = true;
                doMafiaGame(participantNum - 1, nightNum + 1);
                changeGuiltyScores(idx, false);
                isDeleted[idx] = false;
            }
        }
    }

    static int afternoonTurn() {
        int maxGuiltyScore = Integer.MIN_VALUE;
        int maxParticipant = -1;
        for(int idx = 0; idx < N; idx++) {
            if(isDeleted[idx])
                continue;

            if(maxGuiltyScore < guiltyScores[idx]) {
                maxGuiltyScore = guiltyScores[idx];
                maxParticipant = idx;
            }
        }

        return maxParticipant;
    }

    static void changeGuiltyScores(int mafia, boolean isPlus) {
        for(int idx = 0; idx < N; idx++) {
            if(isDeleted[idx] || idx == mafia)
                continue;

            if(isPlus)
                guiltyScores[idx] += R[mafia][idx];
            else
                guiltyScores[idx] -= R[mafia][idx];
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
