package src.baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun1838 {
    static int N;
    static PriorityQueue<Number> numbers;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        numbers = new PriorityQueue<>();

        for(int idx = 0; idx < N; idx++)
            numbers.offer(new Number(scanner.nextInt(), idx));
    }

    static void solution() {
        // 버블 정렬을 진행해보면 매 회전마다 정렬된 수를 제외한 수 중 가장 큰 수가 가장 끝으로 가며 자신의 자리를 찾아간다
        // 그리고 그보다 작은 수들은 매 회전마다 앞으로 한 칸씩 당겨진다
        // 앞으로 당겨지는 수들을 바라보면 매 회전마다 오직 한 칸씩만 앞으로 당겨지기 때문에
        // 앞으로 당겨지는 수들 중 자신의 자리를 찾아가기 위해 가장 많이 앞으로 당겨져야 하는 수의 이동 횟수가 버블 정렬의 회전수가 된다
        int index = 0;
        int answer = 0;

        // PriorityQueue에서는 원소의 수에 따라 오름차순으로 정령된 Number들이 나올 것이므로
        // PriorirtQueue에서 Number를 하나씩 뽑으면서 (정렬 전 인덱스 - 정렬 후 인덱스)를 구하고
        // 그렇게 계산한 수 중 가장 큰 수를 구한다
        while(!numbers.isEmpty()) {
            Number number = numbers.poll();

            answer = Math.max(answer, number.idx - index);
            index++;
        }

        System.out.println(answer);
    }

    // 배열 A의 각 원소들을 나타내는 클래스
    // num : 원소의 값, idx : 해당 원소의 인덱스값
    static class Number implements Comparable<Number> {
        int num;
        int idx;

        public Number(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Number n) {
            return num - n.num;
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
