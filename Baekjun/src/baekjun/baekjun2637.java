package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjun2637 {
	// N (3 <= N <= 100) -> 1부터 N - 1까지는 기본 부품이나 중간 부품의 번호를 나타냄. N -> 완제품의 번호
    // M (3 <= M <= 100) -> 부품을 완성하는데 필요한 부품들 간의 관계의 개수
    // M개의 줄 -> 부품을 완성하는데 필요한 부품들 간의 관계
    //      - X, Y, K로 주어짐
    //      - 중간 부품이나 완제품 X를 만드는데 중간 부품 혹은 기본 부품 Y가 K개 필요하다
    // 두 중간 부품이 서로를 필요로 하는 경우는 없다

    static int N, M;
    static HashMap<Integer, HashMap<Integer, Integer>> map, necessaryComponent;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        map = new HashMap<>();
        necessaryComponent = new HashMap<>();

        for(int component = 1; component <= M; component++) {
            int X = scanner.nextInt(), Y = scanner.nextInt(), K = scanner.nextInt();
            if(!map.containsKey(X)) map.put(X, new HashMap<>());

            map.get(X).put(Y, K);
        }
    }

    static void solution() {
        for(int key : map.keySet()) {
            func(key);
            System.out.println("a");
        }

        for(int key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }

    static HashMap<Integer, Integer> func(int curComponent) {
        for(int component : map.get(curComponent).keySet()) {
            System.out.println("keySet = " + map.get(curComponent).keySet());
            if(map.containsKey(component)) {
                HashMap<Integer, Integer> baseComponents = func(component);
                int size = map.get(curComponent).get(component);
                for(int baseComponent : baseComponents.keySet())
                    map.get(curComponent).put(baseComponent, baseComponents.get(baseComponent) * size);
                map.get(curComponent).remove(component);
                System.out.println(curComponent + ": " + map.get(curComponent));
            }
        }

        return map.get(curComponent);

        // 다 돌아서 중간이 없다면 개수만큼 곱해서 해당 HashMap 반환
        
        // HashMap에 있는 것들 다 돌았으면 마지막에 자기 자신의 HashMap 반환
    }

//    static class Component {
//        int num;
//    }
    
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
