package programmers;

import java.util.*;

public class Level3_BestAlbum {
	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, Music> map = new HashMap<>();
		for(int index = 0; index < genres.length; index++) {
			String genre = genres[index];
			int play = plays[index];
			if(!map.containsKey(genre)) {
				map.put(genre, new Music(play, new LinkedList<>(Arrays.asList(index))));
			} else {
				Music cur = map.get(genre);
				int playSum = cur.play + play;
				LinkedList<Integer> temp = cur.musics;
				temp.add(index);
				map.put(genre, new Music(playSum, temp));
			}
		}
		List<Map.Entry<String, Music>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Music>>() {
			public int compare(Map.Entry<String, Music> l1, Map.Entry<String, Music> l2) {
				return l2.getValue().play - l1.getValue().play;
			}
		});
		LinkedList<Integer> songs = new LinkedList<>();
		int idx = 0;
		for(int index = 0; index < list.size(); index++) {
			LinkedList<Integer> temp = list.get(index).getValue().musics;
			if(temp.size() >= 2) {
				Collections.sort(temp, new Comparator<Integer>() {
					public int compare(Integer t1, Integer t2) {
						if(plays[t1] != plays[t2]) return plays[t2] - plays[t1];
						return t1 - t2;
					}
				});
				songs.add(temp.get(0));
				songs.add(temp.get(1));
			} else if(temp.size() == 1) {
				songs.add(temp.get(0));
			}
		}
		int[] answer = makeListToArr(songs);
		return answer;
	}
	
	static int[] makeListToArr(LinkedList<Integer> songs) {
		int[] answer = new int[songs.size()];
		for(int index = 0; index < songs.size(); index++) answer[index] = songs.get(index);
		return answer;
	}
	
	static class Music {
		int play;
		LinkedList<Integer> musics;
		public Music(int play, LinkedList<Integer> musics) {
			this.play = play;
			this.musics = musics;
		}
	}
	
	public static void main(String[] args) {
		String[] answers = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] answer = solution(answers, plays);
		for(int a : answer) System.out.println(a);
	}
}
