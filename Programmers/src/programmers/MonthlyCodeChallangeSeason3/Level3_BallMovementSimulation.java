package programmers.MonthlyCodeChallangeSeason3;

public class Level3_BallMovementSimulation {
	public long solution(int n, int m, int x, int y, int[][] queries) {
        long lx = x, hx = x, ly = y, hy = y;

        for(int idx = queries.length - 1; idx >= 0; idx--) {
            int dir = queries[idx][0], amount = queries[idx][1];

            if(dir == 0) {
                if(ly != 0) ly += amount;
                hy = Math.min(m - 1, hy + amount);
            } else if(dir == 1) {
                if(hy != m - 1) hy -= amount;
                ly = Math.max(0, ly - amount);
            } else if(dir == 2) {
                if(lx != 0) lx += amount;
                hx = Math.min(n - 1, hx + amount);
            } else {
                if(hx != n - 1) hx -= amount;
                lx = Math.max(0, lx - amount);
            }

            if(hx < 0 || lx >= n || hy < 0 || ly >= m) return 0;
        }

        return (hx - lx + 1) * (hy - ly + 1);
    }

    public static void main(String[] args) {
    	Level3_BallMovementSimulation l = new Level3_BallMovementSimulation();
        int[][] queries = new int[][] {{2,1},{0,1},{1,1},{0,1},{2,1}};
        System.out.println(l.solution(2, 2, 0, 0, queries));

        queries = new int[][] {{3,1},{2,2},{1,1},{2,3},{0,1},{2,1}};
        System.out.println(l.solution(2, 5, 0, 1, queries));

        queries = new int[][] {{0,100001},{2,100001}};
        System.out.println(l.solution(1000, 1000, 1, 1, queries));

        queries = new int[][] {{0,100001},{2,100001}};
        System.out.println(l.solution(1000, 1000, 0, 0, queries));

        queries = new int[][] {{0,1_000_000_000},{2,1_000_000_000}};
        System.out.println(l.solution(1_000_000_000, 1_000_000_000, 0, 0, queries));
    }
}
