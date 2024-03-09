package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun16402 {
    static int kingdomCount;
    static int warCount;
    static int[] parents;
    static String[] kingdomNames;
    static int[][] wars;
    static Map<String, Integer> kingdomIdx;

    static void input() {
        Reader scanner = new Reader();

        kingdomCount = scanner.nextInt();
        warCount = scanner.nextInt();
        parents = new int[kingdomCount];
        kingdomNames = new String[kingdomCount];
        kingdomIdx = new HashMap<>();
        for (int kingdom = 0; kingdom < kingdomCount; kingdom++) {
            parents[kingdom] = kingdom;
            String kingdomName = scanner.nextLine();
            kingdomNames[kingdom] = kingdomName;
            kingdomIdx.put(kingdomName, kingdom);
        }

        wars = new int[warCount][2];
        for (int idx = 0; idx < warCount; idx++) {
            String[] warInfo = scanner.nextLine().split(",");

            if (warInfo[2].equals("1")) {
                wars[idx][0] = kingdomIdx.get(warInfo[0]);
                wars[idx][1] = kingdomIdx.get(warInfo[1]);
            } else {
                wars[idx][0] = kingdomIdx.get(warInfo[1]);
                wars[idx][1] = kingdomIdx.get(warInfo[0]);
            }
        }
    }

    static void solution() {
        for (int idx = 0; idx < warCount; idx++) {
            if (findParent(wars[idx][0]) == wars[idx][1]) {
                parents[wars[idx][0]] = wars[idx][0];
                for (int kingdom = 0; kingdom < kingdomCount; kingdom++) {
                    if (findParent(kingdom) == wars[idx][1]) {
                        union(wars[idx][0], kingdom);
                    }
                }
            } else {
                union(wars[idx][0], wars[idx][1]);
            }
        }

        Set<Integer> suzerains = findSuzerains();
        printAnswer(suzerains);
    }

    static void printAnswer(Set<Integer> suzerains) {
        StringBuilder answer = new StringBuilder();
        answer.append(suzerains.size()).append('\n');
        suzerains.stream().map(kingdomIdx -> kingdomNames[kingdomIdx]).sorted()
                .forEach(kingdomName -> answer.append(kingdomName).append('\n'));
        System.out.print(answer);
    }

    static Set<Integer> findSuzerains() {
        Set<Integer> suzerains = new HashSet<>();
        for (int kingdom = 0; kingdom < kingdomCount; kingdom++) {
            suzerains.add(findParent(kingdom));
        }
        return suzerains;
    }

    static int findParent(int kingdom) {
        if (kingdom == parents[kingdom]) {
            return kingdom;
        }
        return parents[kingdom] = findParent(parents[kingdom]);
    }

    static void union(int winKingdom, int loseKingdom) {
        int winParent = findParent(winKingdom);
        int loseParent = findParent(loseKingdom);
        parents[loseParent] = winParent;
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
