class Solution {
    public int collatzSteps(int n) {
        Map<Integer, Integer> numberToSteps = new HashMap<>();
        return collatzSteps(n, numberToSteps);
    }

    private int collatzSteps(int n, Map<Integer, Integer> numberToSteps) {
        if (n == 1) {
            return 0;
        }
        // memo
        if (numberToSteps.containsKey(n)) {
            return numberToSteps.get(n);
        }
        if (n % 2 == 0) {
            numberToSteps.put(n, 1 + collatzSteps(n / 2, numberToSteps));
        }
        if (n % 2 == 1) {
            numberToSteps.put(n, 1 + collatzSteps(3 * n + 1, numberToSteps));
        }
        return numberToSteps.get(n);
    }
}
