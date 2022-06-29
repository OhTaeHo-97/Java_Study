package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class baekjun11000 {
	public int getClassroomNum(Class[] class_list) {
		Arrays.sort(class_list);
		PriorityQueue<Integer> pqueue = new PriorityQueue<>();
		pqueue.offer(class_list[0].end);
		for(int i = 1; i < class_list.length; i++) {
			if(pqueue.peek() <= class_list[i].start) {
				pqueue.poll();
			}
			pqueue.offer(class_list[i].end);
		}
//		HashMap<Integer, ArrayList<Class>> map = new HashMap<>();
//		map.put(1, new ArrayList<Class>(Arrays.asList(class_list[0])));
//		int max_key = 1;
//		for(int i = 1; i < class_list.length; i++) {
//			boolean flag = false;
//			for(int key : map.keySet()) {
//				Class last_class = map.get(key).get(map.get(key).size() - 1);
//				if(class_list[i].start >= last_class.end) {
//					map.get(key).add(class_list[i]);
//					flag = true;
//					break;
//				}
//			}
//			if(!flag) {
//				max_key++;
//				map.put(max_key, new ArrayList<Class>(Arrays.asList(class_list[i])));
//			}
//		}
		return pqueue.size();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		Class[] class_list = new Class[num];
		for(int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			class_list[i] = new Class(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		br.close();
		baekjun11000 b = new baekjun11000();
		bw.write(b.getClassroomNum(class_list) + "\n");
		bw.flush();
		bw.close();
	}
	
	public static class Class implements Comparable<Class> {
		int start, end;
		public Class(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Class c) {
			// TODO Auto-generated method stub
			if(this.start < c.start) {
				return -1;
			} else if(this.start > c.start) {
				return 1;
			} else {
				if(this.end < c.end) {
					return -1;
				} else if(this.end > c.end) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}
