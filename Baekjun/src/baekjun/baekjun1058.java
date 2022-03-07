package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class baekjun1058 {
	public int getMaxFriend(ArrayList<String> isfriend) {
		int max_num = 0;
		for(int i = 0; i < isfriend.size(); i++) {
			HashSet<Integer> friends = new HashSet<Integer>();
			for(int j = 0; j < isfriend.size(); j++) {
				if(isfriend.get(j).charAt(i) == 'Y') {
					friends.add(j);
				}
			}
			ArrayList<Integer> i_friends = new ArrayList<Integer>();
			for(int j = 0; j < isfriend.get(i).length(); j++) {
				if(isfriend.get(i).charAt(j) == 'Y') {
					i_friends.add(j);
				}
			}
			for(int j = 0; j < i_friends.size(); j++) {
				for(int k = 0; k < isfriend.size(); k++) {
					if(k == i) {
						continue;
					}
					if(isfriend.get(k).charAt(i_friends.get(j)) == 'Y') {
						friends.add(k);
					}
				}
			}
			if(max_num < friends.size())
				max_num = friends.size();
		}
		return max_num;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		ArrayList<String> isfriend = new ArrayList<String>();
		for(int i = 0; i < num; i++) {
			String friend = br.readLine();
			isfriend.add(friend);
		}
		br.close();
		baekjun1058 b = new baekjun1058();
		bw.write(b.getMaxFriend(isfriend) + "\n");
		bw.flush();
		bw.close();
	}
}
