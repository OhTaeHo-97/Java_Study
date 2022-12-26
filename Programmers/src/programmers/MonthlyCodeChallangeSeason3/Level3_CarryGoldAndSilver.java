package programmers.MonthlyCodeChallangeSeason3;

public class Level3_CarryGoldAndSilver {
	public static long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = binarySearch(a, b, g, s, w, t);
        return answer;
    }

    static long binarySearch(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int cityNum = g.length;
        // ((금의 양) + (은의 양) / (한 번에 옮길 수 있는 무게)) * (윪기는데 걸리는 시간 * 2)
        // 조건에서 금, 은의 양의 최대 = 10e9, 올길 수 있는 무게 최소값 = 1, 옮기는데 걸리는 시간의 최대 = 10e5
        long start = 0, end = (long)(10e9 * 2 * 10e5 * 2);
        long answer = end;
        while(start <= end) {
            long mid = (start + end) / 2;
            // gold : 주어진 시간 내에 옮길 수 있는 금의 양이 a 이상인지 체크하기 위함
            // silver : 주어진 시간 내에 옮길 수 있는 은의 양이 b 이상인지 체크하기 위함
            // sum : 주어진 시간 내에 gold만큼과 silver만큼을 같이 옮길 수 있는 것이 아니기 때문에
            //       주어진 시간 내에 (a + b)만큼의 광물양을 운반할 수 있는지 체크하기 위함
            int gold = 0, silver = 0, sum = 0;
            for(int idx = 0; idx < cityNum; idx++) {
                long move = mid / (t[idx] * 2);
                if(mid % (t[idx] * 2) >= t[idx]) move++;

                gold += Math.min(g[idx], move * w[idx]);
                silver += Math.min(s[idx], move * w[idx]);
                sum += Math.min(g[idx] + s[idx], move * w[idx]);
            }

            if(a <= gold && b <= silver && a + b <= sum) {
                end = mid - 1;
                answer = Math.min(answer, mid);
                continue;
            }
            start = mid + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        int a = 90, b = 500;
        int[] g = {70, 70, 0}, s = {0, 0, 500}, w = {100, 100, 2}, t = {4, 8, 1};
        System.out.println(solution(a, b, g, s, w, t));
    }
}
