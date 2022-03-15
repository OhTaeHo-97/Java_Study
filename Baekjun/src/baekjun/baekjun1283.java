package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1283 {
	public boolean case1(ArrayList<String> temp, ArrayList<String> shortcut, ArrayList<String> result) {
		for(int i = 0; i < temp.size(); i++) {
			if(!shortcut.contains(temp.get(i).substring(0, 1).toLowerCase())) {
				shortcut.add(temp.get(i).substring(0, 1).toLowerCase());
				String temp_str = "";
				for(int j = 0; j < temp.size(); j++) {
					if(i == j) {
						temp_str += "[" + temp.get(j).substring(0, 1) + "]" + temp.get(j).substring(1) + " ";
					} else {
						temp_str += temp.get(j) + " ";
					}
				}
				result.add(temp_str);
				return true;
			}
		}
		return false;
	}
	
	public boolean case2(String temp, ArrayList<String> shortcut, ArrayList<String> result) {
		for(int i = 0; i < temp.length(); i++) {
			if(temp.substring(i, i + 1).equals(" "))
				continue;
			if(!shortcut.contains(temp.substring(i, i + 1).toLowerCase())) {
				shortcut.add(temp.substring(i, i + 1).toLowerCase());
				result.add(temp.substring(0, i) + "[" + temp.substring(i, i + 1) + "]" + temp.substring(i + 1));
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getShortcut(String[] words) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> shortcut = new ArrayList<String>();
		shortcut.add(words[0].substring(0, 1).toLowerCase());
		result.add("[" + words[0].substring(0, 1) + "]" + words[0].substring(1));
		for(int i = 1; i < words.length; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(words[i]);
			boolean isShortcut = true;
			while(st.hasMoreTokens()) {
				temp.add(st.nextToken());
			}
			if(case1(temp, shortcut, result)) {
				continue;
			} else {
				if(case2(words[i], shortcut, result)) {
					continue;
				} else {
					result.add(words[i]);
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		String[] words = new String[num];
		for(int i = 0; i < num; i++) {
			words[i] = br.readLine();
		}
		baekjun1283 b = new baekjun1283();
		ArrayList<String> result = b.getShortcut(words);
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
}
