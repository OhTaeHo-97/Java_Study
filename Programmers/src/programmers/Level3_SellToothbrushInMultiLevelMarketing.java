package programmers;

import java.util.*;

public class Level3_SellToothbrushInMultiLevelMarketing {
	static HashMap<String, Integer> profit, men;
	static HashMap<String, String> map;
	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		initialize(enroll, seller, amount);
		makeMap(enroll, referral, seller, amount);
		findProfit(seller, amount);
		int[] answer = new int[enroll.length];
		makeArr(enroll, answer);
		return answer;
	}
	
	public static void initialize(String[] enroll, String[] seller, int[] amount) {
		profit = new HashMap<String, Integer>();
		for(String e : enroll) profit.put(e, 0);
		men = new HashMap<String, Integer>();
		map = new HashMap<String, String>();
		for(String e : enroll) men.put(e, 0);
		for(String e : enroll) map.put(e, null);
	}
	
	public static void makeMap(String[] enroll, String[] referral, String[] seller, int[] amount) {
		for(int index = 0; index < enroll.length; index++) {
			String e = enroll[index]; // 참여자
			String r = referral[index]; // 추천인
			if(!r.equals("-")) {
				men.put(r, men.get(r) + 1);
				map.put(e, r);
			}
		}
	}
	
	public static void findProfit(String[] seller, int[] amount) {
		for(int index = 0; index < seller.length; index++) {
			String s = seller[index];
			int gain = amount[index] * 100;
			int commission = (int)(gain * 0.1);
			profit.put(s, profit.get(s) + gain - commission);
			while(true) {
				String referral = map.get(s);
				if(referral == null) break;
				profit.put(referral, profit.get(referral) + commission - (int)(commission * 0.1));
				commission = (int)(commission * 0.1);
				if(commission <= 0) break;
				s = referral;
			}
		}
	}
	
	public static void makeArr(String[] enroll, int[] answer) {
		for(int index = 0; index < enroll.length; index++)
			answer[index] = profit.get(enroll[index]);
	}
			
	public static void main(String[] args) {
//		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
//		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
//		String[] sellar = {"young", "john", "tod", "emily", "mary"};
//		int[] amount = {12, 4, 2, 5, 10};
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] sellar = {"sam", "emily", "jaimie", "edward"};
		int[] amount = {2, 3, 5, 4};
		int[] answer = solution(enroll, referral, sellar, amount);
		for(int a : answer) System.out.println(a);
	}
}
