class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int left = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        int currlen = 0;
        int maxlen = 0;
        for (int right = 0; right < n; right++) {
            currlen++;
            mp.put(fruits[right], mp.getOrDefault(fruits[right], 0) + 1);

            while (mp.size() > 2) {
                //shrink from left
                int item = fruits[left];
                mp.put(item, mp.get(item) - 1);
                currlen--;
                if (mp.get(item) == 0)
                    mp.remove(item);
                left++;
            }

            if (mp.size() == 2)
                maxlen = Math.max(maxlen, currlen);
        }
        return Math.max(currlen, maxlen);
    }
}