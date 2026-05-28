class Solution {
    boolean[][] visited;
    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        int r = board.length;
        int c = board[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == word.charAt(0))
                    if (dfs(board, word, 0, i, j))
                        return true;
            }
        }
        return false;

    }

    public boolean dfs(char[][] board, String word, int index, int r, int c) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return false;

        if (board[r][c] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1)
            return true;

        char temp = board[r][c];
        board[r][c] = '#'; // mark visited
        boolean result = false;

        if (temp == word.charAt(index)) {
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];
                result = dfs(board, word, index + 1, nr, nc);
                if (result)
                    break;
            }
        }

        //backtrack
        board[r][c] = temp;

        return result;
    }
}