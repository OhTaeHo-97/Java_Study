package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjun14391 {
	// dfs 이용
//  static int N, M, answer;
//  static int[][] map;
//  static boolean[][] isRow;
//
//  static void input() {
//      baekjoon14391_exercise.Reader scanner = new baekjoon14391_exercise.Reader();
//
//      N = scanner.nextInt();
//      M = scanner.nextInt();
//      answer = 0;
//      map = new int[N][M];
//      isRow = new boolean[N][M];
//
//      for(int row = 0; row < N; row++) {
//          String info = scanner.nextLine();
//          for(int col = 0; col < M; col++)
//              map[row][col] = info.charAt(col) - '0';
//      }
//  }
//
//  static void solution() {
//      dfs(0, 0);
//      System.out.println(answer);
//  }
//
//  static void dfs(int row, int col) {
//      if(row >= N) {
//          getSum();
//          return;
//      }
//
//      if(col >= M) {
//          dfs(row + 1, 0);
//          return;
//      }
//
//      isRow[row][col] = true;
//      dfs(row, col + 1);
//      isRow[row][col] = false;
//      dfs(row, col + 1);
//  }
//
//  static void getSum() {
//      int sum = sumRow();
//      sum += sumCol();
//
//      answer = Math.max(answer, sum);
//  }
//
//  static int sumRow() {
//      int sum = 0;
//      for(int row = 0; row < N; row++) {
//          int temp = 0;
//
//          for(int col = 0; col < M; col++) {
//              if(isRow[row][col]) {
//                  temp *= 10;
//                  temp += map[row][col];
//              } else {
//                  sum += temp;
//                  temp = 0;
//              }
//          }
//
//          sum += temp;
//      }
//
//      return sum;
//  }
//
//  static int sumCol() {
//      int sum = 0;
//      for(int col = 0; col < M; col++) {
//          int temp = 0;
//
//          for(int row = 0; row < N; row++) {
//              if(!isRow[row][col]) {
//                  temp *= 10;
//                  temp += map[row][col];
//              } else {
//                  sum += temp;
//                  temp = 0;
//              }
//          }
//
//          sum += temp;
//      }
//
//      return sum;
//  }

  // 비트마스킹 이용
  static int N, M;
  static int[][] map;

  static void input() {
      Reader scanner = new Reader();

      N = scanner.nextInt();
      M = scanner.nextInt();
      map = new int[N][M];

      for(int row = 0; row < N; row++) {
          String info = scanner.nextLine();
          for(int col = 0; col < M; col++)
              map[row][col] = info.charAt(col) - '0';
      }
  }

  static void solution() {
      int answer = 0;

      for(int bit = 0; bit < (1 << (N * M)); bit++) {
          int sum = sumRow(bit);
          sum += sumCol(bit);

          answer = Math.max(answer, sum);
      }

      System.out.println(answer);
  }

  static int sumRow(int bit) {
      int sum = 0;

      for(int row = 0; row < N; row++) {
          int temp = 0;

          for(int col = 0; col < M; col++) {
              if((bit & (1 << (row * M + col))) == 0) {
                  temp *= 10;
                  temp += map[row][col];
              } else {
                  sum += temp;
                  temp = 0;
              }
          }

          sum += temp;
      }

      return sum;
  }

  static int sumCol(int bit) {
      int sum = 0;

      for(int col = 0; col < M; col++) {
          int temp = 0;

          for(int row = 0; row < N; row++) {
              if((bit & (1 << (row * M + col))) != 0) {
                  temp *= 10;
                  temp += map[row][col];
              } else {
                  sum += temp;
                  temp = 0;
              }
          }

          sum += temp;
      }

      return sum;
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
