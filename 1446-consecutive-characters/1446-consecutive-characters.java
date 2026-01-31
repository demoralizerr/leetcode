class Solution {
    public int maxPower(String s) {
        int[] freq = new int[26];
        int power = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            char ch1 = s.charAt(i + 1);
            freq[ch - 'a']++;
            if (ch != ch1) {
                power = Math.max(power, freq[ch - 'a']);
                freq[ch - 'a'] = 0;
            }
        }

        freq[s.charAt(s.length() - 1) - 'a']++;
        power = Math.max(power, freq[s.charAt(s.length() - 1) - 'a']);
        return power;
    }
}