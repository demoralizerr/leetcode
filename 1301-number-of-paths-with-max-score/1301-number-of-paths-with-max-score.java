import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;
        
        // dp[r][c][0] stores the max score to reach cell (r, c) from 'S'
        // dp[r][c][1] stores the number of ways to achieve this max score
        int[][][] dp = new int[n][n][2];
        
        // Base case: Starting point at the bottom right 'S'
        dp[n - 1][n - 1][1] = 1; 
        
        // The three directions we can arrive from:
        // Down (r+1, c), Right (r, c+1), Down-Right (r+1, c+1)
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
        
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                // Skip the starting point and any obstacles
                if ((r == n - 1 && c == n - 1) || board.get(r).charAt(c) == 'X') {
                    continue;
                }
                
                int maxScore = -1;
                int paths = 0;
                
                // Check all possible previous cells we could have moved from
                for (int[] dir : dirs) {
                    int prevR = r + dir[0];
                    int prevC = c + dir[1];
                    
                    // If the previous cell is within bounds and actually reachable
                    if (prevR < n && prevC < n && dp[prevR][prevC][1] > 0) {
                        int prevScore = dp[prevR][prevC][0];
                        
                        if (prevScore > maxScore) {
                            // Found a strictly better score, overwrite paths
                            maxScore = prevScore;
                            paths = dp[prevR][prevC][1];
                        } else if (prevScore == maxScore) {
                            // Found an equal score, accumulate paths (with modulo)
                            paths = (paths + dp[prevR][prevC][1]) % MOD;
                        }
                    }
                }
                
                // If the current cell is reachable from at least one valid path
                if (paths > 0) {
                    int val = 0;
                    // Treat 'E' at (0,0) as a 0 value, otherwise extract digit
                    if (r != 0 || c != 0) { 
                        val = board.get(r).charAt(c) - '0';
                    }
                    
                    dp[r][c][0] = maxScore + val;
                    dp[r][c][1] = paths;
                }
            }
        }
        
        // If the end point 'E' (0, 0) is completely unreachable
        if (dp[0][0][1] == 0) {
            return new int[]{0, 0};
        }
        
        return new int[]{dp[0][0][0], dp[0][0][1]};
    }
}