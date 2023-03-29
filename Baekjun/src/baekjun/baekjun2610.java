package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun2610 {
	static int N;
    static HashMap<Integer, ArrayList<Integer>> relationList, map;
    static int[][] relationArr;
    static ArrayList<ArrayList<Integer>> committees;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        relationArr = new int[N + 1][N + 1];
        relationList = new HashMap<>();
        for(int attendee = 1; attendee <= N; attendee++) {
            relationList.put(attendee, new ArrayList<>());
            Arrays.fill(relationArr[attendee], Integer.MAX_VALUE);
            relationArr[attendee][attendee] = 0;
        }

        int relationNum = scanner.nextInt();
        for(int idx = 0; idx < relationNum; idx++) {
            int attendee1 = scanner.nextInt(), attendee2 = scanner.nextInt();

            relationList.get(attendee1).add(attendee2);
            relationList.get(attendee2).add(attendee1);

            relationArr[attendee1][attendee2] = 1;
            relationArr[attendee2][attendee1] = 1;
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();

        makeCommittee();
        sb.append(committees.size()).append('\n');

        ArrayList<Integer> representatives = new ArrayList<>();

        for(ArrayList<Integer> committee : committees) {
            if(committee.size() == 1) {
                representatives.add(committee.get(0));
                continue;
            }

            floydWarshall(committee);
            representatives.add(findRepresentative(committee));
        }

        Collections.sort(representatives);
        for(int representative : representatives) sb.append(representative).append('\n');

        System.out.print(sb);
    }

    static int findRepresentative(ArrayList<Integer> committee) {
        int min = Integer.MAX_VALUE, selectedRepresentative = 0;

        for(int representative : committee) {
            int max = Integer.MIN_VALUE;
            for(int attendee : committee) {
                if(representative == attendee) continue;
                max = Math.max(max, relationArr[representative][attendee]);
            }

            if(max < min) {
                min = max;
                selectedRepresentative = representative;
            }
        }

        return selectedRepresentative;
    }

    static void floydWarshall(ArrayList<Integer> committee) {
        for(int middle = 0; middle < committee.size(); middle++) {
            int middleAttendee = committee.get(middle);
            for(int start = 0; start < committee.size(); start++) {
                int startAttendee = committee.get(start);
                for(int end = 0; end < committee.size(); end++) {
                    if(start == end || start == middle || end == middle) continue;

                    int endAttendee = committee.get(end);

                    if(relationArr[startAttendee][middleAttendee] == Integer.MAX_VALUE
                            || relationArr[middleAttendee][endAttendee] == Integer.MAX_VALUE) continue;

                    if(relationArr[startAttendee][endAttendee] > relationArr[startAttendee][middleAttendee] + relationArr[middleAttendee][endAttendee])
                        relationArr[startAttendee][endAttendee] = relationArr[startAttendee][middleAttendee] + relationArr[middleAttendee][endAttendee];
                }
            }
        }
    }

    static void makeCommittee() {
        boolean[] visited = new boolean[N + 1];
        committees = new ArrayList<>();

        for(int attendee = 1; attendee <= N; attendee++) {
            ArrayList<Integer> list = new ArrayList<>();
            if(!visited[attendee]) {
                visited[attendee] = true;
                list.add(attendee);
                dfs(attendee, visited, list);
                committees.add(list);
            }
        }
    }

    static void dfs(int attendee, boolean[] visited, ArrayList<Integer> list) {
        for(int next : relationList.get(attendee)) {
            if(!visited[next]) {
                visited[next] = true;
                list.add(next);
                dfs(next, visited, list);
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
