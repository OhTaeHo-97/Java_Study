package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

import baekjun.baekjun1715.Reader;

public class baekjun17144 {
	// 블로그 개시 안 함
//	static int R, C, T;
//	static int[][] map;
//	static int[][] airCleaner;
//	static void input() {
//		Reader scanner = new Reader();
//		R = scanner.nextInt();
//		C = scanner.nextInt();
//		T = scanner.nextInt();
//		map = new int[R][C];
//		airCleaner = new int[2][2];
//		int idx = 0;
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {				
//				map[i][j] = scanner.nextInt();
//				if(map[i][j] == -1) {
//					airCleaner[idx][0] = i;
//					airCleaner[idx][1] = j;
//					idx++;
//				}
//			}
//		}
//	}
//	
//	static void solution() {
//		int ans = 0;
//		for(int i = 0; i < T; i++) {
//			diffuse();
//			airCleaning();
//		}
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				if(map[i][j] != 0) {
//					ans += map[i][j];
//				}
//			}
//		}
//		System.out.println(ans);
//	}
//	
//	static void diffuse() {
//		int[][] accumulate = new int[R][C];
//		int[] dx = {-1, 0, 1, 0};
//		int[] dy = {0, -1, 0 ,1};
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				int cnt = 0;
//				if(map[i][j] != 0) {
//					for(int k = 0; k < 4; k++) {
//						int cx = i + dx[i];
//						int cy = j + dy[i];
//						if(canDiffuse(cx, cy)) {
//							cnt++;
//							accumulate[cx][cy] += map[i][j] / 5;
//						}
//					}
//				}
//				map[i][j] -= ((map[i][j] / 5) * cnt);
//			}
//		}
//		calcDiffuse(accumulate);
//	}
//	
//	static boolean canDiffuse(int x, int y) {
//		if(x >= 0 && x < R && y >= 0 && y < C && map[x][y] != -1) return true;
//		return false;
//	}
//	
//	static void calcDiffuse(int[][] accumulate) {
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				map[i][j] += accumulate[i][j];
//			}
//		}
//	}
//	
//	static void airCleaning() {
//		
//	}
//	
//	static void airCleaningUpside() {
//		int remain = diffuseRight(airCleaner[0][0], airCleaner[0][1], 0);
//		remain = diffuseUp(R - 1, airCleaner[0][1], remain);
//		remain = diffuseLeft(R - 1, 0, remain);
//		remain = diffuseDown(0, 0, remain);
//	}
//	
//	static int diffuseRight(int x, int y, int first) {
//		int last = map[x][C - 1];
//		for(int i = C - 2; i > y; i--) {
//			map[x][i + 1] = map[x][i];
//		}
//		return last;
//	}
//	
//	static int diffuseUp(int x, int y, int first) {
//		int last = map[0][y];
//		for(int i = 1; i <= x; i++) {
//			if(map[i - 1][y] != -1) map[i - 1][y] = map[i][y];
//		}
//		return last;
//	}
//	
//	static int diffuseLeft(int x, int y, int first) {
//		int last = map[x][0];
//		for(int i = 1; i <= y; i++) {
//			map[x][i - 1] = map[x][i];
//		}
//		return last;
//	}
//	
//	static int diffuseDown(int x, int y, int first) {
//		int last = map[x][0];
//		for(int i = 1; i <= y; i++) {
//			map[x][i - 1] = map[x][i];
//		}
//		return last;
//	}
//	
//	public static void main(String[] args) {
//		
//	}
//	
//	static class Reader {
//		BufferedReader br;
//		StringTokenizer st;
//		public Reader() {
//			br = new BufferedReader(new InputStreamReader(System.in));
//		}
//		String next() {
//			while(st == null || !st.hasMoreElements()) {
//				try {
//					st = new StringTokenizer(br.readLine());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			return st.nextToken();
//		}
//		int nextInt() {
//			return Integer.parseInt(next());
//		}
//	}
	static int R, C, T;
	static int[][] map;
	static ArrayList<int[]> airCleaner;
	static HashMap<Integer, HashSet<Integer>> dust;
	static void input() {
		Reader scanner = new Reader();
		R = scanner.nextInt();
		C = scanner.nextInt();
		T = scanner.nextInt();
		map = new int[R][C];
		airCleaner = new ArrayList<>();
		dust = new HashMap<>();
		for(int i = 0; i < R; i++) dust.put(i, new HashSet<Integer>());
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				map[r][c] = scanner.nextInt();
				if(map[r][c] != 0 && map[r][c] != -1) dust.get(r).add(c);
				else if(map[r][c] == -1) airCleaner.add(new int[] {r, c});
			}
		}
	}
	
	static void solution() {
		for(int i = 0; i < T; i++) {
			diffuse();
			if(i == 1) {
				for(int j = 0; j < R; j++) {
					for(int k = 0; k < C; k++) {
						System.out.print(map[j][k] + " ");
					}
					System.out.println();
				}
			}
			airClean();
		}
		System.out.println(getDustAmount());
	}
	
	static int getDustAmount() {
		int amount = 0;
		for(int row = 0; row < R; row++) {
			for(int col = 0; col < C; col++) {
				if(map[row][col] != 0 && map[row][col] != -1) amount += map[row][col];
			}
		}
		return amount;
	}
	
	static void diffuse() {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, -1, 0, 1};
		int[][] diffusionAmount = new int[R][C];
		HashMap<Integer, HashSet<Integer>> otherDustPosition = new HashMap<>();
		for(int r : dust.keySet()) {
			Iterator iter = dust.get(r).iterator();
			while(iter.hasNext()) {
				int c = (int)iter.next();
				int count = 0;
				for(int i = 0; i < 4; i++) {
					int cr = r + dr[i];
					int cc = c + dc[i];
					if(cr >= 0 && cr < R && cc >= 0 && cc < C && map[cr][cc] != -1) {
						diffusionAmount[cr][cc] += (map[r][c] / 5);
						count++;
						if(!otherDustPosition.containsKey(cr)) otherDustPosition.put(cr, new HashSet<>());
						otherDustPosition.get(cr).add(cc);
					}
				}
				map[r][c] -= (map[r][c] / 5) * count;
			}
		}
		updateDust(otherDustPosition, diffusionAmount);
	}
	
	static void updateDust(HashMap<Integer, HashSet<Integer>> otherDustPosition, int[][] diffusionAmount) {
		for(int r : otherDustPosition.keySet()) {
			Iterator iter = otherDustPosition.get(r).iterator();
			while(iter.hasNext()) {
				int c = (int)iter.next();
				dust.get(r).add(c);
				map[r][c] += diffusionAmount[r][c];
			}
		}
	}
	
	static void airClean() {
		upside(airCleaner.get(0)[0], airCleaner.get(0)[1]);
		downside(airCleaner.get(1)[0], airCleaner.get(1)[1]);
	}
	
	static void upside(int r, int c) {
		int remain = leftToRight(r, c);
		remain = upsideBottomToTop(r, C - 1, remain);
		remain = rightToLeft(0, C - 1, remain);
		upsideTopToBottom(0, 0, remain);
	}
	
	static void downside(int r, int c) {
		int remain = leftToRight(r, c);
		remain = downsideTopToBottom(r, C - 1, remain);
		remain = rightToLeft(R - 1, C - 1, remain);
		downsideBottomToTop(R - 1, 0, remain);
	}
	
	static int leftToRight(int r, int c) {
		int last = map[r][C - 1];
		for(int col = C - 2; col >= c + 1; col--) map[r][col + 1] = map[r][col];
		map[r][c + 1] = 0;
		return last;
	}
	
	static int rightToLeft(int r, int c, int num) {
		int last = map[r][0];
		for(int col = 1; col < c; col++) map[r][col - 1] = map[r][col];
		map[r][c - 1] = num;
		return last;
	}
	
	static int upsideBottomToTop(int r, int c, int num) {
		int last = map[0][c];
		for(int row = 1; row < r; row++) map[row - 1][c] = map[row][c];
		map[r - 1][c] = num;
		return last;
	}
	
	static void downsideBottomToTop(int r, int c, int num) {
		for(int row = airCleaner.get(1)[0] + 2; row < r; row++) map[row - 1][c] = map[row][c];
		map[r - 1][c] = num;
	}
	
	static void upsideTopToBottom(int r, int c, int num) {
		for(int row = airCleaner.get(0)[0] - 2; row > r; row--) map[row + 1][c] = map[row][c];
		map[r + 1][c] = num;
	}
	
	static int downsideTopToBottom(int r, int c, int num) {
		int last = map[R - 1][c];
		for(int row = R - 2; row > r; row--) map[row + 1][c] = map[row][c];
		map[r + 1][c] = num;
		return last;
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
