class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder res = new StringBuilder();
        char[] alpha = new char[26];
        
        int idx = 0;
        for (int i = 122; i >= 97; i--) {
            alpha[idx++] = (char) i;
        }

        for (String word : words) {
            int sum = 0;
            for (char ch : word.toCharArray()) {
                sum += weights[ch - 'a'];

            }
            res.append(alpha[sum % 26]);
        }
        return res.toString();
    }
}