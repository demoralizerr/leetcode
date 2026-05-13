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
        int[] bottomUpState = new int[2];
        bottomUpState = robTabulation(root);
        return Math.max(bottomUpState[0], bottomUpState[1]);//[notpicked, picked]
    }

    public int[] robTabulation(TreeNode root) {

        if (root == null)
            return new int[] { 0, 0 };
        int[] left = robTabulation(root.left);
        int[] right = robTabulation(root.right);

        int[] currdp = new int[2];

        //notpicked state
        currdp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        //picked state
        currdp[1] = root.val + left[0] + right[0];

        return currdp;

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