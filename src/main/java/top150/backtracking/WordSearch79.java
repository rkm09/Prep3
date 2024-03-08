package top150.backtracking;

public class WordSearch79 {
    private char[][] board;
    private int ROWS;
    private int COLS;

    public static void main(String[] args) {
        WordSearch79 ws = new WordSearch79();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(ws.exist(board, word));
    }
    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;
        this.board = board;
        for(int row = 0 ; row < ROWS ; row++) {
            for(int col = 0 ; col < COLS ; col++) {
                if(backtrack(row, col, word, 0))
                    return true;
            }
        }
        return false;
    }

//    backtracking; time: O(N.3^L), space: O(N) [N - number of cells in the board, L - length of the word to be matched]
    private boolean backtrack(int row, int col, String word, int index) {
//      check base case
        if(index >= word.length())
            return true;
//      check boundaries
        if(row < 0 || row == ROWS || col < 0 || col == COLS || board[row][col] != word.charAt(index))
            return false;
//      else mark the path before the next exploration
        board[row][col] = '#';
        boolean isValidPath = false;
        int[] rowOffset = {0, 1, 0, -1};
        int[] colOffset = {1, 0, -1, 0};
        for(int d = 0 ; d < 4 ; d++) {
            isValidPath = backtrack(row + rowOffset[d], col + colOffset[d], word, index + 1);
            if(isValidPath) break;
//      can also return true immediately(if an altered board state is fine) after isValidPath is found to be true [return false outside loop];
        }
//      clean up and return result
        board[row][col] = word.charAt(index);
        return isValidPath;
    }
}

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
Example 1:
Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
Output: true
Example 2:
Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
Output: true
Example 3:
Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCB"
Output: false
 
Constraints:
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 
Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
