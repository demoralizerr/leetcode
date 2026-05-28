class Solution {
    //Define Trie Node structure
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int bestIndex = -1; // Stores the ideal index for this suffix path
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();

        // Find the absolute default index (shortest word, smallest index) 
        // in case a query shares 0 common suffix characters.
        int defaultBestIndex = 0;
        for (int i = 1; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[defaultBestIndex].length()) {
                defaultBestIndex = i;
            }
        }
        root.bestIndex = defaultBestIndex;

        // 2. Build the Trie with reversed words
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            TrieNode curr = root;

            // Traverse the string backward
            for (int j = word.length() - 1; j >= 0; j--) {
                int charIdx = word.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                }
                curr = curr.children[charIdx];

                // If this node is unassigned, or if the current word is strictly shorter
                if (curr.bestIndex == -1 || word.length() < wordsContainer[curr.bestIndex].length()) {
                    curr.bestIndex = i;
                }
            }
        }

        // 3. Process Queries
        int[] ans = new int[wordsQuery.length];
        for (int q = 0; q < wordsQuery.length; q++) {
            String query = wordsQuery[q];
            TrieNode curr = root;
            int lastValidIndex = root.bestIndex;

            // Search backward matching suffix paths
            for (int j = query.length() - 1; j >= 0; j--) {
                int charIdx = query.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    break; // Suffix chain broke, stop searching
                }
                curr = curr.children[charIdx];
                lastValidIndex = curr.bestIndex;
            }
            ans[q] = lastValidIndex;
        }

        return ans;
    }
}
