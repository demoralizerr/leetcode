class Solution {
    public int characterReplacement(String s, int k) {
        int[] mp = new int[26];
        int n = s.length();
        int left = 0;
        int right = 0;
        int maxf = 0;
        int maxlen = 0;

        while (right < n) {
            char ch = s.charAt(right);
            mp[ch - 'A']++;

            // correct max frequency update
            maxf = Math.max(maxf, mp[ch - 'A']);

            int windowlen = right - left + 1;

            // shrink window if invalid
            if (windowlen - maxf > k) {
                char leftch = s.charAt(left);
                mp[leftch - 'A']--;
                left++;
            }

            // update max length
            maxlen = Math.max(maxlen, right - left + 1);
            right++;
        }

        return maxlen;
    }
}
