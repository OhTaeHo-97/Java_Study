package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class baekjun13144 {
	static int N;
    static int[] nums;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        nums = new int[N];

        for(int idx = 0; idx < N; idx++)
            nums[idx] = scanner.nextInt();
    }

    static void solution() {
        System.out.println(twoPointer());
    }

    static long twoPointer() {
        long answer = 0L;
        Set<Integer> visitedNums = new HashSet<>();

        for(int left = 0, right = 0; left < N; left++) {
            while(right < N && !visitedNums.contains(nums[right])) {
                visitedNums.add(nums[right]);
                right++;
            }

            answer += visitedNums.size();
            visitedNums.remove(nums[left]);
        }

        return answer;
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
