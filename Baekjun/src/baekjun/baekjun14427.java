package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class baekjun14427 {
	static StringBuilder sb = new StringBuilder();
    static int N, M;
    static TreeMap<Integer, PriorityQueue<Integer>> map;
    static int[] series;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        series = new int[N + 1];
        map = new TreeMap<>();

        for(int idx = 1; idx <= N; idx++) {
            int num = scanner.nextInt();
            if(!map.containsKey(num)) map.put(num, new PriorityQueue<>());
            map.get(num).add(idx);
            series[idx] = num;
        }

        M = scanner.nextInt();
        for(int query = 1; query <= M; query++) {
            int queryType = scanner.nextInt();
            if(queryType == 1) {
                int idx = scanner.nextInt(), newValue = scanner.nextInt();
                int originalValue = series[idx];

                series[idx] = newValue;

                PriorityQueue<Integer> idxList = map.get(originalValue);
                idxList.remove(Integer.valueOf(idx));
                if(idxList.isEmpty()) map.remove(originalValue);

                if(!map.containsKey(newValue)) map.put(newValue, new PriorityQueue<>());
                map.get(newValue).offer(idx);
            } else if(queryType == 2) {
                Map.Entry<Integer, PriorityQueue<Integer>> firstEntry = map.firstEntry();
                sb.append(firstEntry.getValue().peek()).append('\n');
            }
        }
    }

    public static void main(String[] args) {
        input();
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
