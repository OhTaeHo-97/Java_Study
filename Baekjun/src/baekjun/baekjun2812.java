package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class baekjun2812 {
	static StringBuilder sb = new StringBuilder();
    static int N, K;
    static String numStr;
    static void input() {
        Reader scanner = new Reader();
        N = scanner.nextInt();
        K = scanner.nextInt();
        numStr = scanner.nextLine();
    }

    static void solution() {
        Stack<Integer> num = new Stack<>();
        int removeNum = removeSmallNum(num);
        if(removeNum < K) removeLastNums(num, removeNum);
        int size = num.size();
        for(int idx = 0; idx < size; idx++) sb.insert(0, num.pop());
        System.out.println(sb);
    }

    static void removeLastNums(Stack<Integer> num, int removeNum) {
        for(int idx = removeNum; idx < K; idx++)
            num.pop();
    }

    static int removeSmallNum(Stack<Integer> num) {
        int removeNum = 0;
        for(int idx = 0; idx < numStr.length(); idx++) {
            if(num.size() == 0 || removeNum >= K)
                num.push(numStr.charAt(idx) - '0');
            else {
                int cur = numStr.charAt(idx) - '0';
                while(num.size() > 0) {
                    int last = num.peek();
                    if(last >= cur || removeNum >= K) break;
                    if(last < cur) {
                        num.pop();
                        removeNum++;
                    }
                }
                num.push(cur);
            }
        }
        return removeNum;
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
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch(IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
