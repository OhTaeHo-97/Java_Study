package programmers;

public class Level4_Stealing {
	public int solution(int[] money) {
        int[] withOne = new int[money.length], withoutOne = new int[money.length];
        withOne[0] = withOne[1] = money[0];
        withoutOne[1] = money[1];

        for(int idx = 2; idx < money.length; idx++) {
            withOne[idx] = Math.max(withOne[idx - 2] + money[idx], withOne[idx - 1]);
            withoutOne[idx] = Math.max(withoutOne[idx - 2] + money[idx], withoutOne[idx - 1]);
        }

        return Math.max(withOne[money.length - 2], withoutOne[money.length - 1]);
    }

    public static void main(String[] args) {
        Level4_Stealing l = new Level4_Stealing();
        System.out.println(l.solution(new int[] {1, 2, 3, 1}));
    }
}
