package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun2461 {
    static int N, M;
    static PriorityQueue<Integer>[] abilityNums;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        abilityNums = new PriorityQueue[N];
        for(int idx = 0; idx < N; idx++)
            abilityNums[idx] = new PriorityQueue<>();

        for(int idx = 0; idx < N; idx++) {
            for(int num = 0; num < M; num++)
                abilityNums[idx].offer(scanner.nextInt());
        }
    }

    static void solution() {
        PriorityQueue<Ability> abilities = new PriorityQueue<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int idx = 0; idx < N; idx++) {
            int ability = abilityNums[idx].poll();

            min = Math.min(min, ability);
            max = Math.max(max, ability);

            abilities.offer(new Ability(idx, ability));
        }

        int minDiff = max - min;
        while(true) {
            if(abilityNums[abilities.peek().idx].isEmpty()) break;

            Ability ability = abilities.poll();
            int abilityNum = abilityNums[ability.idx].poll();

            max = Math.max(max, abilityNum);

            abilities.offer(new Ability(ability.idx, abilityNum));
            minDiff = Math.min(minDiff, max - abilities.peek().num);
        }

        System.out.println(minDiff);
    }

    static class Ability implements Comparable<Ability> {
        int idx, num;

        public Ability(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Ability o) {
            if(num != o.num) return num - o.num;
            return idx - o.idx;
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
