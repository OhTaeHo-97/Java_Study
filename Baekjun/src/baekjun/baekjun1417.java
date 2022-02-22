package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class baekjun1417 {
	public int purchaseNum(ArrayList<Integer> vote_num, int first) {
		if(vote_num.size() == 0) {
			return 0;
		}
		int count = 0;
		while(true) {
			Collections.sort(vote_num);
			if(first > vote_num.get(vote_num.size() - 1)) {
				break;
			}
			first++;
			vote_num.set(vote_num.size() - 1, vote_num.get(vote_num.size() - 1) - 1);
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int first = Integer.parseInt(br.readLine());
		ArrayList<Integer> vote_num = new ArrayList<Integer>();
		for(int i = 0; i < num - 1; i++) {
			vote_num.add(Integer.parseInt(br.readLine()));
		}
		baekjun1417 b = new baekjun1417();
		System.out.println(b.purchaseNum(vote_num, first));
	}
}
