class Solution {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();

        int n = words.length;
        Arrays.sort(words);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {

                        // ensure all words are distinct
                        if (i == j || i == k || i == l ||
                            j == k || j == l || k == l)
                            continue;

                        String top = words[i];
                        String left = words[j];
                        String right = words[k];
                        String bottom = words[l];

                        if (isValid(top, left, right, bottom)) {
                            addResult(top, left, right, bottom, result);
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean isValid(String top, String left, String right, String bottom) {
        return top.charAt(0) == left.charAt(0)
            && top.charAt(3) == right.charAt(0)
            && bottom.charAt(0) == left.charAt(3)
            && bottom.charAt(3) == right.charAt(3);
    }

    public void addResult(
        String top, String left, String right, String bottom,
        List<List<String>> result
    ) {
        List<String> res = new ArrayList<>();
        res.add(top);
        res.add(left);
        res.add(right);
        res.add(bottom);
        result.add(res);
    }
}
