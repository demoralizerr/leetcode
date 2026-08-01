class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        return solve(nums, 1, 0, n - 1, 0, 0);
    }

    public boolean solve(int[] nums, int turn, int low, int high, long p1score, long p2score) {
        if (low > high) {
            return p1score >= p2score;
        }

        if (turn % 2 != 0) { //player 1 turn
            boolean left = solve(nums, turn + 1, low + 1, high, p1score + nums[low], p2score);
            boolean right = solve(nums, turn + 1, low, high - 1, p1score + nums[high], p2score);
            return left || right;
        } else { //player 2 turn
            boolean left = solve(nums, turn + 1, low + 1, high, p1score, p2score + nums[low]);
            boolean right = solve(nums, turn + 1, low, high - 1, p1score, p2score + nums[high]);
            return left && right;
        }

    }
}