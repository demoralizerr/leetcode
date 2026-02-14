class Solution {
    private boolean[][] visited;
    private int rows;
    private int cols;
    private int color;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        this.rows = image.length;
        this.cols = image[0].length;
        this.visited = new boolean[rows][cols];
        this.color = color;

        fill(image, sr, sc, image[sr][sc]);
        return image;

    }

    private void fill(int[][] image, int row, int col, int oldColor) {
        if (row >= rows || col >= cols || row < 0 || col < 0 || image[row][col] != oldColor)
            return;

        //already visited
        if (visited[row][col])
            return;

        visited[row][col] = true;
        //fill the color
        image[row][col] = color;

        fill(image, row + 1, col, oldColor);
        fill(image, row - 1, col, oldColor);
        fill(image, row, col + 1, oldColor);
        fill(image, row, col - 1, oldColor);

    }
}