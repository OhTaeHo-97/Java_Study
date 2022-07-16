package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class baekjun14395 {
	static long s, t;
	static String[] operator = {"*", "+"};
	public long calculate(long num, int operator_index) {
		if(operator_index == 0) {
			if(num * num > t || num == 1) {
				return - 1;
			}
			return num * num;
		} else {
			if(num + num > t || num == 2) {
				return - 1;
			}
			return num + num;
		}
	}
	
	public String getOperandSet() {
		Queue<Number> queue = new LinkedList<Number>();
		queue.offer(new Number(s * s, "*"));
		queue.offer(new Number(s + s, "+"));
		queue.offer(new Number(1, "/"));
		HashSet<Long> set = new HashSet<Long>();
		while(!queue.isEmpty()) {
			Number cur_num = queue.poll();
			if(cur_num.num == t) {
				return cur_num.expression;
			}
			if(cur_num.num > t) {
				continue;
			}
			for(int i = 0; i < 2; i++) {
				long next_num = calculate(cur_num.num, i);
				if(next_num == -1 || set.contains(next_num)) {
					continue;
				}
				set.add(next_num);
				queue.offer(new Number(next_num, cur_num.expression + operator[i]));
			}
		}
		return "-1";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		br.close();
		s = Long.parseLong(input[0]);
		t = Long.parseLong(input[1]);
		baekjun14395 b = new baekjun14395();
		if(s == t)
			bw.write(0 + "\n");
		else if(t == 1)
			bw.write("/" + "\n");
		else if(t == 0)
			bw.write("-" + "\n");
		else {
			bw.write(b.getOperandSet() + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static class Number {
		long num;
		String expression = "";
		public Number(long num, String expression) {
			this.num = num;
			this.expression = expression;
		}
	}
}
