# Binary Search: Common Mistakes & Pitfalls Guide (Java)

A comprehensive guide covering common mistakes in binary search implementations across all problem variants using Java.

---

## Table of Contents

1. [Classic Binary Search Mistakes](#classic-binary-search-mistakes)
2. [Index Calculation Errors](#index-calculation-errors)
3. [Boundary Condition Mistakes](#boundary-condition-mistakes)
4. [Loop Termination Errors](#loop-termination-errors)
5. [Search Space Reduction Mistakes](#search-space-reduction-mistakes)
6. [Variant-Specific Mistakes](#variant-specific-mistakes)
7. [Edge Case Handling](#edge-case-handling)
8. [Performance Pitfalls](#performance-pitfalls)

---

## Classic Binary Search Mistakes

### 1. Integer Overflow in Mid Calculation

**Wrong:**
```java
int mid = (left + right) / 2;  // Can overflow when left + right > Integer.MAX_VALUE
```

**Correct:**
```java
int mid = left + (right - left) / 2;
// or
int mid = (left + right) >>> 1;  // Unsigned right shift
```

**Example of overflow:**
```java
int left = Integer.MAX_VALUE - 5;
int right = Integer.MAX_VALUE;
int mid = (left + right) / 2;  // Overflow! Results in negative number
```

### 2. Using Wrong Division Operator

**Wrong:**
```java
double mid = (left + right) / 2.0;  // Returns double, but we need int
int index = (int) mid;  // Unsafe casting
```

**Correct:**
```java
int mid = (left + right) / 2;  // Integer division in Java
```

### 3. Wrong Comparison Operator

**Wrong:**
```java
if (arr[mid] == target) {
    return mid;
} else if (arr[mid] <= target) {  // Should be <, not <=
    left = mid + 1;
}
```

**Correct:**
```java
if (arr[mid] == target) {
    return mid;
} else if (arr[mid] < target) {
    left = mid + 1;
} else {
    right = mid - 1;
}
```

---

## Index Calculation Errors

### 4. Off-by-One Errors in Initialization

**Wrong:**
```java
int left = 1;  // Should start at 0
int right = arr.length;  // Should be arr.length - 1 for inclusive bounds
```

**Correct:**
```java
int left = 0;
int right = arr.length - 1;  // For inclusive binary search
// OR
int right = arr.length;  // For exclusive upper bound variant
```

### 5. Forgetting to Update Pointers

**Wrong:**
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] < target) {
        left = mid;  // Missing +1, causes infinite loop
    } else {
        right = mid;  // Missing -1, causes infinite loop
    }
}
```

**Correct:**
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```

### 6. Inconsistent Pointer Updates with Loop Condition

**Wrong:**
```java
while (left < right) {  // Condition is <
    int mid = left + (right - left) / 2;
    if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;  // Should be mid, not mid - 1 for left < right
    }
}
```

**Correct:**
```java
while (left < right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid;  // No -1 when using left < right
    }
}
```

---

## Boundary Condition Mistakes

### 7. Not Handling Empty Array

**Wrong:**
```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {  // Will fail on empty array (right = -1)
        int mid = left + (right - left) / 2;
        // ...
    }
    return -1;
}
```

**Correct:**
```java
public int binarySearch(int[] arr, int target) {
    if (arr == null || arr.length == 0) {  // Handle null and empty array
        return -1;
    }
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        // ...
    }
    return -1;
}
```

### 8. Not Handling Single Element Array

**Wrong:**
```java
// Assuming array has at least 2 elements
if (arr[0] > arr[1]) {  // ArrayIndexOutOfBoundsException on single element
    // ...
}
```

**Correct:**
```java
if (arr.length < 2) {
    // Handle single element case
    return arr.length == 1 ? arr[0] : -1;
}
```

### 9. Wrong Return Value When Element Not Found

**Wrong:**
```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    // Missing return statement - compilation error in Java
}
```

**Correct:**
```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;  // Element not found
}
```

---

## Loop Termination Errors

### 10. Infinite Loop Due to Wrong Mid Calculation

**Wrong:**
```java
while (left < right) {
    int mid = (left + right) / 2;  // Always rounds down
    if (condition) {
        left = mid;  // When left = right - 1, mid = left, infinite loop!
    } else {
        right = mid - 1;
    }
}
```

**Correct:**
```java
while (left < right) {
    int mid = (left + right + 1) / 2;  // Round up to avoid infinite loop
    if (condition) {
        left = mid;
    } else {
        right = mid - 1;
    }
}
```

### 11. Wrong Loop Condition Choice

**Wrong:**
```java
// Using <= when you should use <, or vice versa
while (left <= right) {  // Want to find insertion point
    int mid = left + (right - left) / 2;
    if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid;  // Inconsistent with <=
    }
}
```

**Correct:**
```java
while (left < right) {  // Use < for finding insertion point
    int mid = left + (right - left) / 2;
    if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid;
    }
}
return left;  // Insertion point
```

### 12. Not Understanding When Loop Terminates

**Common misconception:** Loop terminates when element is found.

**Reality:** Loop terminates when search space is exhausted (left > right or left == right depending on variant).

---

## Search Space Reduction Mistakes

### 13. Not Maintaining Search Space Invariant

**Wrong:**
```java
// Looking for first occurrence
while (left < right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) {
        return mid;  // Wrong! Might not be first occurrence
    } else if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```

**Correct:**
```java
// Looking for first occurrence
while (left < right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid;  // Keep searching left for first occurrence
    }
}
return (left < arr.length && arr[left] == target) ? left : -1;
```

### 14. Excluding Mid Too Early

**Wrong:**
```java
// Finding minimum in rotated sorted array
while (left < right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] > arr[right]) {
        left = mid + 1;
    } else {
        right = mid - 1;  // Wrong! mid could be the answer
    }
}
```

**Correct:**
```java
// Finding minimum in rotated sorted array
while (left < right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] > arr[right]) {
        left = mid + 1;  // Mid can't be minimum
    } else {
        right = mid;  // Mid could be minimum
    }
}
return arr[left];
```

---

## Variant-Specific Mistakes

### 15. First/Last Occurrence Mistakes

**Finding First Occurrence - Wrong:**
```java
public int firstOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;  // Might not be first!
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```

**Correct - Method 1:**
```java
public int firstOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            result = mid;
            right = mid - 1;  // Keep searching left
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
```

**Correct - Method 2 (More Elegant):**
```java
public int firstOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return (left < arr.length && arr[left] == target) ? left : -1;
}
```

**Finding Last Occurrence:**
```java
public int lastOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            result = mid;
            left = mid + 1;  // Keep searching right
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
```

### 16. Rotated Array Search Mistakes

**Wrong:**
```java
// Not properly identifying which half is sorted
public int search(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[left] < arr[mid]) {  // Left half sorted
            if (arr[left] <= target && target < arr[mid]) {  // Missing = on left
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // Missing else case!
    }
    return -1;
}
```

**Correct:**
```java
public int search(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[left] <= arr[mid]) {  // Left half sorted (include =)
            if (arr[left] <= target && target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {  // Right half sorted
            if (arr[mid] < target && target <= arr[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    return -1;
}
```

### 17. Peak Element Finding Mistakes

**Wrong:**
```java
// Comparing with wrong neighbors
public int findPeakElement(int[] arr) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] > arr[mid - 1]) {  // ArrayIndexOutOfBoundsException when mid = 0!
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```

**Correct:**
```java
public int findPeakElement(int[] arr) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] < arr[mid + 1]) {  // Safe, mid + 1 <= right always holds
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```

### 18. Square Root / Nth Root Mistakes

**Wrong:**
```java
// Finding square root - integer overflow
public int mySqrt(int x) {
    int left = 0, right = x;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (mid * mid == x) {  // Overflow when mid is large!
            return mid;
        } else if (mid * mid < x) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return right;
}
```

**Correct:**
```java
public int mySqrt(int x) {
    if (x == 0) return 0;
    int left = 1, right = x;
    int result = 0;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (mid <= x / mid) {  // Avoid overflow
            result = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
```

**Alternative - Using long:**
```java
public int mySqrt(int x) {
    if (x == 0) return 0;
    long left = 1, right = x;
    long result = 0;
    while (left <= right) {
        long mid = left + (right - left) / 2;
        if (mid * mid == x) {
            return (int) mid;
        } else if (mid * mid < x) {
            result = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return (int) result;
}
```

### 19. 2D Matrix Search Mistakes

**Wrong:**
```java
// Treating 2D matrix as 1D incorrectly
public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length, n = matrix[0].length;
    int left = 0, right = m * n - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int row = mid / m;  // Wrong! Should divide by n
        int col = mid % m;  // Wrong! Should mod by n
        // ...
    }
    return false;
}
```

**Correct:**
```java
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    int m = matrix.length, n = matrix[0].length;
    int left = 0, right = m * n - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        int row = mid / n;  // Correct
        int col = mid % n;  // Correct
        if (matrix[row][col] == target) {
            return true;
        } else if (matrix[row][col] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return false;
}
```

### 20. Search in Infinite/Unknown Size Array Mistakes

**Wrong:**
```java
// Trying to find upper bound incorrectly
public int search(ArrayReader reader, int target) {
    int high = 1;
    while (reader.get(high) < target) {
        high *= 2;  // Might overshoot badly
    }
    // Then binary search between 0 and high - inefficient
}
```

**Correct:**
```java
public int search(ArrayReader reader, int target) {
    // Exponential search for bounds
    int low = 0, high = 1;
    while (reader.get(high) < target) {
        low = high;
        high *= 2;  // Keep low to reduce search space
    }
    
    // Binary search between low and high
    while (low <= high) {
        int mid = low + (high - low) / 2;
        int val = reader.get(mid);
        if (val == target) {
            return mid;
        } else if (val < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return -1;
}
```

---

## Edge Case Handling

### 21. Duplicate Elements Confusion

**Problem:** Not clear whether to return any occurrence, first, or last.

**Wrong:**
```java
// Inconsistent handling of duplicates
public int search(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;  // Which occurrence? Unspecified!
        }
        // ...
    }
    return -1;
}
```

**Correct - First Occurrence:**
```java
public int firstOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return (left < arr.length && arr[left] == target) ? left : -1;
}
```

**Correct - Last Occurrence:**
```java
public int lastOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = left + (right - left + 1) / 2;  // Round up!
        if (arr[mid] <= target) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return (left < arr.length && arr[left] == target) ? left : -1;
}
```

### 22. Negative Numbers Handling

**Wrong:**
```java
// Assuming array only has positive numbers
public int findSqrt(int x) {
    int left = 0, right = x;  // Breaks when x is negative
    // ...
}
```

**Correct:**
```java
public int findSqrt(int x) {
    if (x < 0) {
        throw new IllegalArgumentException("Cannot find square root of negative number");
    }
    if (x == 0) {
        return 0;
    }
    int left = 1, right = x;
    // ... rest of implementation
}
```

### 23. Large Number Overflow

**Wrong:**
```java
// For very large numbers
public int mySqrt(int x) {
    int left = 0, right = x;
    while (left <= right) {
        int mid = (left + right) / 2;  // Can overflow!
        if (mid * mid == x) {  // Overflow!
            return mid;
        }
        // ...
    }
    return -1;
}
```

**Correct - Method 1 (Division):**
```java
public int mySqrt(int x) {
    if (x == 0) return 0;
    int left = 1, right = x;
    int result = 0;
    while (left <= right) {
        int mid = left + (right - left) / 2;  // Avoid overflow
        if (mid <= x / mid) {  // Avoid overflow by using division
            result = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}
```

**Correct - Method 2 (Using long):**
```java
public int mySqrt(int x) {
    if (x == 0) return 0;
    long left = 1, right = x;
    long result = 0;
    while (left <= right) {
        long mid = left + (right - left) / 2;
        if (mid * mid <= x) {
            result = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return (int) result;
}
```

---

## Performance Pitfalls

### 24. Unnecessary Comparisons

**Wrong:**
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) {
        return mid;
    }
    if (arr[mid] < target) {  // Redundant if, should use else if
        left = mid + 1;
    }
    if (arr[mid] > target) {
        right = mid - 1;
    }
}
```

**Correct:**
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) {
        return mid;
    } else if (arr[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```

### 25. Recalculating Length in Loop

**Wrong:**
```java
while (left <= right) {
    int mid = left + (arr.length - 1 - left) / 2;  // Recalculating length!
    // ...
}
```

**Correct:**
```java
int n = arr.length;
int left = 0, right = n - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;
    // ...
}
```

### 26. Using Wrong Data Structure for Frequent Insertions

**Wrong:**
```java
// Using ArrayList for insertions during binary search
List<Integer> list = new ArrayList<>();
while (searching) {
    // ... binary search logic
    list.add(mid, value);  // O(n) operation in ArrayList!
}
```

**Correct:**
```java
// Use TreeSet for O(log n) insertions with ordering
TreeSet<Integer> set = new TreeSet<>();
set.add(value);  // O(log n)

// Or if duplicates allowed, use TreeMap
TreeMap<Integer, Integer> map = new TreeMap<>();
map.put(value, map.getOrDefault(value, 0) + 1);
```

---

## Additional Common Mistakes

### 27. Floating Point Precision Issues

**Wrong:**
```java
// Comparing floating point numbers directly
public double findSqrt(double x) {
    double left = 0, right = x;
    while (left <= right) {
        double mid = (left + right) / 2.0;
        if (mid * mid == x) {  // Floating point equality is unreliable!
            return mid;
        } else if (mid * mid < x) {
            left = mid;
        } else {
            right = mid;
        }
    }
    return left;
}
```

**Correct:**
```java
public double findSqrt(double x) {
    double left = 0, right = x;
    double epsilon = 1e-9;
    while (right - left > epsilon) {
        double mid = (left + right) / 2.0;
        if (Math.abs(mid * mid - x) < epsilon) {
            return mid;
        } else if (mid * mid < x) {
            left = mid;
        } else {
            right = mid;
        }
    }
    return left;
}
```

### 28. Not Considering Search Space Monotonicity

**Wrong:**
```java
// Applying binary search on non-monotonic function
public boolean canComplete(int time, int tasks) {
    // ... some complex logic that isn't monotonic
}

public int minTime(int tasks) {
    int left = 0, right = Integer.MAX_VALUE;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (canComplete(mid, tasks)) {  // Assumes monotonic, might not be!
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

**Correct:**
```java
// Verify the search space is monotonic first
// If canComplete(x) is true, then canComplete(x+1) must also be true
// Only then apply binary search

public int minTime(int tasks) {
    // Ensure canComplete() is monotonic
    int left = 0, right = Integer.MAX_VALUE;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (canComplete(mid, tasks)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### 29. Confusion Between Lower Bound and Upper Bound

**Lower Bound (first position where arr[i] >= target):**
```java
public int lowerBound(int[] arr, int target) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```

**Upper Bound (first position where arr[i] > target):**
```java
public int upperBound(int[] arr, int target) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] <= target) {  // Note: <= instead of <
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```

### 30. Template Confusion

**Three Main Templates - Use Consistently:**

**Template 1: Standard (left <= right)**
```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return -1;
}
```

**Template 2: Advanced (left < right)**
```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```

**Template 3: Advanced with ceiling (left < right)**
```java
public int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int mid = left + (right - left + 1) / 2;  // Round up
        if (arr[mid] > target) {
            right = mid - 1;
        } else {
            left = mid;
        }
    }
    return left;
}
```

---

## Best Practices Checklist

- [ ] Check for null and empty array before starting
- [ ] Use correct loop condition (< or <=)
- [ ] Calculate mid without overflow: `left + (right - left) / 2`
- [ ] Update left/right pointers correctly (+1 or -1 when needed)
- [ ] Match pointer updates with loop condition
- [ ] Handle element not found case
- [ ] Consider duplicate elements
- [ ] Test with edge cases: null, empty, single element, two elements
- [ ] Verify search space is monotonic
- [ ] Use appropriate template consistently
- [ ] Check for integer overflow in calculations (use long if needed)
- [ ] Handle boundary conditions correctly
- [ ] Return correct value (index, element, or -1)
- [ ] Consider floating point precision if applicable
- [ ] Use meaningful variable names

---

## Quick Reference Table

| Mistake Type | Common Error | Fix |
|-------------|--------------|-----|
| Mid calculation | `(left + right) / 2` | `left + (right - left) / 2` |
| Overflow prevention | `mid * mid` | `mid <= x / mid` or use `long` |
| Infinite loop | `left = mid` with `left < right` | Use `mid = (left + right + 1) / 2` |
| First occurrence | Return immediately when found | Continue searching left: `right = mid - 1` |
| Last occurrence | Return immediately when found | Continue searching right: `left = mid + 1` |
| Empty array | No check | Add `if (arr == null \|\| arr.length == 0)` |
| Rotated array | Wrong sorted half check | Use `arr[left] <= arr[mid]` with `<=` |
| 2D to 1D | `row = mid / m` | `row = mid / n` (n = cols) |
| Array bounds | `arr[mid - 1]` when mid = 0 | Use `arr[mid + 1]` comparison |

---

## Common LeetCode Problems by Variant

### Classic Binary Search
- LeetCode 704: Binary Search
- LeetCode 374: Guess Number Higher or Lower

### First/Last Occurrence
- LeetCode 34: Find First and Last Position of Element in Sorted Array
- LeetCode 278: First Bad Version

### Rotated Array
- LeetCode 33: Search in Rotated Sorted Array
- LeetCode 81: Search in Rotated Sorted Array II (with duplicates)
- LeetCode 153: Find Minimum in Rotated Sorted Array
- LeetCode 154: Find Minimum in Rotated Sorted Array II

### Peak Element
- LeetCode 162: Find Peak Element
- LeetCode 852: Peak Index in a Mountain Array

### 2D Matrix
- LeetCode 74: Search a 2D Matrix
- LeetCode 240: Search a 2D Matrix II

### Square Root / Power
- LeetCode 69: Sqrt(x)
- LeetCode 367: Valid Perfect Square
- LeetCode 50: Pow(x, n)

### Binary Search on Answer
- LeetCode 875: Koko Eating Bananas
- LeetCode 1011: Capacity To Ship Packages Within D Days
- LeetCode 410: Split Array Largest Sum

---

## Array Rotation Logic (Not Binary Search, but Important for Rotated Array Problems)

### Understanding Array Rotation

**Example Array: [4, 6, 5]**

#### Left Rotation by k times

**Left Rotation by k=1:**
- Move first element to end
- [4, 6, 5] → [6, 5, 4]

**Left Rotation by k=2:**
- [4, 6, 5] → [6, 5, 4] → [5, 4, 6]

**Left Rotation by k=3:**
- [4, 6, 5] → [6, 5, 4] → [5, 4, 6] → [4, 6, 5] (back to original!)

**General pattern:** Left rotation by k is same as left rotation by k % n (where n = array length)

#### Right Rotation by k times

**Right Rotation by k=1:**
- Move last element to front
- [4, 6, 5] → [5, 4, 6]

**Right Rotation by k=2:**
- [4, 6, 5] → [5, 4, 6] → [6, 5, 4]

**Right Rotation by k=3:**
- [4, 6, 5] → [5, 4, 6] → [6, 5, 4] → [4, 6, 5] (back to original!)

### Implementation Methods

#### Method 1: Using Extra Space (Simple but O(n) space)

**Left Rotate by k:**
```java
public void rotateLeft(int[] arr, int k) {
    int n = arr.length;
    k = k % n;  // Handle k > n
    
    int[] temp = new int[n];
    
    // Copy elements from index k onwards to beginning
    for (int i = 0; i < n - k; i++) {
        temp[i] = arr[k + i];
    }
    
    // Copy first k elements to end
    for (int i = 0; i < k; i++) {
        temp[n - k + i] = arr[i];
    }
    
    // Copy back to original array
    for (int i = 0; i < n; i++) {
        arr[i] = temp[i];
    }
}
```

**Example with [4, 6, 5], k=1:**
```
Original: [4, 6, 5]
Step 1: Copy from index 1 onwards → temp = [6, 5, _]
Step 2: Copy first 1 element to end → temp = [6, 5, 4]
Result: [6, 5, 4]
```

**Right Rotate by k:**
```java
public void rotateRight(int[] arr, int k) {
    int n = arr.length;
    k = k % n;  // Handle k > n
    
    int[] temp = new int[n];
    
    // Copy last

1. **Add print statements** to track mid, left, right in each iteration:
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    System.out.println("left=" + left + ", mid=" + mid + ", right=" + right);
    // ... rest of code
}
```

2. **Verify invariant**: What property must hold for left and right?

3. **Test with small arrays**: `{1}`, `{1,2}`, `{1,2,3}`

4. **Check boundary**: What happens when `left == right`?

5. **Trace through manually** with pencil and paper

6. **Verify monotonicity**: Is the search space truly monotonic?

7. **Use assertions** to catch bugs early:
```java
assert left <= right : "Invariant violated";
assert mid >= left && mid <= right : "Mid out of bounds";
```

---

## Conclusion

Binary search is deceptively simple but has many subtle edge cases. The key to mastering it is:

1. **Understand which template to use** based on the problem
2. **Maintain clear invariants** about your search space
3. **Be consistent** with boundary updates
4. **Test thoroughly** with edge cases (null, empty, single element, duplicates)
5. **Practice different variants** to build intuition
6. **Watch for integer overflow** - use `left + (right - left) / 2`
7. **Understand the monotonicity** requirement

Remember: When in doubt, go back to first principles - what property guarantees the element is in the left half vs right half?

---

## Additional Resources

- **Java Collections**: `Arrays.binarySearch()`, `Collections.binarySearch()`
- **TreeSet/TreeMap**: For maintaining sorted data with O(log n) operations
- **Comparator**: For custom binary search on objects

**Using built-in binary search:**
```java
int[] arr = {1, 2, 3, 4, 5};
int index = Arrays.binarySearch(arr, 3);  // Returns 2
// If not found, returns -(insertion point) - 1
```

---

*Created for comprehensive binary search reference in Java*
