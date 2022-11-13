package programmers.kakaoInternship2020;

import java.util.*;

public class Level3_GemShopping {
	// 1 <= gems 길이 <= 100,000
	
	public static int[] solution(String[] gems) {
		HashSet<String> gemsType = new HashSet<>();
		HashMap<String, Integer> gemsNum = new HashMap<>();
		for(String gem : gems) {
			if(!gemsNum.containsKey(gem)) gemsNum.put(gem, 0);
			gemsType.add(gem);
		}
		int gemsTypeNum = gemsType.size();
		if(gemsTypeNum == 1) return new int[] {1, 1};
		int len = Integer.MAX_VALUE;
		int[] answer = new int[2];
		gemsType = new HashSet<>();
		gemsType.add(gems[0]);
		gemsNum.compute(gems[0], (k, v) -> ++v);
		for(int L = 0, R = 1; L < gems.length; L++) {
			while(R < gems.length) {
				if(gemsType.size() == gemsTypeNum) break;
				gemsType.add(gems[R]);
				gemsNum.compute(gems[R], (k, v) -> ++v);
				R++;
			}
			if(gemsType.size() == gemsTypeNum) {				
				if(len > (R - L)) {
					len = R - L;
					answer[0] = L + 1;
					answer[1] = R;
				}
			}
			gemsNum.compute(gems[L], (k, v) -> --v);
			if(gemsNum.get(gems[L]) == 0) gemsType.remove(gems[L]);
		}
		return answer;
	}
	
	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//		String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//		String[] gems = {"XYZ", "XYZ", "XYZ"};
//		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
//		String[] gems = {"A", "B", "B", "C", "A", "B", "C", "A", "B", "C"};
//		String[] gems = {"DIA", "EM", "EM", "RUB", "DIA"};
		int[] answer = solution(gems);
		for(int a : answer) System.out.println(a);
	}
}
