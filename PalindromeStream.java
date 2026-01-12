class PalindromeStream {
    private long forwardHash;
    private long backwardHash;
    private final long BASE;
    private final long MOD;
    private int length;
    private List<Long> basePowers;  // basePowers[i] = base^i % mod

    public PalindromeStream() {
        forwardHash = 0;
        backwardHash = 0;
        BASE = 31;
        MOD = 1000000007L;
        length = 0;
        // list len is also how many chars in total
        basePowers = new ArrayList<>();
        // e.g. 31^0 = 1L, 31^2 ..., i = index = len of string
        basePowers.add(1L);  // basePowers[i] = base^i % mod
    }

    public void track(char c) {
        long charVal = (long)(c - 'a') + 1;
        // Extend base powers if needed
        if (length >= basePowers.size()) {
            long lastPower = basePowers.get(basePowers.size() - 1);
            // which is basically prev*31
            basePowers.add((lastPower * BASE) % MOD);
        }
        // Update forward hash: add new character at the end
        // appending the new character's value.
        forwardHash = (forwardHash * BASE + charVal) % MOD;
        // Update backward hash: add new character at the beginning with appropriate weight
        // prepending the new character's value
        backwardHash = (charVal * basePowers.get(length) + backwardHash) % MOD;
        length++;
    }

    public boolean isPalindrome() {
        return forwardHash == backwardHash;
    }
}
