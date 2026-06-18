class Solution {
    public double angleClock(int hour, int minutes) {

        double hourhandpos = (30 * hour);
        double minhandpos = 5.5 * minutes;

        double diff = Math.abs(hourhandpos - minhandpos);

        return diff <= 180 ? diff : 360 - diff;
    }
}