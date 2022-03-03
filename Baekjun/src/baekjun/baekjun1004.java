package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1004 {
	public boolean isInPlan(int x, int y, int plan_x, int plan_y, int radius) {
		boolean inPlan = false;
		double distance = Math.sqrt((Math.pow(x - plan_x, 2)) + (Math.pow(y - plan_y, 2)));
		if(distance < radius) {
			inPlan = true;
		}
		return inPlan;
	}
	
	public int calIOCount(int[] start, int[] end, int[] x, int[] y, int[] radius) {
		int count = 0;
		for(int i = 0; i < x.length; i++) {
			if(isInPlan(start[0], start[1], x[i], y[i], radius[i]) && isInPlan(end[0], end[1], x[i], y[i], radius[i])) {
				continue;
			} else {
				if(isInPlan(start[0], start[1], x[i], y[i], radius[i])) {
					count++;
				}
				if(isInPlan(end[0], end[1], x[i], y[i], radius[i])) {
					count++;
				}
			}
		}
		return count;
	}
	
	public ArrayList<Integer> getIOCount(int num, ArrayList<String> se, ArrayList<ArrayList<String>> plans) {
		ArrayList<Integer> count = new ArrayList<Integer>();
		for(int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(se.get(i));
			int[] start = new int[2];
			int[] end = new int[2];
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			ArrayList<String> plan = plans.get(i);
			int[] x = new int[plan.size()];
			int[] y = new int[plan.size()];
			int[] radius = new int[plan.size()];
			for(int j = 0; j < plan.size(); j++) {
				st = new StringTokenizer(plan.get(j));
				x[j] = Integer.parseInt(st.nextToken());
				y[j] = Integer.parseInt(st.nextToken());
				radius[j] = Integer.parseInt(st.nextToken());
			}
			count.add(calIOCount(start, end, x, y, radius));
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		ArrayList<String> se = new ArrayList<String>();
		ArrayList<ArrayList<String>> plans = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < num; i++) {
			se.add(br.readLine());
			int plan_num = Integer.parseInt(br.readLine());
			ArrayList<String> plan = new ArrayList<String>();
			for(int j = 0; j < plan_num; j++) {
				plan.add(br.readLine());
			}
			plans.add(plan);
		}
		br.close();
		baekjun1004 b = new baekjun1004();
		ArrayList<Integer> count = b.getIOCount(num, se, plans);
		for(int i = 0; i < count.size(); i++) {
			bw.write(count.get(i) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
