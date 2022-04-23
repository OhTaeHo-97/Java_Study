package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class baekjun1931 {
	public int getMaxTaskNum(Task[] tasks) {
		Arrays.sort(tasks, new Comparator<Task>() {
			public int compare(Task t1, Task t2) {
				if(t1.end == t2.end) {
					return t1.start - t2.start;
				}
				return t1.end - t2.end;
			}
		});
		int prev_end = tasks[0].end;
		int count = 1;
		for(int i = 1; i < tasks.length; i++) {
			if(prev_end <= tasks[i].start) {
				count++;
				prev_end = tasks[i].end;
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int task_num = Integer.parseInt(br.readLine());
		Task[] tasks = new Task[task_num];
		for(int i = 0; i < task_num; i++) {
			String[] times = br.readLine().split(" ");
			tasks[i] = new Task(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
		}
		br.close();
		baekjun1931 b = new baekjun1931();
		bw.write(b.getMaxTaskNum(tasks) + "\n");
		bw.flush();
		bw.close();
	}
}
class Task {
	int start;
	int end;
	public Task(int start, int end) {
		this.start = start;
		this.end = end;
	}
}