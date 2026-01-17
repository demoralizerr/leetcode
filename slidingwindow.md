# 🟢 Sliding Window Cheat Sheet

A quick reference for solving **sliding window problems** in coding interviews and contests. Covers both **fixed-size** and **variable-size** windows.

---

## 1️⃣ Window Types

- **Fixed-size:** Window length = k  
- **Variable-size:** Unknown length, condition-based (e.g., sum ≥ target, ≤ X, at most K distinct characters)

---

## 2️⃣ Templates

### **Variable-Size Window**
```java
int left = 0, ans = 0, windowValue = 0;
for (int right = 0; right < n; right++) {
    windowValue += nums[right];          // expand window

    while (condition) {                   // shrink as much as possible
        ans = update(ans, right - left + 1, windowValue);
        windowValue -= nums[left];
        left++;
    }
}
return ans;
```

### **Fixed-Size Window**
```java
int left = 0, ans = 0, windowValue = 0;
for (int right = 0; right < n; right++) {
    windowValue += nums[right];          // expand window

    if (right - left + 1 == k) {         // shrink to maintain size
        ans = update(ans, right - left + 1, windowValue);
        windowValue -= nums[left];
        left++;
    }
}
return ans;
```

# 🟢 Sliding Window Problem Patterns

A categorized reference for solving **sliding window problems** in coding interviews and contests.  
Covers both **fixed-size** and **variable-size (dynamic)** windows.

---

## 1️⃣ Fixed-Size Window Patterns (size = k)

**Use when:** window size is given or fixed, e.g., “every subarray of size k”, “maximum/minimum of k elements”.  

| Pattern | Description | What to Track |
|---------|------------|---------------|
| Maximum Sum of Size K | Find max sum of k consecutive elements | Window sum |
| Minimum Sum of Size K | Find min sum of k consecutive elements | Window sum |
| Sliding Window Average | Average of every k-length subarray | Window sum or average |
| Maximum/Minimum in Window of Size K | Find max/min in every k-length window | Window max/min (deque) |
| Count of Elements Matching a Condition | E.g., number of even numbers in every window of size k | Window count |
| Fixed-length Substring Problems | Substring length = k, e.g., check pattern | Hashmap or frequency count |

**Key:** Use `if (right - left + 1 == k)` and shrink window after updating answer.

---

## 2️⃣ Variable-Size Window Patterns (dynamic size)

**Use when:** window length is unknown and depends on a condition, e.g., sum ≥ target, ≤ X, or K distinct characters.  

| Pattern | Description | What to Track |
|---------|------------|---------------|
| Minimum Size Subarray Sum | Find smallest subarray with sum ≥ target | Window sum, min length |
| Longest Substring with At Most K Distinct Characters | Find longest substring ≤ K distinct characters | Frequency map, unique count |
| Longest Substring Without Repeating Characters | Max-length substring with unique chars | Hashmap or set |
| Maximum Sum ≤ Target | Find largest subarray sum ≤ X | Window sum |
| At Most K Ones/Zeros | Binary array, subarray with ≤ K ones/zeros | Count |
| Subarray with Product ≥ X | Product of elements ≥ X | Window product (careful!) |

**Key:** Use `while (condition)` and shrink window inside while loop, update answer while shrinking.

---

## 3️⃣ Quick Tips to Identify Pattern
1. **Fixed-size:** Problem explicitly mentions a **length k**, “every subarray of size k”, or “window length fixed”.  
2. **Variable-size:** Problem mentions a **threshold or limit**, “sum ≥ target”, “at most K distinct”, “≤ X”, “product ≥ X”, etc.  

---

## 4️⃣ Memory Trick
> **“Fixed → if, Variable → while”**  

- **Fixed-size:** shrink only after reaching window size  
- **Variable-size:** shrink as long as condition is satisfied  

---



