package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjun1051 {
	public int findSquare(int[] size, ArrayList<String> rectangle) {
		if(size[0] == 1 || size[1] == 1) {
			return 1;
		}
		int count;
		if(size[0] > size[1]) {
			count = size[1];
		} else {
			count = size[0];
		}
		while(true) {
			for(int i = 0; i <= size[0] - count; i++) {
				for(int j = 0; j <= size[1] - count; j++) {
					if(Integer.parseInt(rectangle.get(i).substring(j, j + 1)) == Integer.parseInt(rectangle.get(i).substring(j + (count - 1), j + count))
							&& Integer.parseInt(rectangle.get(i).substring(j, j + 1)) == Integer.parseInt(rectangle.get(i + (count - 1)).substring(j + (count - 1), j + count))
							&& Integer.parseInt(rectangle.get(i).substring(j, j + 1)) == Integer.parseInt(rectangle.get(i + (count - 1)).substring(j, j + 1))) {
						return count * count;
					}
				}
			}
			count--;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String size_str = br.readLine();
		StringTokenizer st = new StringTokenizer(size_str);
		int[] size = new int[2];
		size[0] = Integer.parseInt(st.nextToken());
		size[1] = Integer.parseInt(st.nextToken());
		ArrayList<String> rectangle = new ArrayList<String>();
		for(int i = 0; i < size[0]; i++) {
			rectangle.add(br.readLine());
		}
		br.close();
		baekjun1051 b = new baekjun1051();
		bw.write(b.findSquare(size, rectangle) + "\n");
		bw.flush();
		bw.close();
	}
}
