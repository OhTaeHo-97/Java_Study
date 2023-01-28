package baekjun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class baekjun4195 {
	static StringBuilder sb = new StringBuilder();
    static Reader scanner = new Reader();
    static int F;
    static HashMap<String, String> parents;
    static HashMap<String, HashSet<String>> children;
    static void input() {
        F = scanner.nextInt();
        parents = new HashMap<>();
        children = new HashMap<>();
        for(int relation = 0; relation < F; relation++) {
            String person1 = scanner.next(), person2 = scanner.next();
            if(person1.equals(person2)) {
                if(!parents.containsKey(person1))
                    parents.put(person1, person1);

                if(!children.containsKey(person1))
                    children.put(person1, new HashSet<>(Arrays.asList(person1)));
            } else {
                addElementInParents(person1);
                addElementInParents(person2);

                union(person1, person2);
            }

            String parent = findParent(person1);
            parent = parents.get(person1);

            sb.append(children.get(parent).size()).append('\n');
        }
    }

    static void addElementInParents(String person) {
        if(!parents.containsKey(person)) parents.put(person, person);
    }

    static String findParent(String person) {
        if(person.equals(parents.get(person))) return person;
        return parents.put(person, findParent(parents.get(person)));
    }

    static void union(String person1, String person2) {
        String parent1 = findParent(person1), parent2 = findParent(person2);
        parent1 = parents.get(person1);
        parent2 = parents.get(person2);

        if(!parent1.equals(parent2)) {
            if(parent1.compareTo(parent2) < 0)
                editChildren(parent2, parent1);
            else
                editChildren(parent1, parent2);
        }
    }

    private static void editChildren(String child, String parent) {
        parents.put(child, parent);

        if(children.getOrDefault(parent, null) == null)
            children.put(parent, new HashSet<>(Arrays.asList(parent)));

        if(child == parent) return;

        children.get(parent).add(child);

        if(children.getOrDefault(child, null) != null) {
            children.get(parent).addAll(children.get(child));
            children.put(child, null);
        }
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
