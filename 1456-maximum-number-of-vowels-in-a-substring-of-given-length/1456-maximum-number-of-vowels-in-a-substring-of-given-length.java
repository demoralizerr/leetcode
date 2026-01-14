class Solution {
    public int maxVowels(String s, int k) {
        String vowels = "aeiou";
        String res = "";
        int maxCount = 0, count = 0;
        int left = 0;
        int right = 0;
        int n = s.length();
        while (right < n) {
            char ch = s.charAt(right);
            if (vowels.contains(String.valueOf(ch)))
                count++;
            res += ch;

            if (right - left + 1 == k) {
                //shrink the window
                maxCount = Math.max(maxCount, count);
                if (vowels.contains(String.valueOf(s.charAt(left)))) //removing vowel count if found during shrinking
                    count--;
                res.substring(left + 1);//removing left char for shrinking
                left++;
            }
            right++;
        }
        return maxCount;
    }
}