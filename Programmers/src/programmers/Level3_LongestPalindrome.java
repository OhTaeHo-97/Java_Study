package programmers;

public class Level3_LongestPalindrome {
	public static int solution(String s) {
        int answer = 1;
        int size = s.length();
        boolean[][] dp = new boolean[size][size];

        // 길이가 1일 때 -> 모두 1
        // 길이가 2일 때 -> 인접이 같으면 dp[i][i + 1] = 1
        for(int idx = 0; idx < size; idx++) {
            dp[idx][idx] = true;
            if(idx == size - 1) break;
            if(s.charAt(idx) == s.charAt(idx + 1)) dp[idx][idx + 1] = true;
        }

        // 길이가 3 이상일 때
        //      양쪽 끝이 같고 양쪽 끝을 제외한 중간 문자열이 팰린드롬인지 확인
        //      그렇다면 팰린드롬
        for(int len = 3; len <= size; len++) {
            for(int idx = 0; idx <= size - len; idx++) {
                if(s.charAt(idx) == s.charAt(idx + len - 1) &&
                dp[idx + 1][idx + len - 2]) {
                    dp[idx][idx + len - 1] = true;
                    answer = Math.max(answer, len);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("a"));
        System.out.println(solution("abacde"));
    }
}
