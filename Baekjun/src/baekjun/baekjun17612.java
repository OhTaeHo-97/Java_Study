package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun17612 {
	static int N, k;
    static int[][] customers;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        k = scanner.nextInt();
        customers = new int[N][2];

        for(int idx = 0; idx < N; idx++) {
            int id = scanner.nextInt(), time = scanner.nextInt();

            customers[idx][0] = id; customers[idx][1] = time;
        }
    }

    static void solution() {
        int index = 0, time = 0;
        PriorityQueue<Customer> queue = new PriorityQueue<>();
        List<Integer> orders = new ArrayList<>();

        // init
        for(int idx = 1; idx <= k && index < N; idx++) {
            queue.offer(new Customer(customers[index][0], customers[index][1], idx));
            index++;
        }

        while(!queue.isEmpty()) {
            PriorityQueue<Customer> exitCustomers = new PriorityQueue<>(new Comparator<Customer>() {
                @Override
                public int compare(Customer c1, Customer c2) {
                    return c2.counterNum - c1.counterNum;
                }
            });
            PriorityQueue<Integer> counterNums = new PriorityQueue<>();
            Customer cur = queue.poll();
            counterNums.offer(cur.counterNum);
            exitCustomers.offer(cur);
            int curTime = cur.time;
            while(!queue.isEmpty() && queue.peek().time <= curTime) {
                Customer customer = queue.poll();
                exitCustomers.offer(customer);
                counterNums.offer(customer.counterNum);
            }

            while(!exitCustomers.isEmpty()) orders.add(exitCustomers.poll().id);

            while(queue.size() < k && index < N) {
                queue.offer(new Customer(customers[index][0], curTime + customers[index][1], counterNums.poll()));
                index++;
            }
        }

        long answer = 0;
        for(int idx = 1; idx <= orders.size(); idx++)
            answer += (long)idx * orders.get(idx - 1);

        System.out.println(answer);
    }

    static class Customer implements Comparable<Customer> {
        int id, time, counterNum;

        public Customer(int id, int time, int counterNum) {
            this.id = id;
            this.time = time;
            this.counterNum = counterNum;
        }

        @Override
        public int compareTo(Customer c) {
            return time - c.time;
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
