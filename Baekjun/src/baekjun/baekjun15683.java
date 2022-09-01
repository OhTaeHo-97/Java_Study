package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun15683 {
	static int N, M;
	static int[][] map;
	static int[][] cnt;
	static ArrayList<Point> CCTVs;
	static int min = Integer.MAX_VALUE;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		map = new int[N][M];
		cnt = new int[N][M];
		CCTVs = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = scanner.nextInt();
				if(map[i][j] != 0 && map[i][j] != 6) CCTVs.add(new Point(i, j));
			}
		}
	}
	
	static void solution(int idx) {
		if(idx == CCTVs.size()) {
			min = Math.min(min, getBlindSpotNum());
			return;
		}
		
		// 1: 오른쪽, 2: 아래, 3: 왼쪽, 4: 위
		for(int j = 1; j <= 4; j++) {
			Point cctv = CCTVs.get(idx);
			switch(map[cctv.x][cctv.y]) {
				case 1:
					if(j == 1) {
						fillRight(cctv, -1);
						solution(idx + 1);
						fillRight(cctv, 0);
					} else if(j == 2) {
						fillDown(cctv, -1);
						solution(idx + 1);
						fillDown(cctv, 0);
					} else if(j == 3) {
						fillLeft(cctv, -1);
						solution(idx + 1);
						fillLeft(cctv, 0);
					} else if(j == 4) {
						fillUp(cctv, -1);
						solution(idx + 1);
						fillUp(cctv, 0);
					}
					break;
				case 2:
					if(j == 1 || j == 3) {
						fillRight(cctv, -1);
						fillLeft(cctv, -1);
						solution(idx + 1);
						fillRight(cctv, 0);
						fillLeft(cctv, 0);
					} else if(j == 2 || j == 4) {
						fillUp(cctv, -1);
						fillDown(cctv, -1);
						solution(idx + 1);
						fillDown(cctv, 0);
						fillUp(cctv, 0);
					}
					break;
				case 3:
					if(j == 1) {
						fillRight(cctv, -1);
						fillUp(cctv, -1);
						solution(idx + 1);
						fillRight(cctv, 0);
						fillUp(cctv, 0);
					} else if(j == 2) {
						fillRight(cctv, -1);
						fillDown(cctv, -1);
						solution(idx + 1);
						fillRight(cctv, 0);
						fillDown(cctv, 0);
					} else if(j == 3) {
						fillLeft(cctv, -1);
						fillDown(cctv, -1);
						solution(idx + 1);
						fillLeft(cctv, 0);
						fillDown(cctv, 0);
					} else if(j == 4) {
						fillLeft(cctv, -1);
						fillUp(cctv, -1);
						solution(idx + 1);
						fillLeft(cctv, 0);
						fillUp(cctv, 0);
					}
					break;
				case 4:
					if(j == 1) {
						fillRight(cctv, -1);
						fillUp(cctv, -1);
						fillLeft(cctv, -1);
						solution(idx + 1);
						fillRight(cctv, 0);
						fillUp(cctv, 0);
						fillLeft(cctv, 0);
					} else if(j == 2) {
						fillRight(cctv, -1);
						fillUp(cctv, -1);
						fillDown(cctv, -1);
						solution(idx + 1);
						fillRight(cctv, 0);
						fillUp(cctv, 0);
						fillDown(cctv, 0);
					} else if(j == 3) {
						fillRight(cctv, -1);
						fillDown(cctv, -1);
						fillLeft(cctv, -1);
						solution(idx + 1);
						fillRight(cctv, 0);
						fillDown(cctv, 0);
						fillLeft(cctv, 0);
					} else if(j == 4) {
						fillDown(cctv, -1);
						fillUp(cctv, -1);
						fillLeft(cctv, -1);
						solution(idx + 1);
						fillDown(cctv, 0);
						fillUp(cctv, 0);
						fillLeft(cctv, 0);
					}
					break;
				case 5:
					fillRight(cctv, -1);
					fillUp(cctv, -1);
					fillDown(cctv, -1);
					fillLeft(cctv, -1);
					solution(idx + 1);
					fillRight(cctv, 0);
					fillUp(cctv, 0);
					fillDown(cctv, 0);
					fillLeft(cctv, 0);
					break;
				default:
					break;
			}
		}
		
	}
	
	static void fillRight(Point p, int type) {
		if(type != 0) {
			for(int i = p.y + 1; i < M; i++) {
				if(map[p.x][i] == 0 || map[p.x][i] == -1) {
					cnt[p.x][i]++;
					map[p.x][i] = type;
				}
				else if(map[p.x][i] == 6) break;
			}
		} else {
			for(int i = p.y + 1; i < M; i++) {
				if(map[p.x][i] == -1) {
					cnt[p.x][i]--;
					if(cnt[p.x][i] == 0) map[p.x][i] = type;
				}
				else if(map[p.x][i] == 6) break;
			}
		}
	}
	
	static void fillLeft(Point p, int type) {
		if(type != 0) {
			for(int i = p.y - 1; i >= 0; i--) {
				if(map[p.x][i] == 0 || map[p.x][i] == -1) {
					cnt[p.x][i]++;
					map[p.x][i] = type;
				}
				else if(map[p.x][i] == 6) break;
			}
		} else {
			for(int i = p.y - 1; i >= 0; i--) {
				if(map[p.x][i] == -1) {
					cnt[p.x][i]--;
					if(cnt[p.x][i] == 0) map[p.x][i] = type;
				}
				else if(map[p.x][i] == 6) break;
			}
		}
	}
	
	static void fillUp(Point p, int type) {
		if(type != 0) {
			for(int i = p.x - 1; i >= 0; i--) {
				if(map[i][p.y] == 0 || map[i][p.y] == -1) {
					cnt[i][p.y]++;
					map[i][p.y] = type;
				}
				else if(map[i][p.y] == 6) break;
			}
		} else {
			for(int i = p.x - 1; i >= 0; i--) {
				if(map[i][p.y] == -1) {
					cnt[i][p.y]--;
					if(cnt[i][p.y] == 0) map[i][p.y] = type;
				}
				else if(map[i][p.y] == 6) break;
			}
		}
	}
	
	static void fillDown(Point p, int type) {
		if(type != 0) {
			for(int i = p.x + 1; i < N; i++) {
				if(map[i][p.y] == 0 || map[i][p.y] == -1) {
					cnt[i][p.y]++;
					map[i][p.y] = type;
				}
				else if(map[i][p.y] == 6) break;
			}
		} else {
			for(int i = p.x + 1; i < N; i++) {
				if(map[i][p.y] == -1) {
					cnt[i][p.y]--;
					if(cnt[i][p.y] == 0) map[i][p.y] = type;
				}
				else if(map[i][p.y] == 6) break;
			}
		}
	}
	
	static int getBlindSpotNum() {
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) sum++;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		input();
		solution(0);
		System.out.println(min);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
