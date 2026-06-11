# Interval Patterns Cheat Sheet

## 1. Merge Intervals

### When to think about it

* Combining overlapping ranges
* Condensing intervals into larger groups

### Key Idea

1. Sort intervals by start time.
2. Compare current interval with previous merged interval.
3. If they overlap, merge them.
4. Otherwise start a new interval.

### Example

Input:

[1,3], [2,6], [8,10], [15,18]

Output:

[1,6], [8,10], [15,18]

### Overlap Condition

For sorted intervals:

[a,b], [c,d]

They overlap if:

c <= b

### Template

Sort by start → Merge overlaps.

---

## 2. Insert Interval

### When to think about it

* A new interval is added to an existing list.
* Need final merged intervals.

### Key Idea

Often easiest to:

1. Add the new interval.
2. Sort all intervals.
3. Run normal merge intervals.

### Example

Intervals:

[1,3], [6,9]

Insert:

[2,5]

Output:

[1,5], [6,9]

### Template

Insert → Sort → Merge.

---

## 3. Detect Overlap (Meeting Rooms)

### When to think about it

* Can all meetings be attended?
* Does any overlap exist?

### Key Idea

Sort by start time.

Check neighboring intervals.

### Example

[0,30], [5,10], [15,20]

Since:

5 < 30

Overlap exists.

Answer = False

### Template

Sort by start → Check adjacent intervals.

---

## 4. Count Merged Groups

### When to think about it

* Need number of disconnected interval groups.

### Example

[1,5], [2,3], [7,9], [8,10]

Merged:

[1,5], [7,10]

Answer = 2 groups

### Template

Merge intervals → Count resulting intervals.

---

## 5. Remove Covered Intervals

### When to think about it

* One interval completely contains another.

### Example

[1,10]
[2,5]
[3,4]

[2,5] and [3,4] are covered by [1,10].

Answer = 1 interval remains.

### Sorting Trick

Sort by:

* Start ascending
* End descending

Why?

Ensures larger intervals appear first.

### Template

Sort(start ↑, end ↓) → Detect covered intervals.

---

## 6. Merge Consecutive Ranges

### When to think about it

* Adjacent intervals should also merge.

### Example

[1,3]
[4,6]

Normal Merge:

[1,3], [4,6]

Merge Adjacent:

[1,6]

### Condition

Normal:

nextStart <= currEnd

Merge touching intervals:

nextStart <= currEnd + 1

### Template

Adjust overlap condition based on problem statement.

---

## 7. Interval Scheduling (Greedy)

### When to think about it

* Maximum non-overlapping intervals.
* Minimum intervals to remove.

### Example

[1,2]
[2,3]
[3,4]
[1,3]

Best selection:

[1,2], [2,3], [3,4]

Answer = 3 intervals

### Key Idea

Sort by END time, not START time.

Choosing the earliest finishing interval leaves maximum room for future intervals.

### Template

Sort by end → Greedily select intervals.

---

## 8. Sweep Line Pattern

### When to think about it

* Counting overlaps.
* Active intervals.
* Skyline problems.
* Calendar problems.
* Rectangle coverage.

### Key Idea

Convert intervals into events.

### Example

Intervals:

[1,5]
[2,8]
[4,6]

Events:

(1,+1)
(2,+1)
(4,+1)
(5,-1)
(6,-1)
(8,-1)

Process from left to right.

Track active intervals.

### Template

Convert to events → Sort events → Sweep.

---

# Quick Decision Guide

## Need merged intervals?

Sort by START

Examples:

* Merge Intervals
* Insert Interval

---

## Need overlap detection?

Sort by START

Examples:

* Meeting Rooms
* Calendar problems

---

## Need maximum non-overlapping intervals?

Sort by END

Examples:

* Interval Scheduling
* Non-overlapping Intervals

---

## Need overlap counts or active intervals?

Use Sweep Line

Examples:

* Meeting Rooms II
* Skyline Problem
* Calendar III

---

## Need fast range updates/queries?

Use Segment Tree

Examples:

* Calendar III
* Rectangle Area
* Dynamic interval queries

---

# Golden Rule

For interval problems, first ask:

1. Do I need to MERGE intervals?
2. Do I need to DETECT overlap?
3. Do I need MAXIMUM non-overlapping intervals?
4. Do I need COUNT of active overlaps?
5. Do I need FAST range updates/queries?
