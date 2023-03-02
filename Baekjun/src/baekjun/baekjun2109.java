package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun2109 {
	static int n;
    static PriorityQueue<Lecture> lectures;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        lectures = new PriorityQueue<>();

        for(int lecture = 0; lecture < n; lecture++) {
            int pay = scanner.nextInt(), deadline = scanner.nextInt();
            lectures.offer(new Lecture(deadline, pay));
        }
    }

    static void solution() {
        PriorityQueue<Integer> pays = new PriorityQueue<>();

        for(int idx = 0; idx < n; idx++) {
            Lecture lecture = lectures.poll();
            pays.offer(lecture.pay);

            while(!pays.isEmpty() && lecture.deadline < pays.size())
                pays.poll();
        }

        int maxPay = 0;
        while(!pays.isEmpty())
            maxPay += pays.poll();

        System.out.println(maxPay);
    }

    static class Lecture implements Comparable<Lecture> {
        int deadline, pay;

        public Lecture(int deadline, int pay) {
            this.deadline = deadline;
            this.pay = pay;
        }

        @Override
        public int compareTo(Lecture l) {
            if(this.deadline != l.deadline) return this.deadline - l.deadline;
            return l.pay - this.pay;
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
