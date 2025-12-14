class Solution {
    // Time O(nP+nlogk), Space O(n); Top-down DP with memo
    public int getKth(int lo, int hi, int k) {
        // [number, power/step] to break the tie w/o calling helper
        // kth in ascending/smallest -> maxHeap
        PriorityQueue<int[]> maxHeap =
            new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        Map<Integer, Integer> numberToPower = new HashMap<>();
        for (int n = lo; n <= hi; n++) {
            int power = getCollatzPower(n, numberToPower);
            int[] numberPower = new int[]{n, power};
            maxHeap.offer(numberPower);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        int[] kth = maxHeap.peek();
        return kth[0];
    }
    // DFS w/ memo; Time O(n)
    private int getCollatzPower(int n, Map<Integer, Integer> numberToSteps) {
        // base case, then memo
        if (n == 1) {
            return 0;
        }
        if (numberToSteps.containsKey(n)) {
            return numberToSteps.get(n);
        }
        if (n % 2 == 0) {
            numberToSteps.put(n, 1 + getCollatzPower(n / 2, numberToSteps));
        }
        if (n % 2 == 1) {
            numberToSteps.put(n , 1 + getCollatzPower(3 * n + 1, numberToSteps));
        }
        return numberToSteps.get(n);
    }
}
