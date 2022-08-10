package programmers;

public class Level3_VanishingFootholder {
	int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, 1, 0, -1};
       
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, 0, board).count;
    }
    
    public Result dfs(int ax, int ay, int bx, int by, int aNum, int bNum, int[][] board){
        
        boolean win = false;
        int min = 5*5;
        int max = aNum + bNum;
        
        if(aNum == bNum && board[ax][ay] == 1){
        	for(int i = 0; i < 4; i++) {
        		int a_cx = ax + dx[i];
        		int a_cy = ay + dy[i];
        		if(a_cx >= 0 && a_cx < board.length && a_cy >= 0 && a_cy < board[0].length && board[a_cx][a_cy] == 1) {
        			board[ax][ay] = 0;
        			Result b_result = dfs(a_cx, a_cy, bx, by, aNum + 1, bNum, board);
        			win |= !b_result.win;
        			if(!b_result.win) {
        				min = Math.min(min, b_result.count);
        			} else {
        				max = Math.max(max, b_result.count);
        			}
        			board[ax][ay] = 1;
        		}
        	}
        } else if (aNum > bNum && board[bx][by] == 1){
        	for(int i = 0; i < 4; i++) {
        		int b_cx = bx + dx[i];
        		int b_cy = by + dy[i];
        		if(b_cx >= 0 && b_cx < board.length && b_cy >= 0 && b_cy < board[0].length && board[b_cx][b_cy] == 1) {
        			board[bx][by] = 0;
        			Result a_result = dfs(ax, ay, b_cx, b_cy, aNum, bNum + 1, board);
        			win |= !a_result.win;
        			if(!a_result.win) {
        				min = Math.min(min, a_result.count);
        			} else {
        				max = Math.max(max, a_result.count);
        			}
        			board[bx][by] = 1;
        		}
        	}
        }
        
        return new Result(win, win ? min : max);
    }
    
    static class Result{
        boolean win;
        int count;
        
        public Result(boolean win, int count){
            this.win = win;
            this.count = count;
        }
    }
}
