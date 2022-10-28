package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjun1043 {
	// BFS 풀이
//	static int N, M;
//	static HashMap<Integer, ArrayList<Integer>> personPerParty, partyPerPerson;
//	static boolean[] people, parties;
//	static int[] truthPeople;
//	static void input() {
//		Reader scanner = new Reader();
//		N = scanner.nextInt();
//		M = scanner.nextInt();
//		people = new boolean[N + 1];
//		parties = new boolean[M + 1];
//		personPerParty = new HashMap<Integer, ArrayList<Integer>>();
//		partyPerPerson = new HashMap<Integer, ArrayList<Integer>>();
//		for(int person = 1; person <= N; person++) partyPerPerson.put(person, new ArrayList<>());
//		for(int party = 1; party <= M; party++) personPerParty.put(party, new ArrayList<>());
//		int num = scanner.nextInt();
//		truthPeople = new int[num];
//		for(int person = 0; person < num; person++) truthPeople[person] = scanner.nextInt();
//		for(int party = 1; party <= M; party++) {
//			int peopleNum = scanner.nextInt();
//			for(int person = 0; person < peopleNum; person++) {
//				int p = scanner.nextInt();
//				partyPerPerson.get(p).add(party);
//				personPerParty.get(party).add(p);
//			}
//		}
//	}
//	
//	static void solution() {
//		for(int person : truthPeople) bfs(person);
//		int result = 0;
//		for(int party = 1; party <= M; party++) {
//			if(!parties[party]) result++;
//		}
//		System.out.println(result);
//	}
//	static void bfs(int start) {
//		Queue<Integer> queue = new LinkedList<Integer>();
//		queue.add(start);
//		people[start] = true;
//		while(!queue.isEmpty()) {
//			int cur = queue.poll();
//			for(int party : partyPerPerson.get(cur)) {
//				parties[party] = true;
//				for(int person : personPerParty.get(party)) {
//					if(!people[person]) {
//						people[person] = true;
//						queue.add(person);
//					}
//				}
//			}
//		}
//	}
	
	// 분리 집합(Union-Find) 이용한 풀이
	static int N, M;
	static int[] parents, truthPeople;
	static HashMap<Integer, ArrayList<Integer>> peoplePerParty;
	static boolean[] people;
	static void input() {
		Reader scanner = new Reader();
		N = scanner.nextInt();
		M = scanner.nextInt();
		parents = new int[N + 1];
		for(int person = 1; person <= N; person++) parents[person] = person;
		peoplePerParty = new HashMap<>();
		for(int party = 1; party <= M; party++) peoplePerParty.put(party, new ArrayList<>());
		people = new boolean[N + 1];
		int num = scanner.nextInt();
		truthPeople = new int[num];
		for(int person = 0; person < num; person++) truthPeople[person] = scanner.nextInt();
		for(int party = 1; party <= M; party++) {
			int peopleNum = scanner.nextInt();
			if(peopleNum == 1) {
				peoplePerParty.get(party).add(scanner.nextInt());
				continue;
			}
			int first = scanner.nextInt();
			peoplePerParty.get(party).add(first);
			for(int person = 1; person < peopleNum; person++) {				
				int cur = scanner.nextInt();
				union(first, cur);
				peoplePerParty.get(party).add(cur);
			}
		}
	}
	
	static void solution() {
		for(int person : truthPeople) {
			if(!people[person]) {
				int parent = findParents(person);
				people[person] = true;
				for(int p = 1; p <= N; p++) {
					if(parent == findParents(p)) people[p] = true;
				}
			}
		}
		int result = 0;
		for(int party : peoplePerParty.keySet()) {
			boolean flag = false;
			for(int person : peoplePerParty.get(party)) {
				if(people[person]) {
					flag = true;
					break;
				}
			}
			if(!flag) result++;
		}
		System.out.println(result);
	}
	
	static int findParents(int person) {
		if(person == parents[person]) return person;
		return parents[person] = findParents(parents[person]);
	}
	
	static void union(int person1, int person2) {
		int parent1 = findParents(person1);
		int parent2 = findParents(person2);
		if(parent1 != parent2) {
			if(parent1 < parent2) parents[parent2] = parent1;
			else parents[parent1] = parent2;
		}
	}
	
	public static void main(String[] args) {
		input();
		solution();
	}
	
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
