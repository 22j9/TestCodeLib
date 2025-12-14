class Solution {
    // Time O(W+26^2), Space O(26); Hash Table w/ Array
    public boolean equalFrequency(String s) {
        int[] charCount = new int[26];
        for (char current : s.toCharArray()) {
            charCount[current - 'a']++;
        }
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] == 0) {
                continue; // no count, skip
            }
            charCount[i]--;
            if (isEqualized(charCount)) {
                return true;
            }
            charCount[i]++;
        }
        return false;
    }

    private boolean isEqualized(int[] charCount) {
        int onlyFrequency = -1; // only frequency allowed
        for (int freq : charCount) {
            if (freq == 0) {
                continue;
            }
            // not initialized
            if (onlyFrequency == -1 && freq != 0) {
                onlyFrequency = freq;
            }
            // initialized but mismatch
            if (onlyFrequency != -1 && freq != onlyFrequency) {
                return false;
            }
        }
        return true;
    }
}
