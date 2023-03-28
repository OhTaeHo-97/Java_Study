package programmers;

import java.util.ArrayList;

public class Level3_FillPuzzle {
	int maxX, minX, maxY, minY;
    ArrayList<Puzzle> blanks, puzzles;
    int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    public int solution(int[][] game_board, int[][] table) {
        makeBlankList(game_board);
        makePuzzleList(table);

        int answer = 0;
        boolean[] visited = new boolean[puzzles.size()];
        for(int blankIdx = 0; blankIdx < blanks.size(); blankIdx++) {
            for(int puzzleIdx = 0; puzzleIdx < puzzles.size(); puzzleIdx++) {
                if(!visited[puzzleIdx]) {
                    int count = matchPuzzleToBlank(blankIdx, puzzleIdx, visited);
                    answer += count;
                    if(count > 0) break;
                }
            }
        }

        return answer;
    }

    public int countMatching(int[][] blank, int[][] puzzle) {
        if (blank.length != puzzle.length || blank[0].length != puzzle[0].length) return 0;

        int count = 0;
        for (int row = 0; row < blank.length; row++) {
            for (int col = 0; col < blank[0].length; col++) {
                if (blank[row][col] != puzzle[row][col]) return 0;
                if (puzzle[row][col] == 1) count++;
            }
        }

        return count;
    }

    public int matchPuzzleToBlank(int blankIdx, int puzzleIdx, boolean[] visited) {
        int count = countMatching(blanks.get(blankIdx).puzzle, puzzles.get(puzzleIdx).puzzle);
        if(count != 0) {
            visited[puzzleIdx] = true;
            return count;
        }

        count = countMatching(blanks.get(blankIdx).puzzle, puzzles.get(puzzleIdx).rotate90());
        if(count != 0) {
            visited[puzzleIdx] = true;
            return count;
        }

        count = countMatching(blanks.get(blankIdx).puzzle, puzzles.get(puzzleIdx).rotate180());
        if(count != 0) {
            visited[puzzleIdx] = true;
            return count;
        }

        count = countMatching(blanks.get(blankIdx).puzzle, puzzles.get(puzzleIdx).rotate270());
        if(count != 0) {
            visited[puzzleIdx] = true;
            return count;
        }

        return 0;
    }

    public boolean isInMap(int x, int y, int[][] table) {
        if(x >= 0 && x < table.length && y >= 0 && y < table[0].length) return true;
        return false;
    }

    public void makePuzzleList(int[][] table) {
        boolean[][] visited = new boolean[table.length][table[0].length];
        ArrayList<int[]> list = new ArrayList<>();
        puzzles = new ArrayList<>();

        for(int row = 0; row < table.length; row++) {
            for(int col = 0; col < table[row].length; col++) {
                if(!visited[row][col] && table[row][col] == 1) {
                    list = new ArrayList<>();
                    list.add(new int[] {row, col});
                    maxX = minX = row;
                    maxY = minY = col;
                    visited[row][col] = true;
                    dfs(row, col, 1, visited, table, list);

                    Puzzle puzzle = new Puzzle();
                    puzzle.puzzle = new int[maxX - minX + 1][maxY - minY + 1];

                    for(int[] elem : list)
                        puzzle.puzzle[elem[0] - minX][elem[1] - minY] = 1;

                    puzzles.add(puzzle);
                }
            }
        }
    }

    public void makeBlankList(int[][] game_board) {
        boolean[][] visited = new boolean[game_board.length][game_board[0].length];
        ArrayList<int[]> list = new ArrayList<>();
        blanks = new ArrayList<>();

        for(int row = 0; row < game_board.length; row++) {
            for(int col = 0; col < game_board[row].length; col++) {
                if(!visited[row][col] && game_board[row][col] == 0) {
                    list = new ArrayList<>();
                    list.add(new int[] {row, col});
                    maxX = minX = row;
                    maxY = minY = col;
                    visited[row][col] = true;
                    dfs(row, col, 0, visited, game_board, list);

                    Puzzle puzzle = new Puzzle();
                    puzzle.puzzle = new int[maxX - minX + 1][maxY - minY + 1];

                    for(int[] elem : list)
                        puzzle.puzzle[elem[0] - minX][elem[1] - minY] = 1;

                    blanks.add(puzzle);
                }
            }
        }
    }

    public void dfs(int x, int y, int target, boolean[][] visited, int[][] arr, ArrayList<int[]> list) {
        for(int dir = 0; dir < dx.length; dir++) {
            int cx = x + dx[dir], cy = y + dy[dir];

            if(isInMap(cx, cy, arr)) {
                if(!visited[cx][cy] && arr[cx][cy] == target) {
                    visited[cx][cy] = true;
                    list.add(new int[] {cx, cy});
                    maxX = Math.max(maxX, cx);
                    minX = Math.min(minX, cx);
                    maxY = Math.max(maxY, cy);
                    minY = Math.min(minY, cy);
                    dfs(cx, cy, target, visited, arr, list);
                }
            }
        }
    }

    class Puzzle {
        int[][] puzzle;

        int[][] rotate90() {
            int[][] rotate = new int[puzzle[0].length][puzzle.length];

            for(int row = 0; row < puzzle.length; row++) {
                for(int col = 0; col < puzzle[0].length; col++) {
                    rotate[col][(puzzle.length - 1) - row] = puzzle[row][col];
                }
            }

            return rotate;
        }

        int[][] rotate180() {
            int[][] rotate = new int[puzzle.length][puzzle[0].length];

            for(int row = 0; row < puzzle.length; row++) {
                for(int col = 0; col < puzzle[0].length; col++) {
                    rotate[(puzzle.length - 1) - row][(puzzle[0].length - 1) - col] = puzzle[row][col];
                }
            }

            return rotate;
        }

        int[][] rotate270() {
            int[][] rotate = new int[puzzle[0].length][puzzle.length];

            for(int row = 0; row < puzzle.length; row++) {
                for(int col = 0; col < puzzle[0].length; col++) {
                    rotate[(puzzle[0].length - 1) - col][row] = puzzle[row][col];
                }
            }

            return rotate;
        }
    }

    public static void main(String[] args) {
        Level3_FillPuzzle l = new Level3_FillPuzzle();

        System.out.println(l.solution(new int[][] {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}}, new int[][] {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}}));
        System.out.println(l.solution(new int[][] {{0,0,0},{1,1,0},{1,1,1}}, new int[][] {{1,1,1},{1,0,0},{0,0,0}}));
    }
}
