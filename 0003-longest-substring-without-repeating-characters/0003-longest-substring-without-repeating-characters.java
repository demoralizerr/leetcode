class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int left = 0;
        int maxlen = 0;
        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            //shrink the window
            if (set.contains(ch)) {
                //calculate the maxlen possible till now
                maxlen = Math.max(maxlen, right - left);
                //shrink window from left till duplicate char is removed from set
                while (set.contains(ch)) {
                    set.remove(s.charAt(left++));
                }
            }
            set.add(ch);
        }
        maxlen= Math.max(maxlen,n-left);
        return maxlen;
    }
}