package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class baekjun1092 {
	public int getMinTime(Integer[] cranes, ArrayList<Integer> boxes) {
		Arrays.sort(cranes, Collections.reverseOrder());
		Collections.sort(boxes, Collections.reverseOrder());
		if(boxes.get(0) > cranes[0]) {
			return -1;
		}
		int time = 0;
		while(boxes.size() > 0) {
			for(int i = 0; i < cranes.length; i++) {
				for(int j = 0; j < boxes.size(); j++) {
					if(cranes[i] >= boxes.get(j)) {
						boxes.remove(j);
						break;
					}
				}
			}
			time += 1;
		}
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int crane_num = Integer.parseInt(br.readLine());
		Integer[] cranes = new Integer[crane_num];
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < cranes.length; i++) {
			cranes[i] = Integer.parseInt(input[i]);
		}
		int box_num = Integer.parseInt(br.readLine());
		ArrayList<Integer> boxes = new ArrayList<Integer>();
		input = br.readLine().split(" ");
		br.close();
		for(int i = 0; i < box_num; i++) {
			boxes.add(Integer.parseInt(input[i]));
		}
		baekjun1092 b = new baekjun1092();
		bw.write(b.getMinTime(cranes, boxes) + "\n");
		bw.flush();
		bw.close();
	}
}
