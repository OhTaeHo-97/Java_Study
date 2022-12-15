package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun2623 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] related;
    static HashMap<Integer, HashSet<Integer>> followings, precede; // 후행, 선행되어있는 것들
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        M = scanner.nextInt();
        related = new int[N + 1];
        followings = new HashMap<>();
        precede = new HashMap<>();
        for(int singer = 1; singer <= N; singer++) {
            followings.put(singer, new HashSet<>());
            precede.put(singer, new HashSet<>());
        }
        for(int idx = 0; idx < M; idx++) {
            int num = scanner.nextInt();
            int singer = scanner.nextInt();
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(singer));
            for(int s = 1; s < num; s++) {
                singer = scanner.nextInt();
                for(int pre : list) {
                    precede.get(singer).add(pre);
                    followings.get(pre).add(singer);
                }
                list.add(singer);
            }
        }
    }

    static void solution() {
        Queue<Integer> queue = new LinkedList<>();
        LinkedList<Integer> answer = new LinkedList<>();
        int[] related = new int[N + 1];
        for(int key : precede.keySet()) {
            related[key] = precede.get(key).size();
            if(related[key] == 0) {
                queue.offer(key);
                answer.add(key);
            }
        }
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int singer : followings.get(cur)) {
                related[singer]--;
                if(related[singer] == 0) {
                    queue.offer(singer);
                    answer.add(singer);
                }
            }
        }
        if(answer.size() == N) {
            for(int singer : answer) sb.append(singer).append('\n');
        } else sb.append(0).append('\n');
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;
        public Reader() {
            br = new BufferedReader(new InputStreamReader((System.in)));
        }
        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(IOException e) {
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