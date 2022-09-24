package programmers;

public class Level2_FindPrimeNumCount {
	public static int solution(int n, int k) {
		int answer = 0, i, j;
		String reverseNum = "";
		while(n > 0) {
			reverseNum += n % k;
			n /= k;
		}
		String num = "";
		for(int index = reverseNum.length() - 1; index >= 0; index--) {
			num += reverseNum.charAt(index);
		}
		for(i = 0; i < num.length(); i = j) {
			for(j = i + 1; j < num.length() && num.charAt(j) != '0'; j++);
			if(isPrime(Long.parseLong(num.substring(i, j))))
				answer++; 
		}
		return answer;
	}
	
	static String to_Knum(int n, int k) {
        String res = "";
        while(n > 0) {
            res = n % k + res;
            n /= k;
        }
        return res;
    }
	
	static boolean isPrime(long num) {
		if(num <= 1) return false;
		if(num == 2) return true;
		for(int n = 2; n <= Math.sqrt(num); n++) {
			if(num % n == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
//		int n = 437674;
//		int k = 3;
		int n = 110011;
		int k = 10;
		System.out.println(solution(n, k));
	}
}
