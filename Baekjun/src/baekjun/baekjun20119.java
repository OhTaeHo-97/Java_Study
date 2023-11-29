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

public class baekjun20119 {
    static int potionCount;
    static int recipeCount;
    static int potionInPossessionCount;
    static int[] potionNumbers;
    static int[] requiredPotionCounts;
    static Set<Integer> possiblePotions;
    static List<Integer>[] potions;

    static void input() {
        Reader scanner = new Reader();

        potionCount = scanner.nextInt();
        recipeCount = scanner.nextInt();
        potionNumbers = new int[recipeCount];
        requiredPotionCounts = new int[recipeCount];
        possiblePotions = new HashSet<>();
        potions = new ArrayList[potionCount + 1];
        for (int potion = 1; potion <= potionCount; potion++) {
            potions[potion] = new ArrayList<>();
        }

        for (int idx = 0; idx < recipeCount; idx++) {
            int size = scanner.nextInt();
            requiredPotionCounts[idx] = size;
            for (int count = 0; count < size; count++) {
                potions[scanner.nextInt()].add(idx);
            }
            potionNumbers[idx] = scanner.nextInt();
        }

        potionInPossessionCount = scanner.nextInt();
        for (int count = 0; count < potionInPossessionCount; count++) {
            possiblePotions.add(scanner.nextInt());
        }
    }

    static void solution() {
        topologicalSorting();
        print();
    }

    static void print() {
        StringBuilder answer = new StringBuilder();
        answer.append(possiblePotions.size()).append('\n');
        possiblePotions.stream().sorted().forEach(potion -> answer.append(potion).append(' '));
        System.out.println(answer);
    }

    static void topologicalSorting() {
        Queue<Integer> queue = new LinkedList<>(possiblePotions);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : potions[cur]) {
                if (requiredPotionCounts[next] == 0) {
                    continue;
                }

                requiredPotionCounts[next]--;
                if (requiredPotionCounts[next] == 0 && !possiblePotions.contains(potionNumbers[next])) {
                    queue.offer(potionNumbers[next]);
                    possiblePotions.add(potionNumbers[next]);
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
