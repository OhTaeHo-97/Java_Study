package programmers.MonthlyCodeChallangeSeason1;

public class Level3_PoppingBalloon {
//	static HashSet<Integer> set;
//	public static int solution(int[] a) {
//		LinkedList<Integer> list = new LinkedList<>();
//		for(int num : a) list.add(num);
//		set = new HashSet<>();
//		fn(false, list);
//		Iterator iter = set.iterator();
//		while(iter.hasNext()) {
//			System.out.println(iter.next());
//		}
//		return set.size();
//	}
//	
//	static void fn(boolean isSmall, LinkedList<Integer> list) {
//		if(list.size() == 1) {
//			set.add(list.get(0));
//			return;
//		}
//		for(int index = 0; index < list.size() - 1; index++) {
//			int first = list.get(index), second = list.get(index + 1);
//			int max = Math.max(first, second), min = Math.min(first, second);
//			int minIdx = min == first ? index : index + 1, maxIdx = max == first ? index : index + 1;
//			if(!isSmall) {				
//				list.remove(minIdx);
//				fn(true, list);
//				list.add(minIdx, min);
//			}
//			list.remove(maxIdx);
//			fn(isSmall, list);
//			list.add(maxIdx, max);
//		}
//	}
	
	public static int solution(int[] a) {
		int[] leftMin = new int[a.length], rightMin = new int[a.length];
		int min = Integer.MAX_VALUE;
		for(int idx = 0; idx < a.length; idx++) {
			if(min > a[idx]) min = a[idx];
			leftMin[idx] = min;
		}
		min = Integer.MAX_VALUE;
		for(int idx = a.length - 1; idx >= 0; idx--) {
			if(min > a[idx]) min = a[idx];
			rightMin[idx] = min;
		}
		int count = 0;
		for(int idx = 1; idx < a.length - 1; idx++) {
			if(leftMin[idx] < a[idx] && rightMin[idx] < a[idx]) count++;
		}
		return a.length - count;
	}
	
	public static void main(String[] args) {
//		int[] a = {9,-1,-5};
//		int[] a = {-16,27,65,-2,58,-92,-71,-68,-61,-33};
		int[] a = {-16,27,-2,58,-92,-71,-68,-61,-33,65};
		System.out.println(solution(a));
	}
}
