package showMeTheCode1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BuyLiquidMedicine_bj24954 {
	static int N;
    static int[] prices, order;
    static ArrayList<Liquid>[] discountList;
    static ArrayList<int[]> orders;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        prices = new int[N + 1];
        for(int liquid = 1; liquid <= N; liquid++)
            prices[liquid] = scanner.nextInt();
        discountList = new ArrayList[N + 1];
        for(int liquid = 1; liquid <= N; liquid++) {
            int num = scanner.nextInt();
            discountList[liquid] = new ArrayList<>();
            for(int count = 1; count <= num; count++) {
                int idx = scanner.nextInt(), discount = scanner.nextInt();
                discountList[liquid].add(new Liquid(idx, discount));
            }
        }
    }

    static void solution() {
        order = new int[N];
        orders = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        getOrders(0, 0, visited);
        int answer = Integer.MAX_VALUE;
        for(int[] liquidOrder : orders) {
            answer = Math.min(answer, getPrice(liquidOrder));
        }
        System.out.println(answer);
    }

    static int getPrice(int[] liquidOrder) {
        int[] priceCopy = prices.clone();
        int total = 0;
        for(int idx = 0; idx < liquidOrder.length; idx++) {
            total += priceCopy[liquidOrder[idx]];
            for(Liquid discount : discountList[liquidOrder[idx]]) {
                priceCopy[discount.idx] -= discount.discount;
                if(priceCopy[discount.idx] <= 0) priceCopy[discount.idx] = 1;
            }
        }
        return total;
    }

    static void getOrders(int index, int count, boolean[] visited) {
        if(count == N) {
            int[] copy = order.clone();
            orders.add(copy);
            return;
        }
        for(int idx = 1; idx <= N; idx++) {
            if(!visited[idx]) {
                visited[idx] = true;
                order[index] = idx;
                getOrders(index + 1, count + 1, visited);
                visited[idx] = false;
            }
        }
    }

    static class Liquid {
        int idx, discount;
        public Liquid(int idx, int discount) {
            this.idx = idx;
            this.discount = discount;
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
