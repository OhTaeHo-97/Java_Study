package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun1033 {
	static int N;
    static Map<Integer, ArrayList<Integer>> ingredients;
    static int[] ratios;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        ingredients = new HashMap<>();
        ratios = new int[N];
        for(int ingredient = 0; ingredient < N; ingredient++) {
            ingredients.put(ingredient, new ArrayList<>());
            ratios[ingredient] = 1;
        }

        for(int idx = 0; idx < N - 1; idx++) {
            int ingredient1 = scanner.nextInt(), ingredient2 = scanner.nextInt();
            int ratio1 = scanner.nextInt(), ratio2 = scanner.nextInt();
            int gcd = getGCD(Math.max(ratio1, ratio2), Math.min(ratio1, ratio2));
            calculate(ingredient1, ingredient2, ratio1 / gcd, ratio2 / gcd);
        }
    }

    static void calculate(int base, int mixed, int ratio1, int ratio2) {
        boolean[] check = new boolean[N];

        int baseRatio = ratios[base], mixedRatio = ratios[mixed];
        update(base, mixedRatio * ratio1, check);
        update(mixed, baseRatio * ratio2, check);
        ingredients.get(base).add(mixed);
        ingredients.get(mixed).add(base);
    }

    static void update(int ingredient, int num, boolean[] check) {
        ratios[ingredient] *= num;
        check[ingredient] = true;

        for(int idx = 0; idx < ingredients.get(ingredient).size(); idx++) {
            if(check[ingredients.get(ingredient).get(idx)]) continue;
            update(ingredients.get(ingredient).get(idx), num, check);
        }
    }

    static void solution() {
        int gcd = getGCD(Math.max(ratios[0], ratios[1]), Math.min(ratios[0], ratios[1]));
        while(gcd > 1) {
            for(int idx = 0; idx < N; idx++) gcd = getGCD(Math.max(gcd, ratios[idx]), Math.min(gcd, ratios[idx]));
            for(int idx = 0; idx < N; idx++) ratios[idx] /= gcd;
        }

        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < N; idx++) sb.append(ratios[idx]).append(' ');
        System.out.println(sb);
    }

    static int getGCD(int num1, int num2) {
        if(num2 == 0) return num1;
        return getGCD(num2, num1 % num2);
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
