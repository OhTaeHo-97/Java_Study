package programmers;

public class Level2_CompressString {
	static int solution(String s) {
		int answer = Integer.MAX_VALUE;
		for(int size = 1; size <= s.length() / 2; size++) {
			String standard = s.substring(0, size);
			int num = 1;
			String result = "";
			for(int index = size; index <= s.length(); index += size) {
				String next = s.substring(index, index + size > s.length() ? s.length() : index + size);
				if(standard.equals(next)) num++;
				else {
					result += (num == 1 ? "" : num) + standard;
					standard = next;
					num = 1;
				}
			}
			result += standard;
			System.out.println(result);
			answer = Math.min(answer, result.length());
		}
        return answer;
	}
	
	public static void main(String[] args) {
//		String s = "aabbaccc";
//		String s = "ababcdcdababcdcd";
		String s = "abcabcdede";
//		String s = "abcabcabcabcdededededede";
//		String s = "xababcdcdababcdcd";
		System.out.println(solution(s));
	}
}
