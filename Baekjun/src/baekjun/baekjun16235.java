package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16235 {
	// M개의 나무 구매, 각 칸에 여러 나무 가능, 처음 모든 칸 양분 5
	// 봄 - 나이만큼 양분 먹고 나이 1 증가, 어린 나무부터 양분 먹음, 나이만큼 못먹으면 먹지 못하고 죽음
	// 여름 - 봄에 죽은 나무가 양분으로 변함(나이 / 2)
	// 가을 - 나무 번식 -> 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생김
	// 겨울 - S2D2가 땅을 돌아다니면서 땅에 양분 추가, 각 칸에 추가되는 양분의 양은 A[r][c]
	// K년 후에 땅에 살아있는 나무의 개수
	
	// 1 <= N <= 10
	// 1 <= M <= N^2
	// 1 <= K <= 1000
	// 1 <= A[r][c] <= 100
	static int N, M, K;
	static LinkedList<Tree> trees;
	static Queue<Tree> dead;
	static int[][] A, nourishment;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		K = scanner.nextInt();
		trees = new LinkedList<>();
		dead = new LinkedList<>();
		A = new int[N][N];
		nourishment = new int[N][N];
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				nourishment[row][col] = 5;
				A[row][col] = scanner.nextInt();
			}
		}
		for(int count = 0; count < M; count++) {
			int x = scanner.nextInt(), y = scanner.nextInt(), age = scanner.nextInt();
			trees.add(new Tree(x - 1, y - 1, age));
		}
	}
	
	static void solution() {
		Collections.sort(trees);
		for(int year = 1; year <= K; year++) {
			spring();
			summer();
			fall();
			winter();
		}
		System.out.println(trees.size());
	}
	
	static void spring() {
		LinkedList<Tree> temp = new LinkedList<>();
		for(Tree tree : trees) {
			if(tree.age <= nourishment[tree.x][tree.y]) {
				temp.add(new Tree(tree.x, tree.y, tree.age + 1));
				nourishment[tree.x][tree.y] -= tree.age; 
			} else {
				dead.offer(tree);
			}
		}
		trees = temp;
	}
	
	static void summer() {
		while(!dead.isEmpty()) {
			Tree tree = dead.poll();
			nourishment[tree.x][tree.y] += (tree.age / 2); 
		}
	}
	
	static void fall() {
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
		LinkedList<Tree> temp = new LinkedList<>();
		for(Tree tree : trees) {
			if(tree.age % 5 == 0) {
				for(int dir = 0; dir < 8; dir++) {
					int cx = tree.x + dx[dir], cy = tree.y + dy[dir];
					if(isInMap(cx, cy)) temp.add(new Tree(cx, cy, 1));
				}
			}
		}
		trees.addAll(0, temp);
	}
	
	static void winter() {
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) nourishment[row][col] += A[row][col];
		}
	}
	
	static boolean isInMap(int x, int y) {
		if(x >= 0 && x < N && y >= 0 && y < N) return true;
		return false;
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Tree implements Comparable<Tree> {
		int x, y, age;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
		public int compareTo(Tree t) {
			return age - t.age;
		}
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
