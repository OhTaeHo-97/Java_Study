package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Swea5695 {
    private static Reader scanner;
    private static StringBuilder sb;

    private static int testNumber;
    private static int peopleCount;
    private static int cyclePersonCount;
    private static int[] bestFriend;
    private static int[] depth;
    private static int[] cycleNumbers;
//    private static int

    private static void input() {
        peopleCount = scanner.nextInt();
        bestFriend = new int[peopleCount + 1];
        depth = new int[peopleCount + 1];
        cycleNumbers = new int[peopleCount + 1];

        for(int person = 1; person <= peopleCount; person++) {
            bestFriend[person] = scanner.nextInt();
        }
    }

    private static void solution() {
        int answer = 0;
        Map<Integer, Set<Integer>> cycles = findCycles();
        for(int key : cycles.keySet()) {
            Set<Integer> cyclePeople = cycles.get(key);
            if(cyclePeople.size() == 2) {
                for(int person = 1; person <= peopleCount; person++) {
                    if(cyclePeople.contains(person)) continue;
                    if(cyclePeople.contains(bestFriend[person])) {
                        answer++;
                        break;
                    }
                }
            }
            answer += cyclePeople.size();
        }

        sb.append('#').append(testNumber).append(' ').append(answer).append('\n');
    }

    private static Map<Integer, Set<Integer>> findCycles() {
        int cycleNumber = 1;
        for(int person = 1; person <= peopleCount; person++) {
            Arrays.fill(depth, 0);
            if(depth[person] == 0) {
                cyclePersonCount = 0;
                findCycle(person, 1, cycleNumber);
                cycleNumber++;
            }
        }

        Map<Integer, Set<Integer>> cycles = new HashMap<>();
        for(int person = 1; person <= peopleCount; person++) {
            if(cycleNumbers[person] != 0) {
                if(!cycles.containsKey(cycleNumbers[person])) cycles.put(cycleNumbers[person], new HashSet<>());
                cycles.get(cycleNumbers[person]).add(person);
            }
        }

        return cycles;
    }

    private static int findCycle(int person, int d, int cycleNumber) {
        depth[person] = d;
        int nextPerson = bestFriend[person];

        // 종료 조건
        if(cycleNumbers[nextPerson] != 0) return 0;
        if(depth[nextPerson] != 0) {
            cyclePersonCount = d - depth[nextPerson];
            cycleNumbers[nextPerson] = cycleNumber;
            return nextPerson;
        }

        if(findCycle(nextPerson, d + 1, cycleNumber) > 0 && cyclePersonCount > 0) {
            cycleNumbers[nextPerson] = cycleNumber;
            cyclePersonCount--;
            return nextPerson;
        }

        return 0;
    }

    public static void main(String args[]) throws Exception
    {
        scanner = new Reader();
        sb = new StringBuilder();

        int testCount = scanner.nextInt();
        for(testNumber = 1; testNumber <= testCount; testNumber++) {
            input();
            solution();
        }

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
