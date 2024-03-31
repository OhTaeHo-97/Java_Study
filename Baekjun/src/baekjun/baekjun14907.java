package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun14907 {
    static final int ALPHABET_SIZE = 26;

    static int answer;
    static int[] elapseTimes;
    static int[] workTimes;
    static int[] precedeWorkCount;
    static Set<Character> works;
    static Map<Character, List<Character>> followingWorks;

    static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str;

        try {
            works = new HashSet<>();
            elapseTimes = new int[ALPHABET_SIZE];
            workTimes = new int[ALPHABET_SIZE];
            precedeWorkCount = new int[ALPHABET_SIZE];
            followingWorks = new HashMap<>();

            while ((str = br.readLine()) != null) {
                if (str.isEmpty()) {
                    break;
                }
                st = new StringTokenizer(str);

                char workAlphabet = st.nextToken().charAt(0);
                int workTime = Integer.parseInt(st.nextToken());
                String precedeWorks = "";
                if (st.hasMoreElements()) {
                    precedeWorks = st.nextToken();
                }

                workTimes[workAlphabet - 'A'] = workTime;
                works.add(workAlphabet);

                for (int idx = 0; idx < precedeWorks.length(); idx++) {
                    followingWorks.computeIfAbsent(workAlphabet, key -> new ArrayList<>())
                            .add(precedeWorks.charAt(idx));
                    precedeWorkCount[precedeWorks.charAt(idx) - 'A']++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        calculateTotalElapseTime();
        System.out.println(answer);
    }

    static void calculateTotalElapseTime() {
        List<List<Character>> sortedWorkOrder = new ArrayList<>();
        sortedWorkOrder.add(new ArrayList<>());

        Queue<Character> queue = new LinkedList<>();
        for (char work : works) {
            if (precedeWorkCount[work - 'A'] == 0) {
                queue.offer(work);
                elapseTimes[work - 'A'] = workTimes[work - 'A'];
                answer = Math.max(answer, elapseTimes[work - 'A']);
            }
        }

        while (!queue.isEmpty()) {
            char cur = queue.poll();

            if (followingWorks.containsKey(cur)) {
                for (char nextWork : followingWorks.get(cur)) {
                    precedeWorkCount[nextWork - 'A']--;
                    if (precedeWorkCount[nextWork - 'A'] == 0) {
                        queue.offer(nextWork);
                    }

                    elapseTimes[nextWork - 'A'] = Math.max(elapseTimes[nextWork - 'A'],
                            elapseTimes[cur - 'A'] + workTimes[nextWork - 'A']);
                    answer = Math.max(answer, elapseTimes[nextWork - 'A']);
                }
            }
        }
    }

    public static void main(String[] args) {
        input();
        solution();
    }
}
