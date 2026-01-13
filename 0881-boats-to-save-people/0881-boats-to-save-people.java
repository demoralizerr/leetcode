class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);

        int l = 0, r = n - 1;
        int minboats = 0;

        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                l++;        // light person boards
                r--;        // heavy person boards
            } else {
                r--;        // heavy person goes alone
            }
            minboats++;     // one boat used
        }
        return minboats;
    }
}
