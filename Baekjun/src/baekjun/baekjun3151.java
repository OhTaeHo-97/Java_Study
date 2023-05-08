package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjun3151 {
	static int N;
    static int[] codingSkills;

    static void input() {
        Reader scanner = new Reader();

        N = scanner.nextInt();
        codingSkills = new int[N];

        for(int student = 0; student < N; student++)
            codingSkills[student] = scanner.nextInt();
    }

    static void solution() {
        Arrays.sort(codingSkills);

        long answer = 0L;
        for(int student = 0; student < N; student++)
            answer += twoPointer(student + 1, N - 1, codingSkills[student] * (-1));

        System.out.println(answer);
    }

    static long twoPointer(int left, int right, int target) {
        long count = 0;
        while(left < right) {
            int sum = codingSkills[left] + codingSkills[right];

            if(sum == target) {
                if(codingSkills[left] == codingSkills[right]) {
                    int sameNum = right - left + 1;
                    count += sameNum * (sameNum - 1) / 2;
                    break;
                }

                int sameLeftNum = 1, sameRightNum = 1;

                while(codingSkills[left] == codingSkills[left + 1]) {
                    left++;
                    sameLeftNum++;
                }

                while(codingSkills[right] == codingSkills[right - 1]) {
                    right--;
                    sameRightNum++;
                }

                count += sameLeftNum * sameRightNum;
            }

            if(sum <= target) left++;
            else right--;
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
