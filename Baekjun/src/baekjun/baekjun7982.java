package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class baekjun7982 {
    static int seriesCount;
    static int[] series;

    static void input() {
        Reader scanner = new Reader();

        seriesCount = scanner.nextInt();
        series = new int[seriesCount + 1];

        for (int idx = 1; idx <= seriesCount; idx++) {
            series[idx] = scanner.nextInt();
        }
    }

    static void solution() {
        List<List<Integer>> allSets = findAllSets();
        StringBuilder setsInfo = new StringBuilder();
        setsInfo.append(allSets.stream().filter(set -> !set.isEmpty()).count()).append('\n');
        allSets.stream().filter(set -> !set.isEmpty()).forEach(set -> setsInfo.append(createEachSet(set)).append('\n'));
        System.out.print(setsInfo);
    }

    static String createEachSet(List<Integer> set) {
        StringBuilder setInfo = new StringBuilder();
        setInfo.append(set.size()).append(' ');
        set.forEach(elem -> setInfo.append(elem).append(' '));
        return setInfo.toString();
    }

    static List<List<Integer>> findAllSets() {
        List<List<Integer>> allSets = new ArrayList<>();
        int seriesNum = 1;
        int max = Integer.MIN_VALUE;

        allSets.add(new ArrayList<>());

        for (int idx = 1; idx <= seriesCount; idx++) {
            max = Math.max(max, series[idx]);
            allSets.get(seriesNum - 1).add(idx);
            if (max <= idx) {
                seriesNum++;
                allSets.add(new ArrayList<>());
            }
        }

        return allSets;
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
            while (st == null || !st.hasMoreElements()) {
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
