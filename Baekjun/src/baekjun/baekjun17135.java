package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjun17135 {
	// 게임이 진행되는 곳은 N x M 격자판
	// 각 칸에 포함된 적의 수는 최대 하나
	// 성을 향해 몰려오는 적을 잡는 턴 방식의 게임
	// 격자판의 N번 행 바로 아래의 모든 칸에는 성이 있음
	
	// 궁수 3명 배치 -> 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수
	// 각 턴마다 궁수는 적 하나를 공격, 모든 궁수가 동시 공격
	// 궁수가 공격하는 적은 거리가 D 이하인 적 중 가장 가까운 적
	// 그러한 적이 여럿이면 가장 왼쪽에 있는 적 공격
	// 같은 적이 여러 궁수에게 공격당할 수 있음
	// 공격받은 적은 게임에서 제외됨
	// 궁수의 공격이 끝나면 적이 이동
	// 적은 아래로 한칸 이동, 성이 있는 칸으로 이동한 경우 게임에서 제외됨
	// 모든 적이 격자판에서 제외되면 게임 끝남
	
	// 격자판 상태가 주어졌을 때, 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산
	// 3 <= N, M <= 15
	// 1 <= D <= 10
	
	static int N, M, D, answer;
	static int[][] board;
	static LinkedList<Point> bowLoc;
	static ArrayList<Point> enemyLoc, tempLoc;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		D = scanner.nextInt();
		board = new int[N + 1][M];
		bowLoc = new LinkedList<>();
		enemyLoc = new ArrayList<>();
		tempLoc = new ArrayList<>();
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				board[row][col] = scanner.nextInt();
				if(board[row][col] == 1) {
					enemyLoc.add(new Point(row, col));
					tempLoc.add(new Point(row, col));
				}
			}
		}
	}
	
	static void solution() {
		answer = 0;
		int[][] copy = new int[N + 1][M];
		for(int row = 0; row < copy.length; row++) copy[row] = board[row].clone();
		selectBow(0, 0);
		System.out.println(answer);
	}
	
	static void selectBow(int index, int size) {
		if(size == 3) {
			int removedNum = simulate();
			answer = Math.max(answer, removedNum);
			tempLoc = new ArrayList<>();
			for(Point p : enemyLoc) tempLoc.add(new Point(p.x, p.y));
			return;
		}
		for(int idx = index; idx < M; idx++) {
			board[N][idx] = 2;
			bowLoc.add(new Point(N, idx));
			selectBow(idx, size + 1);
			bowLoc.remove(bowLoc.size() - 1);
			board[N][idx] = 0;
		}
	}
	
	static int simulate() {
		int total = 0;
		HashSet<Point> set = null;
		while(true) {
			if(tempLoc.size() == 0) break;
			set = new HashSet<>();
			for(Point bLoc : bowLoc) {
				PriorityQueue<Point> queue = new PriorityQueue<Point>(new Comparator<Point>() {
					public int compare(Point p1, Point p2) {
						int dist1 = Math.abs(bLoc.x - p1.x) + Math.abs(bLoc.y - p1.y);
						int dist2 = Math.abs(bLoc.x - p2.x) + Math.abs(bLoc.y - p2.y);
						if(dist1 != dist2) return dist1 - dist2;
						return p1.y - p2.y;
					}
				});
				for(Point eLoc : tempLoc) {
					if(Math.abs(bLoc.x - eLoc.x) + Math.abs(bLoc.y - eLoc.y) <= D) {
						queue.offer(eLoc);
					}
				}
				Point attacked = queue.poll();
				if(attacked != null) set.add(attacked);
			}
			ArrayList<Point> newLoc = new ArrayList<>();
			for(int idx = 0; idx < tempLoc.size(); idx++) {
				if(set.contains(tempLoc.get(idx))) continue;
				else {
					Point enemy = tempLoc.get(idx);
					if(enemy.x + 1 >= N) continue;
					newLoc.add(new Point(enemy.x + 1, enemy.y));
				}
			}
			tempLoc = newLoc;
			total += set.size();
		}
		return total;
	}
	
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equals(Object obj) {
			Point p = (Point)obj;
			if(this.x == p.x && this.y == p.y) return true;
			return false;
		}
		public int hashCode() {
			return Objects.hash(x, y);
		}
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
