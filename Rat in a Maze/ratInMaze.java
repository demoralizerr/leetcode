/*
Submissions - https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
Start DFS from (0,0) only if start and end cells are open (maze[0][0] and maze[n-1][n-1] ≠ 0).
Use a shared StringBuilder path to track moves (D, L, R, U).
Base case: if (i,j) is bottom-right (n-1,n-1), add path.toString() to result.
Check invalid moves: out-of-bounds, blocked cell (0), or already visited (-1).
Mark cell as visited (maze[i][j] = -1) before recursion; restore after recursion (maze[i][j] = 1).
Explore four directions in lexicographical order: D → L → R → U.
For each direction: append move, recurse, then delete last char (backtracking).
StringBuilder.toString() creates a copy for result; no need to reset path in base case.
Avoid sorting by choosing DFS directions in lexicographical order.
Key principle: append → recurse → delete for every move.
*/

class Solution {
    public ArrayList<String> ratInMaze(int[][] maze) {
        ArrayList<String> result = new ArrayList<>();
        int n = maze.length;
        if (maze[0][0] == 0 || maze[n-1][n-1] == 0) return result;
        dfs(0, 0, maze, result, new StringBuilder());
        return result;
    }
    
    char[] dir = {'D','L','R','U'};
    int[] di = {1, 0, 0, -1};
    int[] dj = {0, -1, 1, 0};

    public void dfs(int i, int j, int[][] maze, List<String> result, StringBuilder path) {
        if (i < 0 || j < 0 || i >= maze.length || j >= maze[0].length
            || maze[i][j] == 0 || maze[i][j] == -1) {
            return;
        }

        if (i == maze.length - 1 && j == maze[0].length - 1) {
            result.add(path.toString());
            return;
        }

        maze[i][j] = -1;

        for (int k = 0; k < 4; k++) {
            path.append(dir[k]);
            dfs(i + di[k], j + dj[k], maze, result, path);
            path.deleteCharAt(path.length() - 1);
        }

        maze[i][j] = 1;
    }
}
