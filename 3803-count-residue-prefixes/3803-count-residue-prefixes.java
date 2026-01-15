class Solution {
    public int residuePrefixes(String s) {
        int right = 0;
        int left = 0;
        int n = s.length();
        int residue = 0;
        Set<Character> mp = new HashSet<>();
        while (right < n) {
            char ch = s.charAt(right);
            mp.add(ch);
            if (mp.size() == (right - left + 1) % 3)
                residue++;
            right++;
        }
        return residue;
    }
}