package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun1727 {
	static int n, m;
    static int[] manPersonality, womanPersonality;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        m = scanner.nextInt();
        manPersonality = new int[n];
        womanPersonality = new int[m];

        for(int idx = 0; idx < n; idx++)
            manPersonality[idx] = scanner.nextInt();
        for(int idx = 0; idx < m; idx++)
            womanPersonality[idx] = scanner.nextInt();
    }

    static void solution() {
        Arrays.sort(manPersonality);
        Arrays.sort(womanPersonality);

        int min = Math.min(n, m), max = Math.max(n, m);
        int[] minArr = min == n ? manPersonality : womanPersonality;
        int[] maxArr = max == m ? womanPersonality : manPersonality;
        List<Integer> nearestDiffs = new ArrayList<>();

        int maxIdx = 0, index = 0;
        for(int minIdx = 0; minIdx < min; minIdx++) {
            int minDiff = Integer.MAX_VALUE;
            for(maxIdx = index; maxIdx < max && (min - minIdx) < (max - maxIdx); maxIdx++) {
                int diff = Math.abs(minArr[minIdx] - maxArr[maxIdx]);
                if(minDiff > diff) minDiff = diff;
                else {
                    index = maxIdx;
                    break;
                }
            }
            nearestDiffs.add(Math.abs(minArr[minIdx] - maxArr[Math.max(0, index - 1)]));
        }

        if(nearestDiffs.size() < min) {
            int remain = n - nearestDiffs.size();
            for(int idx = 0; idx < remain; idx++) {
                nearestDiffs.add(Math.abs(minArr[(min - 1 - remain) + idx] - maxArr[(max - 1 - remain) + idx]));
            }
        }

        System.out.println(nearestDiffs.stream().mapToInt(Integer::valueOf).sum());
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
