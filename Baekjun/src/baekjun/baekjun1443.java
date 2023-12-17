package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun1443 {
    static int maxLength;
    static int multiplyCount;
    static int answer;
    static Set<Number> visited;

    static void input() {
        Reader scanner = new Reader();

        maxLength = scanner.nextInt();
        multiplyCount = scanner.nextInt();
        visited = new HashSet<>();
        answer = -1;
    }

    static void solution() {
        multiply(1, 0);
        System.out.println(answer);
    }

    static void multiply(int number, int count) {
        if (count >= multiplyCount) {
            answer = Math.max(answer, number);
            return;
        }

        for (int num = 9; num >= 2; num--) {
            int multipliedNumber = number * num;
            if (String.valueOf(multipliedNumber).length() > maxLength) {
                continue;
            }
            Number n = new Number(multipliedNumber, count + 1);
            if (visited.contains(n)) {
                continue;
            }
            visited.add(n);
            multiply(multipliedNumber, count + 1);
        }
    }

    static class Number {
        int number;
        int count;

        public Number(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Number n = (Number) o;
            return number == n.number && count == n.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, count);
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
