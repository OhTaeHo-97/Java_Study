package programmers.kakaoBlindRecruitment2023;

public class Level3_ExpressableBinaryTree {
	static final long SIZE = (long)10e15;
    static int maxLen, maxExponent;
    public static int[] solution(long[] numbers) {
        findMaxSize();
        int[] answer = new int[numbers.length];
        for(int idx = 0; idx < numbers.length; idx++) {
            String binary = makeBinary(numbers[idx]);
            boolean[] isOne = new boolean[binary.length()];
            findOnePlace(binary, isOne);
            if(isBinaryTree(binary, isOne)) answer[idx] = 1;
            else answer[idx] = 0;
        }
        return answer;
    }

    static boolean isBinaryTree(String binary, boolean[] isOne) {
        if(binary.length() == 1) return true;
        int center = binary.length() / 2;
        if(binary.charAt(center) == '0') {
            boolean flag = true;
            for(int idx = 0; idx < binary.length(); idx++) {
                if(center == idx) continue;
                if(binary.charAt(idx) == '1') {
                    flag = false;
                    break;
                }
            }
            if(!flag) return false;
        }
        String left = binary.substring(0, center);
        String right = binary.substring(center + 1);
        boolean leftTree = isBinaryTree(left, isOne);
        if(!leftTree) return false;
        boolean rightTree = isBinaryTree(right, isOne);
        if(!rightTree) return false;
        return true;
    }

    static void findOnePlace(String binary, boolean[] isOne) {
        for(int idx = 0; idx < binary.length(); idx++) {
            if(binary.charAt(idx) == '1') isOne[idx] = true;
        }
    }

    static String makeBinary(long number) {
        long copy = number;
        StringBuilder sb = new StringBuilder();
        while(number > 0) {
            long remainder = number % 2;
            sb.insert(0, remainder);
            number /= 2;
        }
        int len = 0;
        for(int exponent = 0; len <= maxLen; exponent++) {
            len += (int)Math.pow(2, exponent);
            if(sb.length() < len) {
                int sbLen = sb.length();
                for(int add = 0; add < len - sbLen; add++) {
                    sb.insert(0, 0);
                }
                break;
            } else if(sb.length() == len) {
                break;
            }
        }
        return sb.toString();
    }

    static void findMaxSize() {
        maxLen = (int)Math.log(SIZE) + 1;
        maxExponent = (int)Math.log(maxLen) + 1;
    }

    public static void main(String[] args) {
        long[] numbers = {2, 5, 106, 10, 6, 8, 63, 111, 95, (long)10e15};
        int[] answer = solution(numbers);
        for(int a : answer) System.out.println(a);
    }
}
