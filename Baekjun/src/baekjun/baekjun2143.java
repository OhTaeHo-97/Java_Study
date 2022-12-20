package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun2143 {
//  static int T, n, m;
//  static int[] A, B;
//  static void input() {
//      Reader scanner = new Reader();
//      T = scanner.nextInt();
//      n = scanner.nextInt();
//      A = new int[n];
//      for(int idx = 0; idx < n; idx++) A[idx] = scanner.nextInt();
//      m = scanner.nextInt();
//      B = new int[m];
//      for(int idx = 0; idx < m; idx++) B[idx] = scanner.nextInt();
//  }
//
//  static void solution() {
//      int[] prefixSumA = prefixSum(A, n), prefixSumB = prefixSum(B, m);
//      HashMap<Integer, Integer> sumA = getNumOfAllSum(prefixSumA), sumB = getNumOfAllSum(prefixSumB);
//      long answer = 0L;
//      for(int key : sumA.keySet()) {
//          int dif = T - key;
//          if(sumB.containsKey(dif)) answer += (long)sumA.get(key) * (long)sumB.get(dif);
//      }
//      System.out.println(answer);
//  }
//
//  private static HashMap<Integer, Integer> getNumOfAllSum(int[] prefixSum) {
//      HashMap<Integer, Integer> sum;
//      sum = new HashMap<>();
//      for(int end = 1; end < prefixSum.length; end++) {
//          for(int start = 0; start < end; start++) {
//              int value = prefixSum[end] - prefixSum[start];
//              sum.put(value, sum.getOrDefault(value, 0) + 1);
//          }
//      }
//      return sum;
//  }
//
//  static int[] prefixSum(int[] org, int size) {
//      int[] arr = new int[size + 1];
//      for(int idx = 1; idx <= size; idx++) {
//          arr[idx] = org[idx - 1];
//          arr[idx] += arr[idx - 1];
//      }
//      return arr;
//  }

  // 이진 탐색 이용
  static int T, n, m;
  static int[] A, B;
  static void input() {
      Reader scanner = new Reader();
      T = scanner.nextInt();
      n = scanner.nextInt();
      A = new int[n];
      for(int idx = 0; idx < n; idx++) A[idx] = scanner.nextInt();
      m = scanner.nextInt();
      B = new int[m];
      for(int idx = 0; idx < m; idx++) B[idx] = scanner.nextInt();
  }

  static void solution() {
      int[] prefixSumA = prefixSum(A, n), prefixSumB = prefixSum(B, m);
      HashMap<Integer, Integer> sumA = getNumOfAllSum(prefixSumA), sumB = getNumOfAllSum(prefixSumB);
      List<Map.Entry<Integer, Integer>> listA = getSortedMap(sumA), listB = getSortedMap(sumB);
      long answer = binarySearch(listA, listB);
      System.out.println(answer);
  }

  static long binarySearch(List<Map.Entry<Integer, Integer>> listA, List<Map.Entry<Integer, Integer>> listB) {
      long answer = 0L;
      int pointerA = 0, pointerB = listB.size() - 1;
      while(pointerA < listA.size() && pointerB >= 0) {
          int sum = listA.get(pointerA).getKey() + listB.get(pointerB).getKey();
          if(sum == T) {
              long numA = (long)listA.get(pointerA).getValue();
              pointerA++;
              long numB = (long)listB.get(pointerB).getValue();
              pointerB--;
              answer += numA * numB;
          } else if(sum > T) {
              pointerB--;
          } else {
              pointerA++;
          }
      }
      return answer;
  }

  static List<Map.Entry<Integer, Integer>> getSortedMap(HashMap<Integer, Integer> sum) {
      List<Map.Entry<Integer, Integer>> list = new ArrayList<>(sum.entrySet());
      Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
          @Override
          public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
             return o1.getKey() - o2.getKey();
          }
      });
      return list;
  }

  static HashMap<Integer, Integer> getNumOfAllSum(int[] prefixSum) {
      HashMap<Integer, Integer> sum;
      sum = new HashMap<>();
      for(int end = 1; end < prefixSum.length; end++) {
          for(int start = 0; start < end; start++) {
              int value = prefixSum[end] - prefixSum[start];
              sum.put(value, sum.getOrDefault(value, 0) + 1);
          }
      }
      return sum;
  }

  static int[] prefixSum(int[] org, int size) {
      int[] arr = new int[size + 1];
      for(int idx = 1; idx <= size; idx++) {
          arr[idx] = org[idx - 1];
          arr[idx] += arr[idx - 1];
      }
      return arr;
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
