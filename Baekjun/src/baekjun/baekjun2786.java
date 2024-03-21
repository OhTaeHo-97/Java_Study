package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun2786 {
    static int foodCount;
    static long[] orderPricePrefixSum;
    static int[] gapsOfFirstOrderPriceAndOrderPrice;
    static int[] minFirstOrderPrices;
    static Food[] foods;

    static void input() {
        Reader scanner = new Reader();

        foodCount = scanner.nextInt();
        orderPricePrefixSum = new long[foodCount + 1];
        gapsOfFirstOrderPriceAndOrderPrice = new int[foodCount + 1];
        minFirstOrderPrices = new int[foodCount + 1];
        foods = new Food[foodCount + 1];
        foods[0] = new Food(0, 0);

        for (int foodIdx = 1; foodIdx <= foodCount; foodIdx++) {
            foods[foodIdx] = new Food(scanner.nextInt(), scanner.nextInt());
        }
    }

    static void solution() {
        Arrays.sort(foods);
        calculatePrefixSumAndGapAndMinFirstOrderPrice();
        System.out.print(findMinOrderPrice());
    }

    static String findMinOrderPrice() {
        int minGap = Integer.MAX_VALUE;
        StringBuilder answer = new StringBuilder();
        for (int foodIdx = 1; foodIdx <= foodCount; foodIdx++) {
            if (minGap > gapsOfFirstOrderPriceAndOrderPrice[foodIdx]) {
                minGap = gapsOfFirstOrderPriceAndOrderPrice[foodIdx];
            }
            long sum = Math.min(orderPricePrefixSum[foodIdx - 1] + minFirstOrderPrices[foodIdx],
                    orderPricePrefixSum[foodIdx] + minGap);
            answer.append(sum).append('\n');
        }
        return answer.toString();
    }

    static void calculatePrefixSumAndGapAndMinFirstOrderPrice() {
        int minFirstOrderPrice = Integer.MAX_VALUE;
        for (int foodIdx = 1; foodIdx <= foodCount; foodIdx++) {
            orderPricePrefixSum[foodIdx] = orderPricePrefixSum[foodIdx - 1] + foods[foodIdx].orderPrice;
            gapsOfFirstOrderPriceAndOrderPrice[foodIdx] = foods[foodIdx].firstOrderPrice - foods[foodIdx].orderPrice;

            if (foods[foodCount - foodIdx + 1].firstOrderPrice < minFirstOrderPrice) {
                minFirstOrderPrice = foods[foodCount - foodIdx + 1].firstOrderPrice;
            }
            minFirstOrderPrices[foodCount - foodIdx + 1] = minFirstOrderPrice;
        }
    }

    static class Food implements Comparable<Food> {
        int firstOrderPrice;
        int orderPrice;

        public Food(int firstOrderPrice, int orderPrice) {
            this.firstOrderPrice = firstOrderPrice;
            this.orderPrice = orderPrice;
        }

        @Override
        public int compareTo(Food o) {
            return orderPrice - o.orderPrice;
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
