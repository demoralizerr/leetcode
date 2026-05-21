class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[10];
    }

    TrieNode root = new TrieNode();

    public void insertInTrie(String num) {
        TrieNode curr = root;
        for (char ch : num.toCharArray()) {
            int idx = ch - '0';
            if (curr.children[idx] == null)
                curr.children[idx] = new TrieNode();

            curr = curr.children[idx];
        }
    }

    public int findPrefix(int num) {
        String str = String.valueOf(num);
        TrieNode curr = root;
        int len = 0;
        for (char ch : str.toCharArray()) {
            int idx = ch - '0';
            if (curr.children[idx] == null)
                break;

            curr = curr.children[idx];
            len++;
        }
        return len;

    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int max = 0;

        for (int arr : arr1) {
            insertInTrie(String.valueOf(arr));
        }

        for (int arr : arr2) {
            int currlen = findPrefix(arr);
            max = Math.max(max, currlen);
        }

        return max;
    }
}