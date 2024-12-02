package programmers;

import java.util.ArrayList;
import java.util.List;

public class Level2_SelfTicTacToe {
    private static final int SIZE = 3;
    private int[][] boards;
    private List<int[]> oLoc;
    private List<int[]> xLoc;
    private boolean[] oVisited;
    private boolean[] xVisited;

    public int solution(String[] board) {
        oLoc = new ArrayList<>();
        xLoc = new ArrayList<>();
        makeBoard(board);
        if(!isPossibleCheckAmount()) {
            return 0;
        }

        oVisited = new boolean[oLoc.size()];
        xVisited = new boolean[xLoc.size()];
        if(backtracking(1, 0, new int[SIZE][SIZE])) {
            return 1;
        }
        return 0;
    }

    private void makeBoard(String[] board) {
        boards = new int[SIZE][SIZE];
        for(int row = 0; row < SIZE; row++) {
            String boardInfo = board[row];
            for(int col = 0; col < SIZE; col++) {
                if(boardInfo.charAt(col) == 'O') {
                    boards[row][col] = 1;
                    oLoc.add(new int[] {row, col});
                } else if(boardInfo.charAt(col) == 'X') {
                    boards[row][col] = -1;
                    xLoc.add(new int[] {row, col});
                } else {
                    boards[row][col]  = 0;
                }
            }
        }
    }

    private boolean isPossibleCheckAmount() {
        int oAmount = oLoc.size();
        int xAmount = xLoc.size();
        return oAmount == xAmount || oAmount == xAmount + 1;
    }

    private boolean backtracking(int turn, int count, int[][] status) {
        if(isCompleted(turn * -1, status)) {
            if(turn == 1) {
                for(boolean visited: oVisited) {
                    if(!visited) {
                        return false;
                    }
                }
                return true;
            } else if(turn == -1) {
                for(boolean visited: xVisited) {
                    if(!visited) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            if(count == oLoc.size() + xLoc.size()) {
                for(boolean visited: oVisited) {
                    if(!visited) {
                        return false;
                    }
                }
                for(boolean visited: xVisited) {
                    if(!visited) {
                        return false;
                    }
                }
                return true;
            }
        }

        if(turn == 1) {
            for(int idx = 0; idx < oLoc.size(); idx++) {
                if(oVisited[idx]) {
                    continue;
                }

                int[] loc = oLoc.get(idx);
                oVisited[idx] = true;
                status[loc[0]][loc[1]] = turn;
                if(backtracking(turn * -1, count + 1, status)) {
                    return true;
                }
                oVisited[idx] = false;
                status[loc[0]][loc[1]] = 0;
            }
        } else if (turn == -1) {
            for(int idx = 0; idx < xLoc.size(); idx++) {
                if(xVisited[idx]) {
                    continue;
                }

                int[] loc = xLoc.get(idx);
                xVisited[idx] = true;
                status[loc[0]][loc[1]] = turn;
                if(backtracking(turn * -1, count + 1, status)) {
                    return true;
                }
                xVisited[idx] = false;
                status[loc[0]][loc[1]] = 0;
            }
        }
        return false;
    }

    private boolean isCompleted(int turn, int[][] status) {
        if(isRowCompleted(turn, status) || isColCompleted(turn, status) || isDiagonalCompleted(turn, status)) {
            return true;
        }
        return false;
    }

    private boolean isRowCompleted(int code, int[][] status) {
        RowFor:
        for(int row = 0; row < SIZE; row++) {
            for(int col = 0; col < SIZE; col++) {
                if(status[row][col] != code) {
                    continue RowFor;
                }
            }
            return true;
        }
        return false;
    }

    private boolean isColCompleted(int code, int[][] status) {
        ColFor:
        for(int col = 0; col < SIZE; col++) {
            for(int row = 0; row < SIZE; row++) {
                if(status[row][col] != code) {
                    continue ColFor;
                }
            }
            return true;
        }
        return false;
    }

    private boolean isDiagonalCompleted(int code, int[][] status) {
        int[] startCol = new int[] {0, 2};
        DiagonalFor:
        for(int col: startCol) {
            int difference = col + 1 >= SIZE ? -1 : 1;
            int nextCol = col;
            for(int row = 0; row < SIZE; row++) {
                if(status[row][nextCol] != code) {
                    continue DiagonalFor;
                }
                nextCol += difference;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Level2_SelfTicTacToe l = new Level2_SelfTicTacToe();
        String[] board = {"O.X", ".O.", "..X"};
        System.out.println(l.solution(board));
    }
}
