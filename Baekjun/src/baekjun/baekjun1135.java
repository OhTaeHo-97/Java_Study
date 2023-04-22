package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1135 {
	static int N;
    static HashMap<Integer, ArrayList<Integer>> relation;
    static int[] times;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        times = new int[N];

        relation = new HashMap<>();
        for(int employee = 0; employee < N; employee++)
            relation.put(employee, new ArrayList<>());

        for(int employee = 0; employee < N; employee++) {
            int num = scanner.nextInt();
            if(num == -1) continue;
            relation.get(num).add(employee);
        }
    }

    static void solution() {
        System.out.println(calcMinTime(0));
    }

    static int calcMinTime(int employee) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for(int next : relation.get(employee)) {
            times[next] = calcMinTime(next);
            queue.offer(new int[] {next, times[next]});
        }

        int count = 0, max = 0;
        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            max = Math.max(max, temp[1] + (++count));
        }

        return max;
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
