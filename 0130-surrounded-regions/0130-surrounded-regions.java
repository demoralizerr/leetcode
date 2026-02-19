class Solution {
    private char[][] board;
    private int rows;
    private int cols;

    public void solve(char[][] board) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;

        //marking unsurrounded regions by marking connected components of border 
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == 'O')
                dfs(0, c);
            if (board[rows - 1][c] == 'O')
                dfs(rows - 1, c);
        }

        for (int r = 0; r < rows; r++) {
            if (board[r][0] == 'O')
                dfs(r, 0);
            if (board[r][cols - 1] == 'O')
                dfs(r, cols - 1);
        }

        // traverse and find surrounded regions and mark it to 'X'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs(int row, int col) {
        if(row<0 || col<0 || row==rows || col==cols || board[row][col]!='O')
          return;

          board[row][col] = '#';
          dfs(row+1,col);
          dfs(row-1,col);
          dfs(row,col+1);
          dfs(row,col-1);
    }
}