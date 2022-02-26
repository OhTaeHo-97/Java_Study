package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class baekjun1235 {
	
//	첫 번째 방법
	
//	public int find(ArrayList<String> reverse_num) {
//		int count = 1;
//		while(true) {
//			ArrayList<String> identifier = new ArrayList<String>();
//			for(int i = 0; i < reverse_num.size(); i++) {
//				String temp = "";
//				for(int j = 0; j < count; j++) {
//					temp += reverse_num.get(i).substring(j, j + 1);	
//				}
//				identifier.add(temp);
//			}
//			boolean flag = true;
//			for(int i = 0; i < identifier.size() - 1; i++) {
//				for(int j = i + 1; j < identifier.size(); j++) {
//					if(identifier.get(i).equals(identifier.get(j))) {
//						flag = false;
//						break;
//					}
//				}
//				if(!flag)
//					break;
//			}
//			if(flag)
//				break;
//			count++;
//		}
//		return count;
//	}
//	
//	public int findIdentifier(ArrayList<String> stu_nums) {
//		ArrayList<String> reverse_num = new ArrayList<String>();
//		for(int i = 0; i < stu_nums.size(); i++) {
//			String num = "";
//			for(int j = stu_nums.get(i).length(); j > 0; j--) {
//				num += stu_nums.get(i).substring(j - 1, j);
//			}
//			reverse_num.add(num);
//		}
//		int result = find(reverse_num);
//		return result;
//	}
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int num = Integer.parseInt(br.readLine());
//		ArrayList<String> stu_nums = new ArrayList<String>();
//		for(int i = 0; i < num; i++) {
//			stu_nums.add(br.readLine());
//		}
//		baekjun1235 b = new baekjun1235();
//		System.out.println(b.findIdentifier(stu_nums));
//	}
	
	
//	두 번째 방법
	
	public int findIdentifier(ArrayList<String> stu_nums) {
		int total_length = stu_nums.get(0).length();
		int length = total_length - 1;
		while(true) {
			Set identifier = new HashSet();
			for(int i = 0; i < stu_nums.size(); i++) {
				identifier.add(stu_nums.get(i).substring(length, total_length));
			}
			if(identifier.size() == stu_nums.size()) {
				break;
			}
			length--;
		}
		return total_length - length;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		ArrayList<String> stu_nums = new ArrayList<String>();
		for(int i = 0; i < num; i++) {
			stu_nums.add(br.readLine());
		}
		baekjun1235 b = new baekjun1235();
		System.out.println(b.findIdentifier(stu_nums));
	}
}
