package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun26019 {
    static int targetMonth;
    static int replacePeriod;
    static List<Edge> cpus;

    static void input() {
        Reader scanner = new Reader();

        targetMonth = scanner.nextInt();
        replacePeriod = scanner.nextInt();
        cpus = new ArrayList<>();

        for(int month = 1; month <= targetMonth; month++) {
            int cpuPrice = scanner.nextInt();
            for(int usedMonth = 1; usedMonth <= Math.min(replacePeriod, targetMonth - month + 1); usedMonth++) {
                cpus.add(new Edge(month, month + usedMonth, cpuPrice - scanner.nextInt()));
            }
        }
    }

    static void solution() {
        long[] prices = bellmanFord(1);
        System.out.println(prices[targetMonth + 1]);
    }

    static long[] bellmanFord(int startMonth) {
        long[] prices = new long[targetMonth + 2];
        Arrays.fill(prices, Long.MAX_VALUE);
        prices[startMonth] = 0;

        boolean isChanged = false;

        for(int count = 0; count < targetMonth; count++) {
            isChanged = false;
            for(Edge next : cpus) {
                if(prices[next.startMonth] == Long.MAX_VALUE) {
                    continue;
                }

                int nextMonth = next.endMonth;
                long nextPrice = prices[next.startMonth] + next.price;
                if(prices[nextMonth] > nextPrice) {
                    isChanged = true;
                    prices[nextMonth] = nextPrice;
                }
            }

            if(!isChanged) {
                break;
            }
        }

        return prices;
    }

    static class Edge {
        int startMonth;
        int endMonth;
        long price;

        public Edge(int startMonth, int endMonth, long price) {
            this.startMonth = startMonth;
            this.endMonth = endMonth;
            this.price = price;
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
