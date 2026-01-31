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
    private int count = 0;
    public int goodNodes(TreeNode root) {
        if (root == null)
            return 0;
        int currmax = root.val;
        findNode(root,currmax);
        return count;

    }

    private void findNode(TreeNode root,int currmax) {
        if (root == null)
            return;

        if (root.val >= currmax)
            count++;
        currmax = Math.max(root.val,currmax);

        findNode(root.left,currmax);
        findNode(root.right,currmax);

    }

}