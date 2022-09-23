package programmers;

import java.util.*;

public class Level3_FindPairCard {
//	static HashMap<Integer, Point[]> cardLoc;
//	public static int solution(int[][] board, int r, int c) {
//		findCardLoc(board);
//		Point selected = new Point(-1, -1), curLoc = new Point(r, c), nearestPoint;
//		int time = 0;
//		if(board[r][c] != 0) {
//			selected = new Point(r, c);
//			time++;
//		}
//		while(true) {
//			if(isFinish(board)) break;
//			if(selected.x == -1 && selected.y == -1) {
//				nearestPoint = bfs(curLoc, board);
//				time += bfs(curLoc, nearestPoint, board);
//				selected = nearestPoint;
//				curLoc = nearestPoint;
//				time++;
//			} else {
//				Point target = null;
//				if(cardLoc.get(board[curLoc.x][curLoc.y])[0].x == curLoc.x && cardLoc.get(board[curLoc.x][curLoc.y])[0].y == curLoc.y)
//					target = cardLoc.get(board[curLoc.x][curLoc.y])[1];
//				else
//					target = cardLoc.get(board[curLoc.x][curLoc.y])[0];
//				time += bfs(curLoc, target, board);
//				selected = new Point(-1, -1);
//				board[curLoc.x][curLoc.y] = 0;
//				board[target.x][target.y] = 0;
//				curLoc = target;
//				time++;
//			}
//		}
//		return time;
//	}
//	
//	public static void findCardLoc(int[][] board) {
//		cardLoc = new HashMap<Integer, Point[]>();
//		for(int num = 1; num <= 6; num++) cardLoc.put(num, new Point[2]);
//		for(int row = 0; row < 4; row++) {
//			for(int col = 0; col < 4; col++) {
//				if(board[row][col] != 0) {
//					if(cardLoc.get(board[row][col])[0] != null)
//						cardLoc.get(board[row][col])[1] = new Point(row, col);
//					else
//						cardLoc.get(board[row][col])[0] = new Point(row, col);
//				}
//			}
//		}
// 	}
//	
//	public static boolean isFinish(int[][] board) {
//		boolean flag = true;
//		for(int row = 0; row < 4; row++) {
//			for(int col = 0; col < 4; col++) {
//				if(board[row][col] != 0) {
//					flag = false;
//					break;
//				}
//			}
//		}
//		return flag;
//	}
//	
//	public static Point bfs(Point curLoc, int[][] board) {
//		Queue<Point> loc = new LinkedList<Point>();
//		int[] dx = {-1, 0, 1, 0};
//		int[] dy = {0, -1, 0, 1};
//		loc.offer(curLoc);
//		boolean[][] visited = new boolean[4][4];
//		visited[curLoc.x][curLoc.y] = true;
//		while(!loc.isEmpty()) {
//			Point current = loc.poll();
//			for(int row = current.x; row < 4; row++) {
//				if(board[row][current.y] != 0 && !visited[row][current.y]) return new Point(row, current.y);
//			}
//			for(int row = current.x; row >= 0; row--) {
//				if(board[row][current.y] != 0 && !visited[row][current.y]) return new Point(row, current.y);
//			}
//			for(int col = current.y; col < 4; col++) {
//				if(board[current.x][col] != 0 && !visited[current.x][col]) return new Point(current.x, col);
//			}
//			for(int col = current.y; col >= 0; col--) {
//				if(board[current.x][col] != 0 && !visited[current.x][col]) return new Point(current.x, col);
//			}
//			for(int index = 0; index < 4; index++) {
//				int cx = transformPosition(current.x + dx[index]);
//				int cy = transformPosition(current.y + dy[index]);
//				if(!visited[cx][cy]) {
//					visited[cx][cy] = true;
//					loc.offer(new Point(cx, cy));
//				}
//			}
//		}
//		return null;
//	}
//	
//	public static int bfs(Point curLoc, Point target, int[][] board) {
//		Queue<Point> loc = new LinkedList<Point>();
//		int[] dx = {-1, 0, 1, 0};
//		int[] dy = {0, -1, 0, 1};
//		loc.offer(curLoc);
//		boolean[][] visited = new boolean[4][4];
//		visited[curLoc.x][curLoc.y] = true;
//		int[][] time = new int[4][4];
//		int minTime = Integer.MAX_VALUE;
//		while(!loc.isEmpty()) {
//			Point current = loc.poll();
//			if(current.x == target.x && current.y == target.y) {
//				if(time[current.x][current.y] < minTime) {
//					minTime = time[current.x][current.y];
//					break;
//				}
//			}
//			for(int row = current.x; row < 4; row++) {
//				if(board[row][current.y] != 0 && !visited[row][current.y]) {
//					visited[row][current.y] = true;
//					time[row][current.y] = time[current.x][current.y] + 1;
//					loc.offer(new Point(row, current.y));
//					break;
//				}
//			}
//			for(int row = current.x; row >= 0; row--) {
//				if(board[row][current.y] != 0 && !visited[row][current.y]) {
//					visited[row][current.y] = true;
//					time[row][current.y] = time[current.x][current.y] + 1;
//					loc.offer(new Point(row, current.y));
//					break;
//				}
//			}
//			for(int col = current.y; col < 4; col++) {
//				if(board[current.x][col] != 0 && !visited[current.x][col]) {
//					visited[current.x][col] = true;
//					time[current.x][col] = time[current.x][current.y] + 1;
//					loc.offer(new Point(current.x, col));
//					break;
//				}
//			}
//			for(int col = current.y; col >= 0; col--) {
//				if(board[current.x][col] != 0 && !visited[current.x][col]) {
//					visited[current.x][col] = true;
//					time[current.x][col] = time[current.x][current.y] + 1;
//					loc.offer(new Point(current.x, col));
//					break;
//				}
//			}
//			for(int index = 0; index < 4; index++) {
//				int cx = transformPosition(current.x + dx[index]);
//				int cy = transformPosition(current.y + dy[index]);
//				if(!visited[cx][cy]) {
//					visited[cx][cy] = true;
//					time[cx][cy] = time[current.x][current.y] + 1;
//					loc.offer(new Point(cx, cy));
//				}
//			}
//		}
//		return minTime;
//	}
//	
//	public static int transformPosition(int position) {
//		if(position < 0) return 3;
//		else if(position >= 4) return 0;
//		return position;
//	}
	
	static ArrayList<String> orders;
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	public static int solution(int[][] board, int r, int c) {
		int answer = Integer.MAX_VALUE;
		
		int cardNum = 0;
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if(board[row][col] != 0) cardNum++;
			}
		}
		cardNum /= 2;
		
		int[] cards = new int[cardNum];
		for(int index = 0; index < cards.length; index++) cards[index] = index + 1;
		
		orders = new ArrayList<String>();
		makeOrder("", 0, cards);
		
		for(String order : orders) {
			String[] eachCard = order.split("");
			
			int totalMove = 0;
			int[] position = new int[2];
			position[0] = r;
			position[1] = c;
			
			int[][] copy = new int[4][4];
			for(int row = 0; row < 4; row++) {
				for(int col = 0; col < 4; col++) {
					copy[row][col] = board[row][col];
				}
			}
			
			for(String card : eachCard) {
				int target = Integer.parseInt(card);
				
				totalMove += bfs(position, target, copy);
				System.out.println("position[0]: " + position[0] + ", position[1]: " + position[1]);
				copy[position[0]][position[1]] = 0;
				totalMove += 1;
				
				totalMove += bfs(position, target, copy);
				copy[position[0]][position[1]] = 0;
				totalMove += 1;
			}
			answer = Math.min(answer, totalMove);
		}
		
		return answer;
	}
	
	static void makeOrder(String order, int depth, int[] cards) {
		if(depth == cards.length) {
			orders.add(order);
			return;
		}
		for(int card = 0; card < cards.length; card++) {
			if(!order.contains(Integer.toString(cards[card]))) {
				makeOrder(order + cards[card], depth + 1, cards);
			}
		}
	}
	
	static int bfs(int[] position, int target, int[][] copy) {
		Queue<Point> queue = new LinkedList<Point>();
		boolean[][] visited = new boolean[4][4];
		queue.offer(new Point(position[0], position[1], 0));
		visited[position[0]][position[1]] = true;
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			int x = curPoint.x;
			int y = curPoint.y;
			int move = curPoint.move;
			if(copy[x][y] == target) {
				System.out.println("["+target+ "] find! "+ x+","+y+ ":"+ move);
				position[0] = x;
				position[1] = y;
				return move;
			}
			for(int direction = 0; direction < 4; direction++) {
				Point nearest = findNearCard(x, y, direction, copy);
				int cx = nearest.x, cy = nearest.y;
				if(cx == x && cy == y) continue;
				if(!visited[cx][cy]) {
					visited[cx][cy] = true;
					queue.offer(new Point(cx, cy, move + 1));
				}
			}
			for(int direction = 0; direction < 4; direction++) {
				int cx = x + dx[direction];
				int cy = y + dy[direction];
				if(cx >= 0 && cx < 4 && cy >= 0 && cy < 4) {
					if(!visited[cx][cy]) {
						visited[cx][cy] = true;
						queue.offer(new Point(cx, cy, move + 1));
					}
				}
			}
		}
		return 0;
	}
	
	static Point findNearCard(int x, int y, int direction, int[][] copy) {
		x += dx[direction];
		y += dy[direction];
		
		while(x >= 0 && x < 4 && y >= 0 && y < 4) {
			if(copy[x][y] != 0) return new Point(x,y,0);
			
			x += dx[direction];
			y += dy[direction];
		}
		
		return new Point(x-dx[direction],y-dy[direction],0);
	}
	
	public static void main(String[] args) {
//		int[][] board = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
		int[][] board = {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}};
//		int r = 1;
		int r = 0;
//		int c = 0;
		int c = 1;
		System.out.println(solution(board, r, c));
	}
	
	static class Point {
		int x, y, move;
		public Point(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
//	static class Point {
//		int x, y;
//		public Point(int x, int y) {
//			this.x = x;
//			this.y = y;
//		}
//	}
}
