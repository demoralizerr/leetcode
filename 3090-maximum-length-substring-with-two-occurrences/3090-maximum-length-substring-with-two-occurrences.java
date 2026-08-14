class Solution {
    public int maximumLengthSubstring(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        int len = s.length();
        int maxlen = 0;
        int left = 0;

        for (int right = 0; right < len; right++) {
            char rchar = s.charAt(right);
            counts.put(rchar, counts.getOrDefault(rchar, 0) + 1);

            while (counts.get(rchar) > 2) {
                char lchar = s.charAt(left);
                int count = counts.get(lchar);
                count--;
                if (count == 0)
                    counts.remove(lchar);
                else
                    counts.put(lchar, count);

                left++;
            }
            maxlen = Math.max(maxlen, right - left + 1);

        }
        return maxlen;
    }
}