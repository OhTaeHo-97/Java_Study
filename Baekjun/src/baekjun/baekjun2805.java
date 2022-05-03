package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun2805 {
	public long getMaxHeight(int need_len, int[] tree_lens) {
		Arrays.sort(tree_lens);
		long start = 0;
		long end = (long)tree_lens[tree_lens.length - 1] + 1;
		while(start < end) {
			long mid = (start + end) / 2;
			long len = 0;
			for(int i = 0; i < tree_lens.length; i++) {
				if(tree_lens[i] > mid) {
					len += (long)(tree_lens[i] - mid);
				}
			}
			if(len < need_len) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start - 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int tree_num = Integer.parseInt(input[0]);
		int need_len = Integer.parseInt(input[1]);
		int[] tree_lens = new int[tree_num];
		input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < tree_num; i++) {
			tree_lens[i] = Integer.parseInt(input[i]);
		}
		baekjun2805 b = new baekjun2805();
		bw.write(b.getMaxHeight(need_len, tree_lens) + "\n");
		bw.flush();
		bw.close();
	}
}
