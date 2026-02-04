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

    class Pair {
        int rob;
        int notrob;

        Pair(int rob, int notrob) {
            this.rob = rob;
            this.notrob = notrob;
        }
    }

    public int rob(TreeNode root) {
        Pair res = robber(root);
        return Math.max(res.rob, res.notrob);
    }

    private Pair robber(TreeNode root) {
        if (root == null)
            return new Pair(0, 0); // base case

        Pair lpair = robber(root.left);
        Pair rpair = robber(root.right);

        int maxpick = root.val + lpair.notrob + rpair.notrob;
        int maxnotpick = Math.max(lpair.rob, lpair.notrob) + Math.max(rpair.rob, rpair.notrob);

        return new Pair(maxpick, maxnotpick);

    }
}