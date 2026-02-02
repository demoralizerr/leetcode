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
    private int preorderIdx = 0;
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        //base case subarray size become 0
        if (left > right)
            return null;

        int rootval = preorder[preorderIdx];
        preorderIdx++;

        //find rootval index in inorder 
        int rootidx = inorderMap.get(rootval);

        TreeNode root = new TreeNode(rootval);
        root.left = build(preorder, left, rootidx - 1);
        root.right = build(preorder, rootidx + 1, right);

        return root;

    }
}