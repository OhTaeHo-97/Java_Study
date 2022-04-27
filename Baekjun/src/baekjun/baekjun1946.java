package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class baekjun1946 {
	public int getMaxWorkerNum(Worker[] workers) {
		Arrays.sort(workers, new Comparator<Worker>() {
			public int compare(Worker w1, Worker w2) {
				return w1.documentation - w2.documentation;
			}
		});
		int count = 1;
		int prev_interview = workers[0].interview;
		for(int i = 1; i < workers.length; i++) {
			if(prev_interview > workers[i].interview) {
				count++;
				prev_interview = workers[i].interview;
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_num = Integer.parseInt(br.readLine());
		int[] maxWorkerNum = new int[test_num];
		baekjun1946 b = new baekjun1946();
		for(int i = 0; i < test_num; i++) {
			int worker_num = Integer.parseInt(br.readLine());
			Worker[] workers = new Worker[worker_num];
			for(int j = 0; j < workers.length; j++) {
				String[] input = br.readLine().split(" ");
				workers[j] = new Worker(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
			}
			maxWorkerNum[i] = b.getMaxWorkerNum(workers);
		}
		br.close();
		for(int i = 0; i < maxWorkerNum.length; i++) {
			bw.write(maxWorkerNum[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}

class Worker {
	int documentation;
	int interview;
	public Worker(int documentation, int interview) {
		this.documentation = documentation;
		this.interview = interview;
	}
}
