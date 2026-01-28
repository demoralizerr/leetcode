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
    public int maxDepth(TreeNode root) {
       return maxdepth(root,0);
    }

    private int maxdepth(TreeNode root, int maxlen){
       if(root==null){
           return maxlen;
       }
        
        return Math.max(maxdepth(root.left, 1+maxlen), maxdepth(root.right,1+maxlen));

    }
}