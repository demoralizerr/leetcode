import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Optimize k if it is larger than the total number of elements
        k = k % totalElements;

        // Initialize the result grid with empty rows
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>(Collections.nCopies(n, 0)));
        }

        // Move each element directly to its final destination
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int old1D = r * n + c;
                int new1D = (old1D + k) % totalElements;
                
                int newRow = new1D / n;
                int newCol = new1D % n;
                
                result.get(newRow).set(newCol, grid[r][c]);
            }
        }

        return result;
    }
}
