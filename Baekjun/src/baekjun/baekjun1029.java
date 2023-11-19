package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun1029 {
    static int artistCount;
    static int[][] prices;
    static int[][][] dp;

    static void input() {
        Reader scanner = new Reader();

        artistCount = scanner.nextInt();
        prices = new int[artistCount][artistCount];

        for (int artist1 = 0; artist1 < artistCount; artist1++) {
            String priceInfo = scanner.nextLine();
            for (int artist2 = 0; artist2 < artistCount; artist2++) {
                prices[artist1][artist2] = priceInfo.charAt(artist2) - '0';
            }
        }

        dp = new int[artistCount][1 << artistCount][10];
    }

    /*
     * dp[artist][visited][price] = artist번 아티스트가 price 가격으로 그림을 사고 visited 상태일 때 그림을 소유했던 사람의 수의 최댓값
     *  - visited : 1번부터 N번까지의 아티스트를 각 bit로 표현하여 그림을 가졌던 사람들의 bit를 1로 표현
     *  - ex. dp[3][001101(2)][3]
     *      - 1, 3, 4번 아티스트가 그림을 소유했던 사람이고, 현재 3번 아티스트가 그림을 소유하고 있는데 3번 아티스트가 3이라는 가격으로 그림을 샀을 때
     *        그림을 계속 사고팔며 마지막에 그림을 소유했던 모든 사람의 수의 최댓값을 나타낸다
     *
     * 처음에는 그림을 소유했던 아티스트의 상태, 현재 그림을 소유하고 있는 아티스트를 기준으로 DP를 진행하려고 하였으나
     * 같은 상태라도 이전에 누구에게 그림을 사왔는지, 얼마에 그림을 사왔는지에 따라 이후의 결과가 달라져 단순히 저 두 기준만으로는 DP를 진행할 수 없었다
     * 현재 그림을 소유하고 있는 아티스트가 그림을 팔 때 직전에 얼마에 그림을 사왔는지 가격에 따라 다음에 누구에게 그림을 팔 수 있는지가 결정되기 때문에
     * 가격을 DP의 기준으로 추가했다
     *
     * 재귀를 통해 1번 아티스트부터 시작해서 그림을 팔아나가는데,
     * 현재 그림을 소유하고 있는 아티스트, 그때의 아티스트들의 상태, 직전에 사온 가격을 기준으로 DP값을 확인하여
     * 0이 아니라면 이미 그 상태에서 그림을 소유할 수 있는 사람의 최댓값을 구한 것이기 때문에 그 값을 그대로 반환한다
     * 그렇지 않다면 아직 그림을 소유하지 않은 아티스트들 중에서 직전에 사온 가격보다 더 비싸게 혹은 같은 가격에 팔 수 있는 아티스트를 찾아
     * 재귀를 통해 해당 아티스트들에게 그림을 팔아본다
     */
    static void solution() {
        System.out.println(dfs(0, 0, 1, 1));
    }

    static int dfs(int prevArtist, int price, int numberOfArtist, int bit) {
        int maxNumberOfArtist = numberOfArtist;
        int nextNumberOfArtist;
        if (dp[prevArtist][bit][price] != 0) {
            return dp[prevArtist][bit][price];
        }

        for (int artist = 0; artist < artistCount; artist++) {
            if ((bit | (1 << artist)) != bit && price <= prices[prevArtist][artist]) {
                if (maxNumberOfArtist < (nextNumberOfArtist = dfs(artist, prices[prevArtist][artist],
                        numberOfArtist + 1,
                        bit | (1 << artist)))) {
                    maxNumberOfArtist = nextNumberOfArtist;
                }
            }
        }

        dp[prevArtist][bit][price] = maxNumberOfArtist;
        return maxNumberOfArtist;
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
