package programmers;

import java.util.*;

public class Level3_DoublePriorityQueue {
	static TreeMap<Integer, Integer> map;
	public static int[] solution(String[] operations) {
        map = new TreeMap<Integer, Integer>();
        for(String operation : operations) {
        	String[] oper = operation.split(" ");
        	int num = Integer.parseInt(oper[1]);
        	if(oper[0].equals("D")) {
        		if(map.size() == 0) continue;
        		delete(num);
        	} else if(oper[0].equals("I")) {
        		insert(num);
        	}
        }
        return map.size() == 0 ? new int[] {0, 0} : new int[] {map.lastKey(), map.firstKey()};
    }
	
	static void insert(int num) {
		map.put(num, map.getOrDefault(num, 0) + 1);
	}
	
	static void delete(int num) {
		int key = num == 1 ? map.lastKey() : map.firstKey();
		if(map.put(key, map.get(key) - 1) == 1) map.remove(key);
	}
}
