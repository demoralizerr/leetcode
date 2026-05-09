class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {
            int top = layer, bottom = m - layer - 1;
            int left = layer, right = n - layer - 1;

            List<Integer> temp = new ArrayList<>();

            // Extract elements in counter-clockwise order
            for (int j = left; j < right; j++)
                temp.add(grid[top][j]); // Top row
            for (int i = top; i < bottom; i++)
                temp.add(grid[i][right]); // Right column
            for (int j = right; j > left; j--)
                temp.add(grid[bottom][j]); // Bottom row
            for (int i = bottom; i > top; i--)
                temp.add(grid[i][left]); // Left column

            int len = temp.size();
            int netRotations = k % len;

            // Rotate the list
            Collections.rotate(temp, -netRotations); //negative for counter-clockwise

            //Put elements back into the grid
            int idx = 0;
            for (int j = left; j < right; j++)
                grid[top][j] = temp.get(idx++);
            for (int i = top; i < bottom; i++)
                grid[i][right] = temp.get(idx++);
            for (int j = right; j > left; j--)
                grid[bottom][j] = temp.get(idx++);
            for (int i = bottom; i > top; i--)
                grid[i][left] = temp.get(idx++);
        }
        return grid;
    }
}
