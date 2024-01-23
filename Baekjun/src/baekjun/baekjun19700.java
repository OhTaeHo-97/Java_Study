package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class baekjun19700 {
    static int studentCount;
    static Map<Integer, Integer> studentInfos;
    static TreeSet<Team> teams;

    static void input() {
        Reader scanner = new Reader();

        studentCount = scanner.nextInt();
        studentInfos = new TreeMap<>(Collections.reverseOrder());
        teams = new TreeSet<>();

        for (int idx = 0; idx < studentCount; idx++) {
            int height = scanner.nextInt();
            int maxLonger = scanner.nextInt();
            studentInfos.put(height, maxLonger);
        }
    }

    static void solution() {
        for (int height : studentInfos.keySet()) {
            if (teams.isEmpty()) {
                teams.add(new Team(height, 1));
                continue;
            }

            Team nearestLowerTeam = teams.lower(new Team(height, studentInfos.get(height)));
            if (nearestLowerTeam == null) {
                teams.add(new Team(height, 1));
                continue;
            }

            nearestLowerTeam.teamMemberCount++;
        }

        System.out.println(teams.size());
    }

    static class Team implements Comparable<Team> {
        int maxHeight;
        int teamMemberCount;

        public Team(int maxHeight, int teamMemberCount) {
            this.maxHeight = maxHeight;
            this.teamMemberCount = teamMemberCount;
        }

        @Override
        public int compareTo(Team o) {
            if (teamMemberCount != o.teamMemberCount) {
                return teamMemberCount - o.teamMemberCount;
            }
            return maxHeight - o.maxHeight;
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
