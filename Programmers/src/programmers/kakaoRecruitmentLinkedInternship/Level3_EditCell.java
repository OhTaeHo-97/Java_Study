package programmers.kakaoRecruitmentLinkedInternship;

import java.util.*;

public class Level3_EditCell {
	static StringBuilder sb;
	static Stack<Cell> removed;
	static int[] prev, next;
	static int selected;
	public static String solution(int n, int k, String[] cmd) {
		removed = new Stack<>();
		prev = new int[n];
		next = new int[n];
		selected = k;
		for(int idx = 0; idx < n; idx++) {
			prev[idx] = idx - 1;
			next[idx] = idx + 1;
		}
		next[n - 1] = -1;
		sb = new StringBuilder("O".repeat(n));
		for(String command : cmd) {
			String[] cmdArr = command.split(" ");
			if(cmdArr.length > 1) {
				int amount = Integer.valueOf(cmdArr[1]);
				if(cmdArr[0].equals("U")) moveUpCell(amount);
				else if(cmdArr[0].equals("D")) moveDownCell(amount);
			} else {
				if(cmdArr[0].equals("C")) deleteCell();
				else if(cmdArr[0].equals("Z")) recoverCell();
			}
		}
		return sb.toString();
	}
	
	static void moveUpCell(int amount) {
		for(int move = 1; move <= amount; move++) selected = prev[selected];
	}
	
	static void moveDownCell(int amount) {
		for(int move = 1; move <= amount; move++) selected = next[selected];
	}
	
	static void deleteCell() {
		removed.push(new Cell(selected, prev[selected], next[selected]));
		if(prev[selected] != -1) next[prev[selected]] = next[selected];
		if(next[selected] != -1) prev[next[selected]] = prev[selected];
		sb.setCharAt(selected, 'X');
		if(next[selected] != -1) selected = next[selected];
		else selected = prev[selected];
	}
	
	static void recoverCell() {
		Cell recoveredCell = removed.pop();
		if(recoveredCell.prev != -1) next[recoveredCell.prev] = recoveredCell.idx;
		if(recoveredCell.next != -1) prev[recoveredCell.next] = recoveredCell.idx;
		sb.setCharAt(recoveredCell.idx, 'O');
	}
	
	static class Cell {
		int idx, prev, next;
		public Cell(int idx, int prev, int next) {
			this.idx = idx;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
//		int n = 8, k = 2;
//		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		int n = 8, k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		System.out.println(solution(n, k, cmd));
	}
}
