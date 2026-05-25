class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxlen = 0;
        int currlen = 0;

        int left = 0;
        int n = s.length();
        for (int right = 0; right < n; right++) {
            currlen++;
            char ch = s.charAt(right);

            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
                currlen--;
            }


            set.add(ch);
            maxlen = Math.max(maxlen, currlen);
        }
        return maxlen;
    }
}