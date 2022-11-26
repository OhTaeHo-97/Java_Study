package programmers.kakaoBlindRecruitment2020;

import java.util.*;

public class Level3_MoveBlock {
	public static int solution(int[][] board) {
		int answer = 0;
		Queue<Robot> queue = new LinkedList<>();
		int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
		boolean[][][] visited = new boolean[board.length][board.length][2];
		queue.offer(new Robot(0, 0, 0, 1, 0, 0));
		while(!queue.isEmpty()) {
			Robot cur = queue.poll();
			if(cur.x1 < 0 || cur.x1 >= board.length || cur.y1 < 0 || cur.y1 >= board.length ||
				cur.x2 < 0 || cur.x2 >= board.length || cur.y2 < 0 || cur.y2 >= board.length)
				continue;
			if(board[cur.x1][cur.y1] == 1 || board[cur.x2][cur.y2] == 1) continue;
			if(visited[cur.x1][cur.y1][cur.direction] && visited[cur.x2][cur.y2][cur.direction])
				continue;
			if((cur.x1 == board.length - 1 && cur.y1 == board.length - 1) ||
				(cur.x2 == board.length - 1 && cur.y2 == board.length - 1)) {
				answer = cur.time;
				break;
			}
			visited[cur.x1][cur.y1][cur.direction] = true;
			visited[cur.x2][cur.y2][cur.direction] = true;
			for(int dir = 0; dir < 4; dir++) {
				int cX1 = cur.x1 + dx[dir], cY1 = cur.y1 + dy[dir];
				int cX2 = cur.x2 + dx[dir], cY2 = cur.y2 + dy[dir];
				queue.offer(new Robot(cX1, cY1, cX2, cY2, cur.direction, cur.time + 1));
			}
			if(cur.direction == 1) {
				// 아래: 오른쪽, 위: 왼쪽
				if(cur.y1 - 1 >= 0 && board[cur.x1][cur.y1 - 1] == 0 &&
						board[cur.x2][cur.y2 - 1] == 0) {
					queue.offer(new Robot(cur.x1, cur.y1, cur.x1, cur.y1 - 1, 0, cur.time + 1));
					queue.offer(new Robot(cur.x2, cur.y2, cur.x2, cur.y2 - 1, 0, cur.time + 1));
				}
				if(cur.y1 + 1 < board.length && board[cur.x1][cur.y1 + 1] == 0 &&
						board[cur.x2][cur.y2 + 1] == 0) {
					queue.offer(new Robot(cur.x1, cur.y1, cur.x1, cur.y1 + 1, 0, cur.time + 1));
					queue.offer(new Robot(cur.x2, cur.y2, cur.x2, cur.y2 + 1, 0, cur.time + 1));
				}
			} else if(cur.direction == 0) {
				if(cur.x1 - 1 >= 0 && board[cur.x1 - 1][cur.y1] == 0 &&
						board[cur.x2 - 1][cur.y2] == 0) {
					queue.offer(new Robot(cur.x1, cur.y1, cur.x1 - 1, cur.y1, 1, cur.time + 1));
					queue.offer(new Robot(cur.x2, cur.y2, cur.x2 - 1, cur.y2, 1, cur.time + 1));
				}
				if(cur.x1 + 1 < board.length && board[cur.x1 + 1][cur.y1] == 0 &&
						board[cur.x2 + 1][cur.y2] == 0) {
					queue.offer(new Robot(cur.x1, cur.y1, cur.x1 + 1, cur.y1, 1, cur.time + 1));
					queue.offer(new Robot(cur.x2, cur.y2, cur.x2 + 1, cur.y2, 1, cur.time + 1));
				}
			}
		}
		return answer;
	}
	
	static class Robot {
		int x1, y1, x2, y2, time, direction;
		public Robot(int x1, int y1, int x2, int y2, int direction, int time) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.direction = direction;
			this.time = time;
		}
	}
	
	public static void main(String[] args) {
		int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
		System.out.println(solution(board));
	}
}
