class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder res = new StringBuilder();

        for (String word : words) {
            int sum = 0;
            for (char ch : word.toCharArray()) {
                sum += weights[ch - 'a'];
            }
            char ch = (char) ('z' - sum%26);
            res.append(ch);
        }
        return res.toString();
    }
}