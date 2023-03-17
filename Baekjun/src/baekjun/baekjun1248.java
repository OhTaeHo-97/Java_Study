package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1248 {
	static int n;
    static char[] signs;
    static char[][] signMatrix;

    static void input() {
        Reader scanner = new Reader();

        n = scanner.nextInt();
        signs = new char[n + 1];
        signMatrix = new char[n + 1][n + 1];

        String info = scanner.nextLine();
        int index = 0;
        for(int row = 1; row <= n; row++) {
            for(int col = row; col <= n; col++) {
                signMatrix[row][col] = info.charAt(index);
                index++;

                if(row == col) signs[col] = signMatrix[row][col];
            }
        }
    }

    static void solution() {

    }

    static void recFunc(int index, int sum, ArrayList<Integer> list) {
        if(index > 10) {

        }

        if(index > 1) {
            for(int idx = 1; idx < index; idx++) {

            }
        }

        for(int idx = index; idx <= n; idx++) {
            if(signs[idx] == '+') {
                for(int num = 1; num <= 10; num++) {
                    list.add(num);
                    recFunc(index + 1, sum + num, list);
                    list.remove(list.size() - 1);
                }
            } else if(signs[idx] == '-') {
                for(int num = -10; num <= -1; num++) {
                    list.add(num);
                    recFunc(index + 1, sum + num, list);
                    list.remove(list.size() - 1);
                }
            } else if(signs[idx] == '0') {
                list.add(0);
                recFunc(index + 1, sum, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {

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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
