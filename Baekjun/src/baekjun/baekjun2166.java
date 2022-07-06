package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun2166 {
	public long getWidth(Point p1, Point p2, Point p3) {
		return ((p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y));
	}
	
	public double getWidthOfPolygon(Point[] points) {
		long result = 0;
		for(int i = 1; i < points.length - 1; i++) {
			result += getWidth(points[0], points[i], points[i + 1]);
		}
		result = Math.abs(result);
		return (double)result / 2;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		Point[] points = new Point[num];
		for(int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			points[i] = new Point(Long.parseLong(input[0]), Long.parseLong(input[1]));
		}
		br.close();
		baekjun2166 b = new baekjun2166();
		double result = b.getWidthOfPolygon(points);
		bw.write(String.format("%.1f", result) + "\n");
		bw.flush();
		bw.close();
	}
	
	static class Point {
		long x, y;
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
}
