class Solution {
    public int prefixConnected(String[] words, int k) {
        int group = 0;
        Map<String, Integer> mp = new HashMap<>();
        int n = words.length;
        for (String word : words) {
            if (word.length() < k)
                continue;
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < k; i++) {
                str.append(word.charAt(i));
            }
            String tm = str.toString();
            int cnt = 0;
            if (mp.containsKey(tm)) {
                cnt = mp.get(tm);
            }
            mp.put(tm, cnt + 1);

        }

        for (int value : mp.values())
            if (value > 1)
                group++;
        return group;
    }
}