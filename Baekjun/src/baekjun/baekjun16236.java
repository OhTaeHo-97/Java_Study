package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun16236 {
	static int N, eatFishNum = 0;
	static int[][] map;
	static ArrayList<Fish> fishes;
	static Shark shark;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		map = new int[N][N];
		fishes = new ArrayList<Fish>();
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				map[row][col] = scanner.nextInt();
				if(map[row][col] == 9) {
					shark = new Shark(row, col, 2, 0);
					map[row][col] = 0;
				}
				else if(1 <= map[row][col] && map[row][col] <= 6) {
					fishes.add(new Fish(row, col, map[row][col]));
				}
			}
		}
	}
	
	static void solution() {
		if(fishes.size() == 0) {
			System.out.println(0);
			return;
		}
		Collections.sort(fishes);
		Shark curLoc = shark;
		ArrayList<Fish> possibleFish = new ArrayList<Fish>();
		while(true) {
			if(eatFishNum == 0) possibleFish = doFishExist(curLoc.size);
			if(possibleFish.size() == 0) {
				System.out.println(curLoc.time);
				return;
			} else if(possibleFish.size() == 1) {
				curLoc = goToFish(curLoc, possibleFish.get(0));
				map[possibleFish.get(0).x][possibleFish.get(0).y] = 0;
				fishes.remove(possibleFish.get(0));
				possibleFish.remove(0);
				eatFishNum++;
			} else {
				Fish nearestFish = findNearestFish(curLoc, possibleFish);
				curLoc = goToFish(curLoc, nearestFish);
				map[nearestFish.x][nearestFish.y] = 0;
				fishes.remove(nearestFish);
				possibleFish.remove(nearestFish);
				eatFishNum++;
			}
			if(eatFishNum == curLoc.size) {
				eatFishNum = 0;
				curLoc = new Shark(curLoc.x, curLoc.y, curLoc.size + 1, curLoc.time);
			}
		}
	}
	
	static Fish findNearestFish(Shark curLoc, ArrayList<Fish> possibleFish) {
		int minDistance = 401, count = 1;
		Fish nearestFish = null;
		int[] distances = new int[possibleFish.size()];
		for(int index = 0; index < possibleFish.size(); index++) {
			Fish fish = fishes.get(index);
			distances[index] = Math.abs(curLoc.x - fish.x) + Math.abs(curLoc.y - fish.y);
			if(minDistance > distances[index]) {
				minDistance = distances[index];
				nearestFish = possibleFish.get(index);
				count = 1;
			} else if(minDistance == distances[index]) {
				count++;
			}
		}
		if(count == 1) return nearestFish;
		int x = 20, y = 20;
		for(int index = 0; index < distances.length; index++) {
			if(minDistance == distances[index]) {
				Fish fish = possibleFish.get(index);
				if(x > fish.x) {
					nearestFish = possibleFish.get(index);
					x = fish.x;
					y = fish.y;
				} else if(x == fish.x) {
					if(y > fish.y) {
						nearestFish = possibleFish.get(index);
						x = fish.x;
						y = fish.y;
					}
				}
			}
		}
		return nearestFish;
	}
	
	static Shark goToFish(Shark curSharkLoc, Fish fish) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		Queue<Shark> sharkLoc = new LinkedList<Shark>();
		Shark result = null;
		sharkLoc.offer(curSharkLoc);
		while(!sharkLoc.isEmpty()) {
			Shark curLoc = sharkLoc.poll();
			if(curLoc.x == fish.x && curLoc.y == fish.y) {
				result = curLoc;
				break;
			}
			for(int index = 0; index < 4; index++) {
				int cx = curLoc.x + dx[index];
				int cy = curLoc.y + dy[index];
				if(isPossibleLoc(cx, cy, curLoc.size)) {
					sharkLoc.offer(new Shark(cx, cy, curLoc.size, curLoc.time + 1));
				}
			}
		}
		return result;
	}
	
	static boolean isPossibleLoc(int x, int y, int size) {
		if(x >= 0 && x < N && y >= 0 && y < N) {
			if(size >= map[x][y]) return true;
		}
		return false;
	}
	
	static ArrayList<Fish> doFishExist(int size) {
		ArrayList<Fish> possibleFish = new ArrayList<Fish>();
		for(Fish fish : fishes) {
			if(fish.size >= size) break;
			possibleFish.add(fish);
		}
		return possibleFish;
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
	
	static class Shark {
		int x, y, size, time;
		public Shark(int x, int y, int size, int time) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.time = time;
		}
	}
	
	static class Fish implements Comparable<Fish> {
		int x, y, size;
		public Fish(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
		public int compareTo(Fish f) {
			return this.size - f.size;
		}
	}
}
