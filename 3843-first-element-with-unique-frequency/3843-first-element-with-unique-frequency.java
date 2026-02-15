class Solution {
    public int firstUniqueFreq(int[] nums) {
        Map<Integer, Integer> freqMap = new LinkedHashMap<>();
        for (int num : nums) {
            int cnt = 0;
            if (freqMap.containsKey(num))
                cnt = freqMap.get(num);
            freqMap.put(num, cnt + 1);
        }

        Map<Integer,Integer> countOfFreq = new HashMap<>();
        for(int f: freqMap.values()){
            countOfFreq.put(f, countOfFreq.getOrDefault(f, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int frequency = entry.getValue();
            if (countOfFreq.getOrDefault(frequency,0) == 1) {
                return entry.getKey(); 
            }
        }
        return -1;
    }
}