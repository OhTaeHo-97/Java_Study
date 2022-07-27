package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.TreeSet;

public class baekjun2800 {
	static char[] expression;
	static ArrayList<bracket> brackets;
	static TreeSet<String> set = new TreeSet<>();
	boolean[] visited;
	boolean isFirstDepth = true;
	public void findExpressions(int d) {
		if(d == brackets.size()) {
			if(isFirstDepth) {
				isFirstDepth = false;
			} else {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < expression.length; i++) {
					if(visited[i]) {
						sb.append(expression[i]);
					}
				}
				set.add(sb.toString());
			}
			return;
		}
		bracket b = brackets.get(d);
		visited[b.start] = true;
		visited[b.end] = true;
		findExpressions(d + 1);
		visited[b.start] = false;
		visited[b.end] = false;
		findExpressions(d + 1);
	}
	
	public void getAllExpressions() {
		visited = new boolean[expression.length];
		Arrays.fill(visited, true);
		findExpressions(0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Integer> bracket_loc = new Stack<Integer>();
		brackets = new ArrayList<bracket>();
		expression = br.readLine().toCharArray();
		br.close();
		for(int i = 0; i < expression.length; i++) {
			if(expression[i] == '(') {
				bracket_loc.push(i);
			} else if(expression[i] == ')') {
				brackets.add(new bracket(bracket_loc.pop(), i));
			}
		}
		StringBuilder sb = new StringBuilder();
		baekjun2800 b = new baekjun2800();
		b.getAllExpressions();
		for(String s : set) {
			sb.append(s).append('\n');
		}
		bw.write(sb + "\n");
		bw.flush();
		bw.close();
	}
	
	public static class bracket {
		int start, end;
		public bracket(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
