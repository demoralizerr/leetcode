class Solution {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        int[] arr = new int[101];
        for (int bulb : bulbs) {
            if (arr[bulb] == 0)
                arr[bulb] = 1; //on
            else
                arr[bulb] = 0; //off
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            if (arr[i] == 1)
                res.add(i);
        }
        return res;
    }
}