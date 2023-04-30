package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun2613 {
	static int N, M, left, right;
    static int[] marbles;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        M = scanner.nextInt();
        left = right = 0;
        marbles = new int[N];

        for(int idx = 0; idx < N; idx++) {
            marbles[idx] = scanner.nextInt();

            left = Math.max(left, marbles[idx]);
            right += marbles[idx];
        }
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();

        int maxGroupSum = binarySearch();
        sb.append(maxGroupSum).append('\n');

        getEachGroupMarblesNum(maxGroupSum, sb);
        System.out.println(sb);
    }

    static void getEachGroupMarblesNum(int maxGroupSum, StringBuilder sb) {
        int count = 0, sum = 0;
        for(int idx = 0; idx < N; idx++) {
            sum += marbles[idx];
            if(sum > maxGroupSum) {
                M--;
                sum = marbles[idx];
                sb.append(count).append(" ");
                count = 1;
            } else count++;

            // 남은 구슬 수와 남은 그룹의 수와 같다면 각 구슬이 하나의 그룹을 이루니 여기서 break
            if(M == N - idx) break;
        }

        while(M-- > 0) {
            sb.append(count).append(" ");
            count = 1;
        }
    }

    static int binarySearch() {
        while(left <= right) {
            int mid = (left + right) / 2;
            int groupNum = getGroupNum(mid);

            if(groupNum > M) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }

    static int getGroupNum(int maxGroupSum) {
        int sum = 0, count = 1;
        for(int idx = 0; idx < N; idx++) {
            sum += marbles[idx];
            if(sum > maxGroupSum) {
                count++;
                sum = marbles[idx];
            }
        }

        return count;
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
