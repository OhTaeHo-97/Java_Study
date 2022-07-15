package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baekjun6987 {
	static int total_matches;
	static int[][] match_team;
	boolean possible = false;
	public boolean simulation(int[][] result, int round) {
		if(round == total_matches)
			return true;
		
		if(result[match_team[round][0]][0] > 0 && result[match_team[round][1]][2] > 0) {
			result[match_team[round][0]][0]--;
			result[match_team[round][1]][2]--;
			if(simulation(result, round + 1))
				return true;
			result[match_team[round][0]][0]++;
			result[match_team[round][1]][2]++;
		}
		if(result[match_team[round][0]][2] > 0 && result[match_team[round][1]][0] > 0) {
			result[match_team[round][0]][2]--;
			result[match_team[round][1]][0]--;
			if(simulation(result, round + 1))
				return true;
			result[match_team[round][0]][2]++;
			result[match_team[round][1]][0]++;
		}
		if(result[match_team[round][0]][1] > 0 && result[match_team[round][1]][1] > 0) {
			result[match_team[round][0]][1]--;
			result[match_team[round][1]][1]--;
			if(simulation(result, round + 1))
				return true;
			result[match_team[round][0]][1]++;
			result[match_team[round][1]][1]++;
		}
		return false;
	}
	
	public int isPossible(int[][] result) {
		for(int i = 0; i < result.length; i++) {
			int match_num = 0;
			for(int j = 0; j < result[i].length; j++) {
				match_num += result[i][j];
			}
			if(match_num != 5)
				return 0;
		}
		if(simulation(result, 0))
			return 1;
		else
			return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		baekjun6987 b = new baekjun6987();
		for(int i = 1; i < 6; i++) {
			total_matches += i;
		}
		int cur_match = 0;
		match_team = new int[total_matches][2];
		for(int i = 0; i < 5; i++) {
			for(int j = i + 1; j < 6; j++) {
				match_team[cur_match][0] = i;
				match_team[cur_match][1] = j;
				cur_match++;
			}
		}
		for(int i = 0; i < 4; i++) {
			int[][] result = new int[6][3];
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 6; j++) {				
				for(int l = 0; l < 3; l++) {
					result[j][l] = Integer.parseInt(input[3* j + l]);
				}
			}
			bw.write(b.isPossible(result) + " ");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
