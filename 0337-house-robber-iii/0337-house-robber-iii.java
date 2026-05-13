/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> dp = new HashMap<>();
        return solve(root, dp);
    }

    public int solve(TreeNode root, HashMap<TreeNode, Integer> dp) {
        if (root == null)
            return 0;

        if (dp.containsKey(root))
            return dp.get(root);

        int pick = root.val;

        if (root.left != null) {
            pick += solve(root.left.left, dp);
            pick += solve(root.left.right, dp);
        }
        if (root.right != null) {
            pick += solve(root.right.left, dp);
            pick += solve(root.right.right, dp);
        }

        int notpick = solve(root.left, dp) + solve(root.right, dp);

        int result = Math.max(pick, notpick);
        dp.put(root, result);

        return result;

    }
}