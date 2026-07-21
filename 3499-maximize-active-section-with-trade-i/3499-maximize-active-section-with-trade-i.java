class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int activecount = 0;
        int n = s.length();
        for (char ch : s.toCharArray()) {
            if (ch - '0' == 1)
                activecount++;
        }
        List<Integer> inactive = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int num = s.charAt(i) - '0';
            if (num == 0) {
                int start = i;
                while (i < n && s.charAt(i) - '0' == 0)
                    i++;
                inactive.add(i - start);
            } else
                i++;
        }

        int maxpairsum = 0;
        for (int idx = 1; idx < inactive.size(); idx++) {
            maxpairsum = Math.max(maxpairsum, inactive.get(idx) + inactive.get(idx - 1));
        }

        return maxpairsum + activecount;
    }
}