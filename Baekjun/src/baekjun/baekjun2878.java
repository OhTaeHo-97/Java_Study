package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2878 {
    static final BigInteger DIVISOR = new BigInteger("2").pow(64);

    static int candyCount;
    static int friendsCount;
    static long totalFriendsCandyCount;
    static int[] requiredCandies;

    static void input() {
        Reader scanner = new Reader();

        candyCount = scanner.nextInt();
        friendsCount = scanner.nextInt();
        totalFriendsCandyCount = 0L;
        requiredCandies = new int[friendsCount];

        for (int idx = 0; idx < friendsCount; idx++) {
            requiredCandies[idx] = scanner.nextInt();
            totalFriendsCandyCount += requiredCandies[idx];
        }
    }

    static void solution() {
        long lackCount = totalFriendsCandyCount - candyCount;
        Arrays.sort(requiredCandies);

        BigInteger answer = BigInteger.ZERO;
        for (int idx = 0; idx < friendsCount; idx++) {
            long removeCount = Math.min(requiredCandies[idx], lackCount / (friendsCount - idx));
            lackCount -= removeCount;
            answer = answer.add(new BigInteger(Long.toString(removeCount)).pow(2));
            answer = answer.remainder(DIVISOR);
        }

        System.out.println(answer);
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
