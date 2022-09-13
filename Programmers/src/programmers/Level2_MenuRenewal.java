package programmers;

import java.util.*;

public class Level2_MenuRenewal {
    static HashMap<String, Integer> orderCnt;
    
    public void sortOrders(String[] orders) {
    	for(int index = 0; index < orders.length; index++) {
    		char[] order = orders[index].toCharArray();
    		Arrays.sort(order);
    		orders[index] = String.valueOf(order);
    	}
    }
    
    public void findCourses(String courseMenu, String order, int size) {
    	if(size == 0) {
    		orderCnt.put(courseMenu, orderCnt.getOrDefault(courseMenu, 0) + 1);
    		return;
    	}
    	for(int index = 0; index < order.length(); index++)
    		findCourses(courseMenu + order.charAt(index), order.substring(index + 1), size - 1);
    }

    public ArrayList<String> solution(String[] orders, int[] course) {
    	ArrayList<String> answer = new ArrayList<String>();
        
    	sortOrders(orders);
    	
    	for(int courseSize : course) {
    		orderCnt = new HashMap<String, Integer>();
    		for(String order : orders) {
    			if(order.length() >= courseSize)
    				findCourses("", order, courseSize);
    		}
    		if(!orderCnt.isEmpty()) {
    			ArrayList<Integer> cntValues = new ArrayList<Integer>(orderCnt.values());
    			int max = Collections.max(cntValues);
    			if(max >= 2) {
    				for(String key : orderCnt.keySet()) {
    					if(orderCnt.get(key) == max)
    						answer.add(key);
    				}
    			}
    		}
    	}
    	
    	Collections.sort(answer);
    	return answer;
    }
	
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		Level2_MenuRenewal l = new Level2_MenuRenewal();
		ArrayList<String> arr = l.solution(orders, course);
		System.out.println(arr);
	}
}
