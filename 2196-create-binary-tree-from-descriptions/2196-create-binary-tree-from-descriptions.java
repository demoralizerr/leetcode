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
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> mp = new HashMap<>();
        Set<Integer> childs = new HashSet<>();
        TreeNode root = null;

        for (int[] desc : descriptions) {
            int parent = desc[0];
            int child = desc[1];
            int leftorright = desc[2];
            TreeNode parentnode = mp.computeIfAbsent(parent, k -> new TreeNode(k));
            TreeNode childnode = mp.computeIfAbsent(child, k -> new TreeNode(k));

            if (leftorright == 1)
                parentnode.left = childnode;
            else
                parentnode.right = childnode;

            childs.add(child);

        }
        for (int[] desc : descriptions) {
            int parent = desc[0];
            if (!childs.contains(parent))
                root = mp.get(parent);
        }

        return root;
    }
}