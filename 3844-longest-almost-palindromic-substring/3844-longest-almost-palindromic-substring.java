class Solution {
    public int almostPalindromic(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            maxLen = Math.max(maxLen, getBest(s, i, i));
            maxLen = Math.max(maxLen, getBest(s, i, i + 1));
        }
        return maxLen;
    }

    private int getBest(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        int len = r - l - 1;

        // Option A: Skip the left character
        int optLeft = expand(s, l - 1, r);
        // Option B: Skip the right character
        int optRight = expand(s, l, r + 1);

        return Math.max(len, Math.max(optLeft, optRight));
    }

    private int expand(String s, int l, int r) {
        if (l < 0 && r >= s.length())
            return 0;

        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
