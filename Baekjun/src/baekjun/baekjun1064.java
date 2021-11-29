package baekjun;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class baekjun1064 {
	public double parallel(int[] p1, int[] p2, int[] p3) {
		if((p2[1] - p1[1]) * (p3[0] - p1[0]) == (p3[1] - p1[1]) * (p2[0] - p1[0])) {
			return -1;
		}
		
		double[] distance = new double[3];
		distance[0] = Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
		distance[1] = Math.sqrt(Math.pow(p1[0] - p3[0], 2) + Math.pow(p1[1] - p3[1], 2));
		distance[2] = Math.sqrt(Math.pow(p2[0] - p3[0], 2) + Math.pow(p2[1] - p3[1], 2));
		
		double min = distance[0];
		double max = distance[0];
		for(int i = 1; i < distance.length; i++) {
			if(min > distance[i]) {
				min = distance[i];
			}
			if(max < distance[i]) {
				max = distance[i];
			}
		}
		
		return (max - min) * 2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int[] p1 = new int[2];
		int[] p2 = new int[2];
		int[] p3 = new int[2];
		
		p1[0] = Integer.parseInt(st.nextToken());
		p1[1] = Integer.parseInt(st.nextToken());
		p2[0] = Integer.parseInt(st.nextToken());
		p2[1] = Integer.parseInt(st.nextToken());
		p3[0] = Integer.parseInt(st.nextToken());
		p3[1] = Integer.parseInt(st.nextToken());
		
		baekjun1064 p = new baekjun1064();
		double result = p.parallel(p1, p2, p3);
		System.out.printf("%.10f\n", result);
	}
}
