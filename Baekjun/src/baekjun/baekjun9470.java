package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun9470 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int K, M, P;
    static HashMap<Integer, ArrayList<Integer>> parentNode;
    static ArrayList<Integer>[] orders;
    static int[] childNodeNum, strahlerOrder;

    static void input() {
        K = scanner.nextInt();
        M = scanner.nextInt();
        P = scanner.nextInt();
        childNodeNum = new int[M + 1];
        strahlerOrder = new int[M + 1];
        orders = new ArrayList[M + 1];
        parentNode = new HashMap<>();
        for(int node = 1; node <= M; node++) {
            parentNode.put(node, new ArrayList<>());
            orders[node] = new ArrayList<>();
        }


        for(int edge = 0; edge < P; edge++) {
            int start = scanner.nextInt(), end = scanner.nextInt();

            childNodeNum[end]++;
            parentNode.get(start).add(end);
        }
    }

    static void solution() {
        getStrahlerOrder();

        int[] sortedOrders = Arrays.stream(strahlerOrder).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer :: valueOf)
                .toArray();

        sb.append(K).append(' ').append(sortedOrders[0]).append('\n');
    }

    static void getStrahlerOrder() {
        Queue<Integer> queue = new LinkedList<>();
        for(int node = 1; node <= M; node++) {
            if(childNodeNum[node] == 0) {
                queue.offer(node);
                strahlerOrder[node] = 1;
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next : parentNode.get(cur)) {
                orders[next].add(strahlerOrder[cur]);
                childNodeNum[next]--;

                if(childNodeNum[next] == 0) {
                    if(orders[next].size() == 1) strahlerOrder[next] = orders[next].get(0);
                    else {
                        Collections.sort(orders[next], Collections.reverseOrder());
                        int max = orders[next].get(0);

                        if(max == orders[next].get(1)) strahlerOrder[next] = max + 1;
                        else strahlerOrder[next] = max;
                    }
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int T = scanner.nextInt();
        while(T-- > 0) {
            input();
            solution();
        }
        System.out.print(sb);
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
