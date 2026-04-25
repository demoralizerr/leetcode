class Solution {
    public List<Integer> findGoodIntegers(int n) {
        Map<Integer, Integer> count = new HashMap<>();
        int limit = (int) Math.cbrt(n);

        for (int a = 1; a <= limit; a++) {
            for (int b = a; b <= limit; b++) {
                long res = (long)a*a*a + (long)b*b*b;
                if (res > n) break;

                int val = (int) res;
                count.put(val, count.getOrDefault(val, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() >= 2) {
                ans.add(entry.getKey());
            }
        }

        Collections.sort(ans);
        return ans;
    }
}