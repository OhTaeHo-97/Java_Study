package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class baekjun17140 {
	static int r, c, k;
	static int[][] arr;
	static HashMap<Integer, Integer>[] map;
	static void input() {
		Reader scanner = new Reader();
		r = scanner.nextInt() - 1;
		c = scanner.nextInt() - 1;
		k = scanner.nextInt();
		arr = new int[3][3];
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) arr[row][col] = scanner.nextInt();
		}
	}
	
	static void solution() {
		for(int time = 0; time <= 100; time++) {
			if(arr.length > r && arr[0].length > c) {
				if(arr[r][c] == k) {
					System.out.println(time);
					return;
				}
			}
			if(time == 100) break;
			if(arr.length >= arr[0].length) operationR();
			else operationC();
		}
		System.out.println(-1);
	}
	
	static void operationR() {
		int size = countNumOfRow();
		if(size * 2 > 100) size = 50;
		int[][] temp = new int[arr.length][size * 2];
		for(int len = 0; len < map.length; len++) {
			List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map[len].entrySet());
			entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
				public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
					if(m1.getValue() != m2.getValue()) return m1.getValue() - m2.getValue();
					return m1.getKey() - m2.getKey();
				}
			});
			int index = 0;
			for(Map.Entry<Integer, Integer> entry : entryList) {
				temp[len][index] = entry.getKey();
				temp[len][index + 1] = entry.getValue();
				index += 2;
			}
		}
		arr = temp;
	}
	
	static void operationC() {
		int size = countNumOfColumn();
		if(size * 2 > 100) size = 50;
		int[][] temp = new int[size * 2][arr[0].length];
		for(int len = 0; len < map.length; len++) {
			List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(map[len].entrySet());
			entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
				public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
					if(m1.getValue() != m2.getValue()) return m1.getValue() - m2.getValue();
					return m1.getKey() - m2.getKey();
				}
			});
			int index = 0;
			for(Map.Entry<Integer, Integer> entry : entryList) {
				temp[index][len] = entry.getKey();
				temp[index + 1][len] = entry.getValue();
				index += 2;
			}
		}
		arr = temp;
	}
	
	static int countNumOfRow() {
		map = new HashMap[arr.length];
		int maxSize = Integer.MIN_VALUE;
		for(int row = 0; row < arr.length; row++) {
			map[row] = new HashMap<>();
			for(int col = 0; col < arr[row].length; col++) {
				if(arr[row][col] == 0) continue;
				map[row].put(arr[row][col], map[row].getOrDefault(arr[row][col], 0) + 1);
			}
			maxSize = Math.max(maxSize, map[row].size());
		}
		return maxSize;
	}
	
	static int countNumOfColumn() {
		map = new HashMap[arr[0].length];
		int maxSize = Integer.MIN_VALUE;
		for(int col = 0; col < arr[0].length; col++) {
			map[col] = new HashMap<>();
			for(int row = 0; row < arr.length; row++) {
				if(arr[row][col] == 0) continue;
				map[col].put(arr[row][col], map[col].getOrDefault(arr[row][col], 0) + 1);
			}
			maxSize = Math.max(maxSize, map[col].size());
		}
		return maxSize;
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
				} catch(IOException e) {
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
