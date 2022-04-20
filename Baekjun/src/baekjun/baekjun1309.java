package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class baekjun1309 {
	public int getArrangeNum(int num) {
		ArrayList<Integer> arrangeNums = new ArrayList<Integer>();
		long arrangeNum = 0;
		if(num == 1) {
			return 3;
		} else if(num == 2) {
			return 7;
		} else if(num == 3) {
			return 17;
		}
		arrangeNum = 41;
		
		arrangeNums.add(1);
		arrangeNums.add(6);
		arrangeNums.add(8);
		arrangeNums.add(2);
		for(int i = 4; i <= num; i++) {
			arrangeNums.set(0, 1);
			arrangeNums.set(1, 2 * num);
			arrangeNums.set(2, arrangeNums.get(2) + ((2 * i - 3) * 2));
			if(i == 4) {
				arrangeNums.set(3, 12);
				arrangeNums.add(2);
			} else {
				int[] nums = new int[i - 3];
				for(int j = 3; j < i; j++) {
					nums[j - 3] = arrangeNums.get(j);
				}
				for(int j = 4; j < i; j++) {
					arrangeNums.set(j, nums[j - 4]);
				}
				int third = arrangeNums.get(3);
				arrangeNums.set(4, third);
				for(int j = 2 * i - 5; j >= 1; j -= 2) {
					if(j == 2 * i - 5) {
						third += (j * 2);
					} else {
						third += (j * 4);
					}
				}
				arrangeNums.set(3, third);
				arrangeNums.add(2);
			}
		}
		for(int i = 0; i < arrangeNums.size(); i++) {
			arrangeNum += arrangeNums.get(i);
		}
		System.out.println(arrangeNum);
		return (int)(arrangeNum % 9901);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		br.close();
		baekjun1309 b = new baekjun1309();
		bw.write(b.getArrangeNum(num) + "\n");
		bw.flush();
		bw.close();
	}
}
