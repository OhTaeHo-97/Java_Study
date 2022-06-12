package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class baekjun2170 {
	public int getLineLength(ArrayList<Point> isLines) {
		Collections.sort(isLines);
		int start = isLines.get(0).start;
		int end = isLines.get(0).end;
		int length = end - start;
		for(int i = 1; i < isLines.size(); i++) {
			if(start <= isLines.get(i).start && isLines.get(i).end <= end) {
				continue;
			} else if(isLines.get(i).start < end) {
				length += -(end - isLines.get(i).start) + (isLines.get(i).end - isLines.get(i).start);
			} else {
				length += isLines.get(i).end - isLines.get(i).start;
			}
			start = isLines.get(i).start;
			end = isLines.get(i).end;
		}
		return length;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		ArrayList<Point> isLines = new ArrayList<>();
		for(int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			isLines.add(new Point(Math.min(start, end), Math.max(start, end)));
		}
		br.close();
		baekjun2170 b = new baekjun2170();
		bw.write(b.getLineLength(isLines) + "\n");
		bw.flush();
		bw.close();
	}
	
	public static class Point implements Comparable<Point> {
		int start, end;
		public Point(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Point e) {
			if(this.start != e.start) {				
				return this.start - e.start;
			} else {
				return this.end - e.end;
			}
		}
		
	}
}
