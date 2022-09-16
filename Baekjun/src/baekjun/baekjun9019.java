package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun9019 {
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	static void input() {
		Reader scanner = new Reader();
		int test_num = scanner.nextInt();
		for(int test = 1; test <= test_num; test++) {			
			int A = scanner.nextInt();
			int B = scanner.nextInt();
			visited = new boolean[10000];
			bfs(A, B);
		}
	}
	
	static void bfs(int A, int B) {
		char[] operators = {'D', 'S', 'L', 'R'};
		Queue<Process> processes = new LinkedList<Process>();
		processes.add(new Process(A, ""));
		visited[A] = true;
		while(!processes.isEmpty()) {
			Process cur_pro = processes.poll();
			if(cur_pro.num == B) {
				sb.append(cur_pro.operation).append('\n');
				break;
			}
			for(int oper = 0; oper < operators.length; oper++) {
				int result = cur_pro.num;
				if(operators[oper] == 'D') {
					result = operateD(cur_pro.num);
				} else if(operators[oper] == 'S') {
					result = operateS(cur_pro.num);
				} else if(operators[oper] == 'L') {
					result = operateL(cur_pro.num);
				} else if(operators[oper] == 'R') {
					result = operateR(cur_pro.num);
				}
				if(!visited[result]) {
					visited[result] = true;
					processes.offer(new Process(result, cur_pro.operation + operators[oper]));
				}
			}
		}
	}
	
	static int operateD(int num) {
		if(num == 0) return 0;
		int result = num * 2;
		if(result > 9999) result %= 10000;
		return result;
	}
	
	static int operateS(int num) {
		if(num == 0) return 9999;
		return num - 1;
	}

	static int operateL(int num) {
		int first_num = num / 1000;
		int result = (num - first_num * 1000) * 10 + first_num;
		return result;
	}
	
	static int operateR(int num) {
		int last_num = num % 10;
		int result = last_num * 1000 + (num / 10);
		return result;
	}
	
	public static void main(String[] args) {
		input();
		System.out.println(sb);
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
	
	static class Process {
		int num;
		String operation;
		public Process(int num ,String operation) {
			this.num = num;
			this.operation = operation;
		}
	}
}
