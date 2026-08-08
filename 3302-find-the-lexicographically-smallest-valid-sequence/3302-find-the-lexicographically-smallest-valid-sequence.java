class Solution {
    public int[] validSequence(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[] suffixmatch = new int[len1];
        int[] seq = new int[len2];
        boolean canChange = true;
        int idx = 0;
        int i = len2 - 1;
        int matched = 0;
        for (int j = len1 - 1; j >= 0; j--) {
            if (i >= 0 && word1.charAt(j) == word2.charAt(i)) {
                matched++;
                i--;
            }
            suffixmatch[j] = matched;
        }

        int k = 0;
        int j = 0;
        while (k < len1 && j < len2) {
            if (word1.charAt(k) == word2.charAt(j)) {
                seq[idx++] = k;
                j++;
            } else if (canChange && k + 1 < len1 && suffixmatch[k + 1] >= len2 - j - 1) {
                seq[idx++] = k;
                j++;
                canChange = false;
            }

            k++;
        }

        if (j == len2)
            return seq;

        return new int[0];
    }
}