class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // We only need to swap until the middle row of the submatrix
        for (int i = 0; i < k / 2; i++) {
            // Row indices to swap
            int rowAbove = x + i;
            int rowBelow = x + k - 1 - i;
            
            // Swap all k elements in these rows within the submatrix column range
            for (int j = 0; j < k; j++) {
                int currentCol = y + j;
                
                int temp = grid[rowAbove][currentCol];
                grid[rowAbove][currentCol] = grid[rowBelow][currentCol];
                grid[rowBelow][currentCol] = temp;
            }
        }
        
        return grid;
    }
}