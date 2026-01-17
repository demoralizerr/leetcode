class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 > n2)
            return false;

        int[] h1 = new int[26];
        int[] h2 = new int[26];

        for (int i = 0; i < n1; i++) {
            char ch = s1.charAt(i);
            h1[ch - 'a']++;
        }

        for (int i = 0; i < n1; i++) {
            char ch = s2.charAt(i);
            h2[ch - 'a']++;
        }

        int left = 0;
        int right = n1;
        while (right < n2) {

            //if freq of s1 char matches s2 char return true
            if (matches(h1, h2, s1))
                return true;

            //shrink the window
            h2[s2.charAt(right) - 'a']++;
            h2[s2.charAt(left) - 'a']--;
            left++;
            right++;
        }

        if (matches(h1, h2, s1))
            return true;
        return false;

    }

    private boolean matches(int[] h1, int h2[], String s1) {
        boolean found = true;
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            if (h1[ch1 - 'a'] != h2[ch1 - 'a']) {
                found = false;
                break;
            }
        }
        return found;
    }
}