class Solution {
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int ROWS = board.length, COLS = board[0].length;
        int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 },
                { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

        for (int[] dir : direction) {
            if (isLegal(rMove, cMove, board, color, dir))
                return true;
        }
        return false;
    }

    private boolean isLegal(int r, int c, char[][] board, char color, int[] dir) {
        int ROWS = 8;
        int COLS = 8;
        board[r][c] = color;
        r = r + dir[0];
        c = c + dir[1];
        int length = 1;

        while (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
            length++;
            if (board[r][c] == '.')
                return false;
            if (board[r][c] == color)
                return length >= 3;

            r = r + dir[0];
            c = c + dir[1];
        }
        return false;
    }
}