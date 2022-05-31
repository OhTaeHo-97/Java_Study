package SWExoertAcademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SW8888 {
	static int[] solved_num;
	static boolean[][] question;
	static int[] score;
	static int[] p_score;
	static int people_num, question_num, num;
	public int[] getScoreAndRank() {
		for(int i = 0; i < people_num; i++) {
			for(int j = 0; j < question_num; j++) {
				if(question[i][j]) {
					p_score[i] += score[j];
				}
			}
		}
		int rank = 1;
		for(int i = 0; i < p_score.length; i++) {
			if(i == num - 1)
				continue;
			if(p_score[i] > p_score[num - 1]) {
				rank++;
			} else if(p_score[i] == p_score[num - 1] && solved_num[i] > solved_num[num - 1]) {
				rank++;
			} else if(p_score[i] == p_score[num - 1] && solved_num[i] == solved_num[num - 1] && i < (num - 1)) {
				rank++;
			}
		}
		int[] result = {p_score[num - 1], rank};
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		SW8888 s = new SW8888();
		int test_num = Integer.parseInt(br.readLine());
		for(int i = 0; i < test_num; i++) {
			String[] input = br.readLine().split(" ");
			people_num = Integer.parseInt(input[0]);
			question_num = Integer.parseInt(input[1]);
			num = Integer.parseInt(input[2]);
			solved_num = new int[people_num];
			question = new boolean[people_num][question_num];
			score = new int[question_num];
			p_score = new int[people_num];
			for(int j = 0; j < people_num; j++) {
				input = br.readLine().split(" ");
				for(int l = 0; l < question_num; l++) {
					if(Integer.parseInt(input[l]) == 1) {
						question[j][l] = true;
						solved_num[j]++;
					} else if(Integer.parseInt(input[l]) == 0) {
						question[j][l] = false;
						score[l]++;
					}
				}
			}
			int[] result = s.getScoreAndRank();
			bw.write("#" + (i + 1) + " " + result[0] + " " + result[1] + "\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
