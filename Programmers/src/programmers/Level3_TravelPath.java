package programmers;

import java.util.ArrayList;
import java.util.Collections;

public class Level3_TravelPath {
	boolean[] visited;
	ArrayList<String> answers;
	public String[] solution(String[][] tickets) {
		visited = new boolean[tickets.length];
		answers = new ArrayList<String>();
		int count = 0;
		dfs(count, "ICN", "ICN", tickets);
		Collections.sort(answers);
		String[] answer = answers.get(0).split(" ");
		return answer;
	}
	
	public void dfs(int count, String present, String answer, String[][] tickets) {
		if(count == tickets.length) {
			answers.add(answer);
			return;
		}
		for(int i = 0; i < tickets.length; i++) {
			if(!visited[i] && tickets[i][0].equals(present)) {
				visited[i] = true;
				dfs(count + 1, tickets[i][1], answer + " " + tickets[i][1], tickets);
				visited[i] = false;
			}
		}
		return;
	}
	
	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		Level3_TravelPath l = new Level3_TravelPath();
		String[] answer = l.solution(tickets);
		for(int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
}
