import java.util.*;

public class AlgorithmActivities {
    // Activity 1: Longest Common Subsequence (LCS)
    public static String getLCS(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder lcsBuilder = new StringBuilder();
        int i = len1, j = len2;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcsBuilder.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcsBuilder.reverse().toString();
    }

    // Activity 2: Brute Force Pattern Matching
    public static List<Integer> searchPattern(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int textLen = text.length();
        int patternLen = pattern.length();

        for (int i = 0; i <= textLen - patternLen; i++) {
            boolean isMatch = true;
            for (int j = 0; j < patternLen; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                occurrences.add(i);
            }
        }
        return occurrences;
    }

    // Activity 3: Recursive Binary Search
    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = left + (right - left) / 2;
        if (arr[middle] == target) {
            return middle;
        }
        if (target < arr[middle]) {
            return binarySearch(arr, target, left, middle - 1);
        }
        return binarySearch(arr, target, middle + 1, right);
    }

    // Activity 4: Fractional Knapsack Problem
    public static double getMaxValue(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }
        Arrays.sort(items, Comparator.comparingDouble(i -> -((double) i.value / i.weight)));

        double totalValue = 0.0;
        for (Item item : items) {
            if (capacity == 0) break;
            if (item.weight <= capacity) {
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                totalValue += ((double) item.value / item.weight) * capacity;
                break;
            }
        }
        return totalValue;
    }

    static class Item {
        int weight, value;
        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        // Testing LCS
        String str1 = "ABCBDAB";
        String str2 = "BDCAB";
        System.out.println("LCS: " + getLCS(str1, str2));

        // Testing Brute Force Pattern Matching
        String text = "ABCABCD";
        String pattern = "ABC";
        System.out.println("Pattern found at: " + searchPattern(text, pattern));

        // Testing Binary Search
        int[] arr = {1, 3, 5, 7, 9};
        int target = 5;
        System.out.println("Binary Search Result: " + binarySearch(arr, target, 0, arr.length - 1));

        // Testing Fractional Knapsack
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        System.out.println("Max Value in Knapsack: " + getMaxValue(weights, values, capacity));
    }
}
