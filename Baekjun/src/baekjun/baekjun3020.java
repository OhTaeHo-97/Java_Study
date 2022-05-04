package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class baekjun3020 {
	public int getObstacleNum(int start, int end, int height, int[] obstacle) {
		while(start < end) {
			int mid = (start + end) / 2;
			if(obstacle[mid] >= height) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return obstacle.length - end;
	}
	
	public int[] getMinObstacle(int len, int height, int[] obstacle_len) {
		int[] stalagmite = new int[len / 2]; // 석순(아래에서 위로)
		int[] stalactite = new int[len / 2]; // 종유석(위에서 아래로)
		int s1 = 0;
		int s2 = 0;
		for(int i = 0; i < obstacle_len.length; i++) {
			if(i % 2 == 0) {
				stalagmite[s1] = obstacle_len[i];
				s1++;
			} else {
				stalactite[s2] = obstacle_len[i];
				s2++;
			}
		}
		Arrays.sort(stalagmite);
		Arrays.sort(stalactite);
		int min = len;
		int count = 0;
		for(int i = 1; i <= height; i++) {
			int obstacle = getObstacleNum(0, len / 2, i, stalagmite) + getObstacleNum(0, len / 2, height - i + 1, stalactite);
			if(min == obstacle) {
				count++;
				continue;
			}
			if(min > obstacle) {
				min = obstacle;
				count = 1;
			}
		}
		int[] result = new int[2];
		result[0] = min;
		result[1] = count;
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int len = Integer.parseInt(input[0]);
		int height = Integer.parseInt(input[1]);
		int[] obstacle_len = new int[len];
		for(int i = 0; i < len; i++) {
			obstacle_len[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		baekjun3020 b = new baekjun3020();
		int[] result = b.getMinObstacle(len, height, obstacle_len);
		for(int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
