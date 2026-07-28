class Solution {
    public String smallestPalindrome(String s) {
        int len = s.length();
        StringBuilder res = new StringBuilder();
        char[] val = new char[len];
        if (len == 1)
            return s;

        int[] mp = new int[26];
        for (char ch : s.toCharArray()) {
            mp[ch - 'a']++;
        }

        int low = 0;
        int high = len - 1;
        while (low < high) { // Time Complexity 0(len*26) where len is given string length
            for (int i = 0; i < 26; i++) {
                int cnt = mp[i];
                if (cnt > 1) {
                    char ch = (char) (97 + i);
                    val[low] = ch;
                    val[high] = ch;
                    cnt = cnt - 2;
                    mp[ch - 'a'] = cnt;
                    low++;
                    high--;
                    break;
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            int cnt = mp[i];
            if (cnt == 1) {
                char ch = (char) (97 + i);
                val[low] = ch;
                break;
            }

        }

        for (char ch : val)
            res.append(ch);

        return res.toString();
    }
}