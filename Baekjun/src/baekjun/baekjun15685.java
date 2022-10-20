package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun15685 {
	static Reader scanner = new Reader();
	static final int MAX_SIZE = 101;
	static final int[] ROTATE_DIR = {1, 2, 3, 0};
	static final int[][] MOVE_PER_DIR = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static int[] startInfo;
	static boolean[][] isCurve;
	static void input() {
		startInfo = new int[4];
		startInfo[1] = scanner.nextInt(); // x좌표(col 좌표)
		startInfo[0] = scanner.nextInt(); // y좌표(row 좌표)
		startInfo[2] = scanner.nextInt(); // 방향
		startInfo[3] = scanner.nextInt(); // 세대
	}
	
	static void solution() {
		ArrayList<Curve> curveList = new ArrayList<Curve>();
		init(curveList);
		if(startInfo[3] == 0) return;
		makeDragonCurve(curveList);
	}
	
	static void init(ArrayList<Curve> curveList) {
		curveList.add(new Curve(new int[] {startInfo[0], startInfo[1]},
				new int[] {startInfo[0] + MOVE_PER_DIR[startInfo[2]][0], startInfo[1] + MOVE_PER_DIR[startInfo[2]][1]}, startInfo[2]));
		isCurve[curveList.get(0).start[0]][curveList.get(0).start[1]] = true;
		isCurve[curveList.get(0).end[0]][curveList.get(0).end[1]] = true;
	}
	
	static void makeDragonCurve(ArrayList<Curve> curveList) {
		for(; startInfo[3] > 0; startInfo[3]--) makeNthDragonCurve(curveList);
	}
	
	static void makeNthDragonCurve(ArrayList<Curve> curveList) {
		ArrayList<Curve> tempList = new ArrayList<Curve>();
		for(int index = curveList.size() - 1; index >= 0; index--) {
			int[] start = new int[2];
			// 커브의 시작 위치 설정
			if(tempList.size() == 0) {
				start[0] = curveList.get(curveList.size() - 1).end[0];
				start[1] = curveList.get(curveList.size() - 1).end[1];
			} else {
				start[0] = tempList.get(tempList.size() - 1).end[0];
				start[1] = tempList.get(tempList.size() - 1).end[1];
			}
			// 변경된 방향 설정
			int changedDir = ROTATE_DIR[curveList.get(index).direction];
			// tempList에 변경된 커브 넣기
			tempList.add(new Curve(new int[] {start[0], start[1]},
					new int[] {start[0] + MOVE_PER_DIR[changedDir][0], start[1] + MOVE_PER_DIR[changedDir][1]}, changedDir));
			// 커브로 설정된 곳 설정
			isCurve[start[0]][start[1]] = true;
			isCurve[tempList.get(tempList.size() - 1).end[0]][tempList.get(tempList.size() - 1).end[1]] = true;
		}
		// N세대의 드래곤 커브에 있는 커브들을 갱신
		curveList.addAll(tempList);
	}
	
	static int getSquareNum() {
		int result = 0;
		for(int row = 0; row < MAX_SIZE - 1; row++) {
			for(int col = 0; col < MAX_SIZE - 1; col++) {
				if(isCurve[row][col] && isCurve[row][col + 1] && isCurve[row + 1][col] && isCurve[row + 1][col + 1])
					result++;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int n = scanner.nextInt();
		isCurve = new boolean[MAX_SIZE][MAX_SIZE];
		while(n-- > 0) {
			input();
			solution();
		}
		System.out.println(getSquareNum());
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
	
	static class Curve {
		int[] start, end;
		int direction;
		public Curve(int[] start, int[] end, int direction) {
			this.start = start;
			this.end = end;
			this.direction = direction;
		}
	}
}
