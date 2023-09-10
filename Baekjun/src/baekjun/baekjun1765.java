package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun1765 {
    static int n;
    static int[] parents;
    static int[] opposite;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        parents = new int[n + 1];
        opposite = new int[n + 1];
        for(int student = 1; student <= n; student++) {
            parents[student] = student;
        }

        int m = scanner.nextInt();
        for(int count = 0; count < m; count++) {
            String type = scanner.next();
            int student1 = scanner.nextInt();
            int student2 = scanner.nextInt();

            if(type.charAt(0) == 'E') {
                if(opposite[student1] == 0 && opposite[student2] == 0) {
                    opposite[student1] = student2;
                    opposite[student2] = student1;
                } else if(opposite[student1] == 0) {
                    union(opposite[student2], student1);
                } else {
                    union(opposite[student1], student2);
                }
            } else if(type.charAt(0) == 'F') {
                union(student1, student2);
            }
        }
    }

    static int findParent(int student) {
        if(parents[student] == student) return student;
        return parents[student] = findParent(parents[student]);
    }

    static void union(int student1, int student2) {
        int parent1 = findParent(student1);
        int parent2 = findParent(student2);

        if(parent1 != parent2) {
            if(parent1 < parent2) parents[parent2] = parent1;
            else parents[parent1] = parent2;
        }
    }

    static void solution() {
        int answer = 0;
        Set<Integer> visited = new HashSet<>();
        for(int student = 1; student <= n; student++) {
            int parent = findParent(student);
            if(visited.add(parent)) {
                answer++;
            }
        }

        System.out.println(answer);
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
