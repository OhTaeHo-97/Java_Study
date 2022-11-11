package programmers;

import java.util.*;

public class Level3_ChangeWord {
	static HashSet<String> candidate;
	public static int solution(String begin, String target, String[] words) {
		candidate = new HashSet<>();
		for(String word : words) candidate.add(word);
		if(!candidate.contains(target)) return 0;
		return bfs(begin, target);
	}
	
	static int bfs(String begin, String target) {
		Queue<Word> words = new LinkedList<Word>();
		HashSet<String> visited = new HashSet<String>();
		words.offer(new Word(begin, 0));
		visited.add(begin);
		int time = 0;
		while(!words.isEmpty()) {
			Word cur = words.poll();
			if(cur.word.equals(target)) {
				time = cur.count;
				break;
			}
			for(int alp = (int)'a'; alp < ((int)'a' + 26); alp++) {
				for(int index = 0; index < cur.word.length(); index++) {
					String left = cur.word.substring(0, index), right = cur.word.substring(index + 1, cur.word.length());
					String temp = left + (char)alp + right;
					if(candidate.contains(temp) && !visited.contains(temp)) {
						visited.add(temp);
						words.offer(new Word(temp, cur.count + 1));
					}
				}
			}
		}
		return time;
	}
	
	static class Word {
		String word;
		int count;
		public Word(String word, int count) {
			this.word = word;
			this.count = count;
		}
	}
	
	public static void main(String[] args) {
		String begin = "hit", target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution(begin, target, words));
	}
}
