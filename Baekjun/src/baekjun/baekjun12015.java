package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun12015 {
	// 앞에서부터 보면서 점점 커지는 수들을 LIS라는 배열에 넣음
    // 이 떄, 만약 지금까지 LIS 배열에 넣은 수 중에서 가장 큰 수보다 작아지거나 같은 수가 나온다면
    // 그 수는 그 수에 가장 가까운 수를 찾아 그 자리에 대치된다
    // 대치시키는 이유는 만약 큰 수가 LIS에 남아있는데 이후에 더 작은 수들이 남아있다면
    // LIS에 있는 큰 수를 이후에 들어오는 수들로 대치해주지 않는 이상 그 이후에 수들은 아무것도 못 들어오게 된다
    // 그래서 대치를 시킨다
    // 이 방식은 LIS의 원소들을 구하는 데에는 맞지 않다
    //  -> 왜냐하면 뒤에 나오는 작은 수를 이용하여 대치를 시키기 때문에 연속되는 수들이 아닐 수 있다
    //  -> 그러나 이 문제는 길이를 구하는 것이므로 상관없다
    // 만약 LIS에 있는 가장 큰 수보다 더 큰 수가 들어온다면 LIS의 길이를 늘려주고, 그렇지 않다면 대치를 시켜줘 이후에 들어올 수 있는 값들을 확인한다
    // 이 방식으로 LIS의 길이를 구해나간다.
    static int N;
    static int[] A;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        A = new int[N];
        for(int idx = 0; idx < N; idx++) A[idx] = scanner.nextInt();
    }

    static void solution() {
        int[] LIS = new int[N];
        LIS[0] = A[0];
        int len = 1;
        for(int idx = 1; idx < N; idx++) {
            int key = A[idx];
            if(LIS[len - 1] < key) {
                len++;
                LIS[len - 1] = key;
            } else {
                int min = 0, max = len;
                while(min < max) {
                    int mid = (min + max) / 2;
                    if(LIS[mid] < key) {
                        min = mid + 1;
                    } else {
                        max = mid;
                    }
                }
                LIS[min] = key;
            }
        }
        System.out.println(len);
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
