package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Level3_PickUpItem {
	static int[][] map;
	public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		map = new int[101][101];
		fillMap(rectangle);
		int answer = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2;
		return answer;
	}
	
	static int bfs(int startX, int startY, int endX, int endY) {
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(startX, startY, 0));
		boolean[][] visited = new boolean[101][101];
		visited[startX][startY] = true;
		int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
		int minDist = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			Position cur = queue.poll();
			if(cur.x == endX && cur.y == endY) {
				minDist = Math.min(minDist, cur.dist);
				break;
			}
			for(int dir = 0; dir < 4; dir++) {
				int cx = cur.x + dx[dir], cy = cur.y + dy[dir];
				if(isInMap(cx, cy)) {
					if(!visited[cx][cy] && map[cx][cy] == 1) {
						visited[cx][cy] = true;
						queue.offer(new Position(cx, cy, cur.dist + 1));
					}
				}
			}
		}
		return minDist;
	}
	
	static boolean isInMap(int x, int y) {
		if(x >= 0 && x < 101 && y >= 0 && y < 101) return true;
		return false;
	}
	
	static void fillMap(int[][] rectangle) {
		for(int[] r : rectangle) {
			int x1 = r[0] * 2, y1 = r[1] * 2;
			int x2 = r[2] * 2, y2 = r[3] * 2;
			fill(x1, y1, x2, y2);
		}
	}
	
	static void fill(int x1, int y1, int x2, int y2) {
		for(int row = x1; row <= x2; row++) {
			for(int col = y1; col <= y2; col++) {
				if(map[row][col] == 2) continue;
				if(row == x1 || row == x2 || col == y1 || col == y2) map[row][col] = 1;
				else map[row][col] = 2;
			}
		}
	}
	
	static class Position {
		int x, y, dist;
		public Position(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) {
		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
		int characterX = 1, characterY = 3, itemX = 7, itemY = 8;
		System.out.println(solution(rectangle, characterX, characterY, itemX, itemY));
	}
}
