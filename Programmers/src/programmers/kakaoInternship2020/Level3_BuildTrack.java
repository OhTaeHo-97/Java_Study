package programmers.kakaoInternship2020;

import java.util.*;

public class Level3_BuildTrack {
	static int size;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	static int[] dirPerDxDy = {4, 3, 2, 1};
	public static int solution(int[][] board) {
		size = board.length;
		return bfs(board);
	}
	
	static int bfs(int[][] board) {
		int answer = 0;
		int[][][] cost = new int[size][size][4]; // 네 방향으로 방문 체크
		cost[0][0][0] = cost[0][0][1] = cost[0][0][2] = cost[0][0][3] = -1;
		PriorityQueue<Track> queue = new PriorityQueue<>();
		if(board[0][1] == 0) {
			queue.offer(new Track(new int[] {0, 1}, 100, 0)); // 오른쪽
			cost[0][1][0] = 100;
		}
		if(board[1][0] == 0) {
			queue.offer(new Track(new int[] {1, 0}, 100, 1)); // 아래
			cost[1][0][1] = 100;
		}
		while(!queue.isEmpty()) {
			Track cur = queue.poll();
			if(cur.loc[0] == size - 1 && cur.loc[1] == size - 1) {
				answer = cur.cost;
				break;
			}
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur.loc[0] + dx[dir], cy = cur.loc[1] + dy[dir];
				if(isInMap(cx, cy) && board[cx][cy] == 0) {
					int curCost = 0;
					if(cur.direction == dir) curCost = 100;
					else curCost = 600;
					if(cost[cx][cy][dir] == 0 || cost[cx][cy][dir] >= cur.cost + curCost) {
						cost[cx][cy][dir] = cur.cost + curCost;
						queue.offer(new Track(new int[] {cx, cy}, cost[cx][cy][dir], dir));
					}
				}
			}
		}
		return answer;
	}
	
	static boolean isInMap(int x, int y) {
		if(x >= 0 && x < size && y >= 0 && y < size) return true;
		return false;
	}
	
	static class Track implements Comparable<Track> {
		int[] loc;
		int cost, direction;
		public Track(int[] loc, int cost, int direction) {
			this.loc = loc;
			this.cost = cost;
			this.direction = direction;
		}
		public int compareTo(Track t) {
			return cost - t.cost;
		}
	}
	
	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0}
		,{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		System.out.println(solution(board));
	}
}
