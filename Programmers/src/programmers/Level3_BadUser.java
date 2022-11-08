package programmers;

import java.util.*;

public class Level3_BadUser {
//	static LinkedList<String>[] lists;
//	static HashSet<Comb> answer;
//	public static int solution(String[] user_id, String[] banned_id) {
//		answer = new HashSet<>();
//		lists = new LinkedList[banned_id.length];
//		for(int index = 0; index < banned_id.length; index++) lists[index] = new LinkedList<>();
//		findIds(user_id, banned_id);
//		dfs(0, lists.length, new HashSet<String>());
//        return answer.size();
//    }
//	
//	static void findIds(String[] user_id, String[] banned_id) {
//		for(int index = 0; index < banned_id.length; index++) {
//			String expression = banned_id[index];
//			expression = expression.replaceAll("[*]", "[a-z0-9]");
//			for(int index2 = 0; index2 < user_id.length; index2++) {
//				if(user_id[index2].matches(expression)) lists[index].add(user_id[index2]);
//			}
//		}
//	}
//	
//	static void dfs(int idx, int size, HashSet<String> set) {
//		if(size == 0) {
//			LinkedList<String> temp = new LinkedList<>();
//			Iterator iter = set.iterator();
//			while(iter.hasNext()) {
//				temp.add((String)iter.next());
//			}
//			Comb c = new Comb(temp);
//			if(!answer.contains(c)) answer.add(c);
//			return;
//		}
//		for(int index = 0; index < lists[idx].size(); index++) {
//			String temp = lists[idx].get(index);
//			if(set.add(temp)) {
//				dfs(idx + 1, size - 1, set);
//				set.remove(temp);
//			}
//		}
//	}
//	
//	static class Comb {
//		LinkedList<String> list;
//		public Comb() {
//			list = new LinkedList<>();
//		}
//		public Comb(LinkedList<String> list) {
//			this.list = list;
//		}
// 		@Override
//		public boolean equals(Object obj) {
//			Comb c = (Comb)obj;
//			if(list.size() != c.list.size()) return false;
//			Collections.sort(list);
//			Collections.sort(c.list);
//			for(int index = 0; index < list.size(); index++) {
//				if(!list.get(index).equals(c.list.get(index))) return false;
//			}
//			return true;
//		}
//		@Override
//		public int hashCode() {
//			return Objects.hash(list.get(0));
//		}
//	}
	
	static String[] regex;
    static HashSet<Integer> set = new HashSet<>();
    public static int solution(String[] user_id, String[] banned_id) {
        regex = new String[banned_id.length];
        for(int i=0; i<banned_id.length; i++){
            regex[i] = banned_id[i].replace("*", "[\\w]");
        }
        dfs(0,user_id,0);
        
        return set.size();
    }
    static void dfs(int index, String[] user_id, int bit){
        if(index==regex.length){
            set.add(bit);
            return;
        }
        for(int i=0; i<user_id.length; ++i) {
            if((((bit>>i) & 1) != 1) && user_id[i].matches(regex[index])){
                dfs(index+1, user_id, bit|1<<i);
            }
        }
    }
	
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*"};
		System.out.println(solution(user_id, banned_id));
	}
}
