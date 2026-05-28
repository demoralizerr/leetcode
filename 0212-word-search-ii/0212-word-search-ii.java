class TrieNode {
    TrieNode[] children;
    String word = null;

    public TrieNode() {
        this.children = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null)
                curr.children[idx] = new TrieNode();

            curr = curr.children[idx];
        }

        curr.word = word;
    }
}

class Solution {

    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    Trie trie = new Trie();

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();
        //insert in Tries
        for (String word : words) {
            trie.insert(word);
        }

        int r = board.length;
        int c = board[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                dfs(board, i, j, trie.root, set);
            }
        }

        return new ArrayList<>(set);

    }

    public void dfs(char[][] board, int r, int c, TrieNode root, Set<String> set) {
        // Base Case: Check boundaries
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return;
        }

        char ch = board[r][c];

        if (ch == '#')
            return;

        int idx = ch - 'a';

        //moving to next letter
        if (root.children[idx] == null) {
            return;
        }
        root = root.children[idx];

        //if a word completed at current node, store it 
        if (root.word != null) {
            set.add(root.word);
        }

        // Mark as visited using your clean in-place trick
        board[r][c] = '#';

        // Explore all 4 directions
        for (int[] d : dir) {
            int nr = r + d[0];
            int nc = c + d[1];
            dfs(board, nr, nc, root, set);
        }

        // Backtrack: Restore the character for other potential paths
        board[r][c] = ch;
    }
}