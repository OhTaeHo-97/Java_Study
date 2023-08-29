package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun12872 {
    static final int DIVISOR = 1_000_000_007;
    static int N, M, P;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        P = scanner.nextInt();
    }

    static void solution() {
        long[][] playlists = new long[P + 1][N + 1];
        calculatePlaylistNum(playlists);
        System.out.println(playlists[P][N]);
    }

    static void calculatePlaylistNum(long[][] playlists) {
        playlists[0][0] = 1L;

        for(int musicNum = 1; musicNum <= P; musicNum++) {
            for(int savedMusicNum = 1; savedMusicNum <= N; savedMusicNum++) {
                playlists[musicNum][savedMusicNum] += (N - savedMusicNum + 1) * playlists[musicNum - 1][savedMusicNum - 1];
                playlists[musicNum][savedMusicNum] %= DIVISOR;

                if(savedMusicNum > M) {
                    playlists[musicNum][savedMusicNum] += (savedMusicNum - M) * playlists[musicNum - 1][savedMusicNum];
                    playlists[musicNum][savedMusicNum] %= DIVISOR;
                }
            }
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
