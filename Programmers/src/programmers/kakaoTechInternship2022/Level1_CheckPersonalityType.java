package programmers.kakaoTechInternship2022;

import java.util.*;

public class Level1_CheckPersonalityType {
	public String solution(String[] survey, int[] choices) {
        char[][] indicator = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        HashMap<Character, Integer>[] frequency = new HashMap[4];
        for(int row = 0; row < 4; row++) {
        	frequency[row] = new HashMap<>();
        	frequency[row].put(indicator[row][0], 0);
        	frequency[row].put(indicator[row][1], 0);
        }
        for(int size = 0; size < survey.length; size++)
        	countIndicator(survey[size], choices[size], frequency);
        String answer = makePersonalityType(frequency);
        return answer;
    }
	
	public void countIndicator(String survey, int choice, HashMap<Character, Integer>[] frequency) {
		char disagree = survey.charAt(0), agree = survey.charAt(1);
		if(choice == 4) return;
		HashMap<Character, Integer> temp = new HashMap<>();;
		for(int index = 0; index < 4; index++) {
			if(frequency[index].containsKey(disagree)) temp = frequency[index];
		}
		if(choice < 4) {
			temp.put(disagree, temp.getOrDefault(disagree, 0) + Math.abs(4 - choice));
		} else {
			temp.put(agree, temp.getOrDefault(agree, 0) + Math.abs(4 - choice));
		}
	}
	
	public String makePersonalityType(HashMap<Character, Integer>[] frequency) {
		String type = "";
		for(int size = 0; size < frequency.length; size++) {
			List<Map.Entry<Character, Integer>> list = new ArrayList<>(frequency[size].entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
				public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
					if(e1.getValue() != e2.getValue()) return e2.getValue() - e1.getValue();
					return e1.getKey() - e2.getKey();
				}
			});
			type += list.get(0).getKey();
		}
		return type;
	}
	
	public static void main(String[] args) {
//		String[] survey = new String[] {"AN", "CF", "MJ", "RT", "NA"};
//		int[] choices = new int[] {5, 3, 2, 7, 5};
		String[] survey = new String[] {"TR", "RT", "TR"};
		int[] choices = new int[] {7, 1, 3};
		Level1_CheckPersonalityType l = new Level1_CheckPersonalityType();
		System.out.println(l.solution(survey, choices));
	}
}
