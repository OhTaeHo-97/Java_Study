package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjun2136 {
    static int antNum;
    static int length;
    static int[] ants;
    static int leftAnt;
    static int rightAnt;
    static int leftNum;
    static Map<Integer, Integer> antInfo;

    static void input() {
        Reader scanner = new Reader();

        antNum = scanner.nextInt();
        length = scanner.nextInt();
        ants = new int[antNum + 1];
        leftAnt = rightAnt = leftNum = 0;
        antInfo = new HashMap<>();

        for(int idx = 1; idx <= antNum; idx++) {
            ants[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        for(int idx = 1; idx <= antNum; idx++) {
            if(ants[idx] > 0) {
                rightAnt = Math.max(rightAnt, length - ants[idx]);
            } else if(ants[idx] < 0) {
                leftNum++;
                leftAnt = Math.max(Math.abs(ants[idx]), leftAnt);
            }
            antInfo.put(idx, Math.abs(ants[idx]));
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(antInfo.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        StringBuilder sb = new StringBuilder();
        if(leftAnt < rightAnt) {
            sb.append(entryList.get(leftNum).getKey()).append(' ').append(rightAnt);
        } else {
            sb.append(entryList.get(leftNum - 1).getKey()).append(' ').append(leftAnt);
        }

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
