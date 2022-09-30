package programmers;

public class Level2_RotateMatrixEdge {
	static int[][] map;
    static int[][] result;
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int num = 1;
        map = new int[rows][columns];
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < columns; c++) {
                map[r][c] = num;
                num++;
            }
        }
        for(int index = 0; index < queries.length; index++) {
            result = new int[rows][columns];
            int r1 = queries[index][0] - 1;
            int c1 = queries[index][1] - 1;
            int r2 = queries[index][2] - 1;
            int c2 = queries[index][3] - 1;
            int min = rotate(r1, c1, r2, c2);
            answer[index] = min;
        }
        return answer;
    }
    
    static int rotate(int r1, int c1, int r2, int c2) {
        for(int col = c1 + 1; col <= c2; col++)
            result[r1][col] = map[r1][col - 1];
        for(int row = r1 + 1; row <= r2; row++)
            result[row][c2] = map[row - 1][c2];
        for(int col = c2 - 1; col >= c1; col--)
            result[r2][col] = map[r2][col + 1];
        for(int row = r2 - 1; row >= r1; row--)
            result[row][c1] = map[row + 1][c1];
        int min = apply(r1, c1, r2, c2, result);
        return min;
    }
    
    static int apply(int r1, int c1, int r2, int c2, int[][] result) {
        int min = Integer.MAX_VALUE;
        for(int col = c1; col <= c2; col++) {
            map[r1][col] = result[r1][col];
            min = Math.min(min, map[r1][col]);
        }
        for(int row = r1; row <= r2; row++) {
            map[row][c2] = result[row][c2];
            min = Math.min(min, map[row][c2]);
        }
            
        for(int col = c2; col >= c1; col--) {
            map[r2][col] = result[r2][col];
            min = Math.min(min, map[r2][col]);
        }
        for(int row = r2; row > r1; row--) {
            map[row][c1] = result[row][c1];
            min = Math.min(min, map[row][c1]);
        }
        return min;
    }
    
    public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
//		int rows = 3;
//		int columns = 3;
//		int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
//		int rows = 100;
//		int columns = 97;
//		int[][] queries = {{1,1,100,97}};
		int[] answer = solution(rows, columns, queries);
		for(int a : answer) System.out.println(a);
	}
}
