package baekjun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun4195 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int F;
    static HashMap<String, Integer> people;
    static int[] parents, children;
    static void input() {
        F = scanner.nextInt();
        people = new HashMap<>();
        parents = new int[F * 2];
        children = new int[F * 2];
        int idx = 0;
        for(int relation = 0; relation < F; relation++) {
            String person1 = scanner.next(), person2 = scanner.next();
            if(!people.containsKey(person1))
                idx = addPerson(idx, person1);
            if(!people.containsKey(person2))
                idx = addPerson(idx, person2);

            sb.append(union(people.get(person1), people.get(person2))).append('\n');
        }
    }

    static int addPerson(int idx, String person) {
        people.put(person, idx++);
        parents[people.get(person)] = people.get(person);
        children[people.get(person)]++;
        return idx;
    }

    static int findParent(int person) {
        if(person == parents[person]) return person;
        return parents[person] = findParent(parents[person]);
    }

    static int union(int person1, int person2) {
        int parent1 = findParent(person1), parent2 = findParent(person2);
        int result = children[parent1];

        if(parent1 != parent2) {
            if(parent1 < parent2) {
                parents[parent2] = parent1;
                children[parent1] += children[parent2];
                children[parent2] = 1;
                result = children[parent1];
            }
            else {
                parents[parent1] = parent2;
                children[parent2] += children[parent1];
                children[parent1] = 1;
                result = children[parent2];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int tc = scanner.nextInt();
        while(tc-- > 0) {
            input();
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
                } catch (Exception e) {
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
