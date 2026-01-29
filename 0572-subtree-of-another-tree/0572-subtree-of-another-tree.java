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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null)
            return false; // If root is null, there's no tree to search in

        if (isIdentical(root, subRoot))
            return true; //if both identical with current root node t and s 

        //otherwise check if both are identical starting root.left node or from root.right node
        if (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot))
            return true;
         //otherwise no match found    
        return false;
    }

    private boolean isIdentical(TreeNode t, TreeNode s) {
        if (t == null && s == null)
            return true; //valid case

        if (t == null || s == null)
            return false; //invalid case

        if (t.val != s.val)
            return false; //invalid case

        return isIdentical(t.left, s.left) && isIdentical(t.right, s.right);
    }
}