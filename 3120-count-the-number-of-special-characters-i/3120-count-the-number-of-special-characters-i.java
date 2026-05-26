class Solution {
    public int numberOfSpecialChars(String word) {
        int count = 0;
        Map<Character, Integer> mp = new HashMap<>();

        for (char ch : word.toCharArray()) {
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }

        for (int ascii = 97; ascii <= 122; ascii++) {
            char lower = (char) (ascii);
            if (mp.containsKey(lower) && mp.containsKey((char) (lower - 32))) {
                count++;
            }
        }
        return count;
    }
}