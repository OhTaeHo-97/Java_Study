package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun21276 {
    static StringBuilder sb = new StringBuilder();
    static int familyCnt;
    static int relationCnt;
    static Map<String, Integer> ancestorNum;
    static Map<String, List<String>> descendants;
    static Map<String, List<String>> answer;

    static void input() {
        Reader scanner = new Reader();

        familyCnt = scanner.nextInt();
        answer = new HashMap<>();
        descendants = new HashMap<>();
        ancestorNum = new HashMap<>();

        for (int idx = 0; idx < familyCnt; idx++) {
            String family = scanner.next();
            answer.put(family, new ArrayList<>());
            ancestorNum.put(family, 0);
            descendants.put(family, new ArrayList<>());
        }

        relationCnt = scanner.nextInt();
        for (int relation = 0; relation < relationCnt; relation++) {
            String startFamily = scanner.next();
            String endFamily = scanner.next();
            descendants.get(endFamily).add(startFamily);
            ancestorNum.put(startFamily, ancestorNum.get(startFamily) + 1);
        }
    }

    static void solution() {
        topologicalSort();
        List<Map.Entry<String, List<String>>> entryList = sort();
        print(entryList);
    }

    static void print(List<Map.Entry<String, List<String>>> entryList) {
        for(Map.Entry<String, List<String>> entry : entryList) {
            sb.append(entry.getKey()).append(' ');
            sb.append(entry.getValue().size()).append(' ');
            Collections.sort(entry.getValue());
            for(String descendant : entry.getValue()) {
                sb.append(descendant).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static List<Map.Entry<String, List<String>>> sort() {
        List<Map.Entry<String, List<String>>> entryList = new ArrayList<>(answer.entrySet());
        Collections.sort(entryList, new Comparator<Entry<String, List<String>>>() {
            @Override
            public int compare(Entry<String, List<String>> o1, Entry<String, List<String>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        return entryList;
    }

    static void topologicalSort() {
        Queue<String> queue = new LinkedList<>();
        List<String> fathers = new ArrayList<>();
        for(String family : ancestorNum.keySet()) {
            if(ancestorNum.get(family) == 0) {
                fathers.add(family);
                queue.offer(family);
            }
        }

        sb.append(fathers.size()).append('\n');
        Collections.sort(fathers);
        for(String father : fathers) {
            sb.append(father).append(' ');
        }
        sb.append('\n');

        while(!queue.isEmpty()) {
            String curFamily = queue.poll();

            for(String descendant : descendants.get(curFamily)) {
                ancestorNum.put(descendant, ancestorNum.get(descendant) - 1);
                if(ancestorNum.get(descendant) == 0) {
                    answer.get(curFamily).add(descendant);
                    queue.offer(descendant);
                }
            }
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
